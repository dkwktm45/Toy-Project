## :pushpin: chat program -Toy
#### 1:1 Open 채팅프로그램을 구현을 위한 설계

#### 개요
지금까지 프로젝트를 하면서 베포 경험은 나에게는 없던 경험이였다. 하지만 이번에 기회 였던. [기초부터 시작하는 AWS 인프라 구축과 운영, 그리고 DevOps 엔지니어링 실무](https://github.com/dkwktm45/Toy-Project/files/9692285/PT_._DevOps_6._.pdf) 과정을 들으면서 ToyProject를 베포 해보기로 결심하면서 만든 프로젝트이다.

이와 동시에 기존 프로젝트를 모각코딩을 위함이다.


### 1. 사용 기술
#### `Back-end`    
  - Java 11 , Spring Boot 2,7,2 , Gradle , Spring Data JSP 2.7.2 , Redis 2.7.2 , MySQL 5.1 ,Security 2.7.2 , Websocket 2.7.2
#### `Front-end`
  - Vue3 , Axios , Bootstrap
#### `DevOps`
  - EC2
  - ALB , ECS

</br>

## 2. 주요 기능
#### 1.채팅방 리스트
  ![image](https://user-images.githubusercontent.com/48014869/193448647-dde2e3aa-34fd-42da-8b91-3f7632906961.png)
  - 내가 [참여한 채팅방](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/service/BoardParticipantService.java#L70-L74) 및 [내가 만든 게시판](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/service/BoardParticipantService.java#L62-L68)의 채팅 참가자를 나뉘어서 보여집니다.
  - 채팅을 원할 시 클릭을 통해서 [채팅방](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/service/BoardParticipantService.java#L39-L60)으로 이동

#### 2. 채팅방
  ![image](https://user-images.githubusercontent.com/48014869/193448846-d072c69d-1580-4be8-96f9-625223bb4e72.png)
  - 채팅방은 Stompjs 를 사용했으며, 들어갈 시 [Stomp handler](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/config/handler/StompHandler.java) 에서 Redis를 통한 채팅방에 대한 조작을 합니다.
  - 해당 유저에게 메시지를 보낼때는 [sendChatMessage](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/service/ChatService.java#L47-L72)를 통해서 [publish](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/pubsub/RedisSubscriber.java#L21-L30) 된 유저에게 메시지를 보냅니다.

#### 3. 알림
  - 알림은 SSE 방식으로서 로그인이 되고, client는 자동으로 알림을 위한 [요청](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/controller/NotificationController.java#L22-L34)을 합니다.
  - 채팅방에서 유저 한명만 채팅을 하고 있다면, [emmitter](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/service/NotificationService.java#L30-L40)를 통한 상대방에게 알림을 보냅니다.

## Application Load Balancer 구성도
![image](https://user-images.githubusercontent.com/48014869/193449035-e238ed72-7e7d-4ac2-8300-3803337af389.png)
**<span style = "color:red;">AWS의 ALB를 통한 무중단 베포를 구축했습니다.</span>**

<br>

### 보완해야 할점
- 베포상의 VueJS에서 Socket connect 지연 문제.
- CICD 구축



