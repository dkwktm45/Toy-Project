## :pushpin: chat program -Toy
#### 1:1 Open 채팅프로그램을 구현을 위한 설계

#### 개요
지금까지 프로젝트를 하면서 배포 경험은 나에게는 없던 경험이었다. 하지만 이번에 기회였던. [기초부터 시작하는 AWS 인프라 구축과 운영, 그리고 DevOps 엔지니어링 실무](https://github.com/dkwktm45/Toy-Project/files/9692285/PT_._DevOps_6._.pdf)  과정을 들으면서 ToyProject를 배포해 보기로 결심하면서 만든 프로젝트이다.

👉 [(my-test-ecs-alb-47067582.ap-northeast-2.elb.amazonaws.com)](http://my-test-ecs-alb-47067582.ap-northeast-2.elb.amazonaws.com/)
  - 로그인 아이디 : happydaddy,angrydaddy
  - 비밀번호 : 1234
  - **운영시간 : 09:00 - 18:00**
  - 비용 문제로 인한 지금은 해당 서비스를 운영중이 아닙니다.(면접시에만 운영되는점 양해 부탁드립니다.)
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
  - 내가 [참여한 채팅방](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/service/BoardParticipantService.java#L70-L74) 및 [내가 만든 게시판](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/service/BoardParticipantService.java#L62-L68)의 채팅 참가자를 나뉘어서 보입니다.
  - 채팅을 원할 시 클릭을 통해서 [채팅방](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/service/BoardParticipantService.java#L39-L60)으로 이동

#### 2. 채팅방
  ![image](https://user-images.githubusercontent.com/48014869/193448846-d072c69d-1580-4be8-96f9-625223bb4e72.png)
  - 채팅방은 Stompjs 를 사용했으며, 들어갈 시 [Stomp handler](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/config/handler/StompHandler.java) 에서 Redis를 통한 채팅방에 대한 조작을 합니다.
  - 해당 유저에게 메시지를 보낼때는 [sendChatMessage](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/service/ChatService.java#L47-L72)를 통해서 [publish](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/pubsub/RedisSubscriber.java#L21-L30) 된 유저에게 메시지를 보냅니다.

#### 3. 알림
  - 알림은 SSE 방식으로서 로그인이 되고, client는 자동으로 알림을 위한 [요청](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/controller/NotificationController.java#L22-L34)을 합니다.
  - 채팅방에서 유저 한명만 채팅을 하고 있다면, [emmitter](https://github.com/dkwktm45/Toy-Project/blob/eb4c4da159580eaaa91e06ed3e0c8951ef8554ec/src/main/java/com/example/demo/service/NotificationService.java#L30-L40)를 통한 상대방에게 알림을 보냅니다.
## Application Load Balancer 배포 전략
  - 어떻게 구성을 하였는가?
  
  ![image](https://user-images.githubusercontent.com/48014869/197376786-7cb6e848-f08e-4b26-b191-03eaf682ebe1.png)
  
  Docker를 통한 Docker hub에 이미지를 올리고 해당 이미지에 대한 작업정의서를 올려 port별로 health check를 합니다.
  - 배포 전략
  
  ![image](https://user-images.githubusercontent.com/48014869/197376811-fbdf45ad-ba5b-46e1-8510-6b46e6e10272.png)
  
  boto3를 통한 Lambda 등록, 등록된 Lambda함수를 Event Bridge에 일정 시간에 만 배포되어 있도록 설정


## Application Load Balancer 구성도
![image](https://user-images.githubusercontent.com/48014869/195769992-fc8c33c7-0cd3-4296-918a-c92a7afd9885.png)
**<span style = "color:red;">AWS의 ALB를 통한 무중단 베포를 구축했습니다.</span>**

<br>

### 보완해야 할점
- 베포상의 VueJS에서 Socket connect 지연 문제.
- CICD 구축



