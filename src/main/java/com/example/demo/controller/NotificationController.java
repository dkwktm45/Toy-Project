package com.example.demo.controller;

import com.example.demo.model.redis.Alarm;
import com.example.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

	private final NotificationService notificationService;
	private static final Map<String, SseEmitter> CLIENTS = new ConcurrentHashMap<>();

	@GetMapping(value = "/receive/notify/{userId}", produces = "text/event-stream")
	public SseEmitter receiveNotify(@PathVariable("userId") String userId) {
		SseEmitter sseEmitter = new SseEmitter(1000 * 6000 * 15L);
		sseEmitter.onCompletion(() -> {
			notificationService.removeEmitter(userId);
		});
		sseEmitter.onTimeout(() -> {
			sseEmitter.complete();
			notificationService.removeEmitter(userId);
		});
		notificationService.setUserEmitter(userId, sseEmitter);
		return sseEmitter;
	}
	@DeleteMapping("/delete")
	public void deleteById(){

	}
	@PostMapping("/find")
	public Set<Alarm> findAll(@RequestParam String name){
		return notificationService.findAllNoti(name);
	}
}
