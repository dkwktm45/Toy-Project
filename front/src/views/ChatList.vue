<template>
  <div id="app" v-cloak>
    <div class="myRoom">
      <div class="row mb-3">
        <div class="col-md-12">
          <h3>내가 모집한 채팅방</h3>
        </div>
      </div>
      <ul class="list-group room">
        <li class="list-group-item list-group-item-action" v-for="item in myRoomList"
            v-on:click="enterRoom(item.roomId, item.participantId)">
          <h5 >참여자 : {{ item.userName }}</h5>
          <div class="float-end">제목 : {{ item.boardTitle }}</div>
        </li>
      </ul>
    </div>
    <div class="otherRoom">
      <div class="row mb-3">
        <div class="col-md-12">
          <h3>내가 지원한 채팅방</h3>
        </div>
      </div>
      <ul class="list-group room">
        <li class="list-group-item list-group-item-action" v-for="item in otherRoomList"
            v-on:click="enterRoom(item.boardParticipantsList[0].roomId, item.boardParticipantsList[0].participantId)">
          <h5 >제목 : {{ item.boardTitle }}</h5>
          <div class="float-end">{{ item.boardWriter }}</div>
        </li>
      </ul>
    </div>
  </div>

</template>
<script setup>
import {useRouter} from 'vue-router';
import {ref} from 'vue'
import axios from 'axios';
import {useStore} from "vuex";

const router = useRouter();
const store = useStore();
const user_name = ref("");
const myRoomList = ref([]);
const otherRoomList = ref([]);

const myBoardRoom = () => {
  axios.post('/chat/my-rooms',store.state.userId, {
    headers: {
      "Content-Type": `application/json`,
    }, auth: {
      username: store.state.username,
      password: "1234"
    }
  }).then(response => {
    myRoomList.value = response.data;
    console.log(myRoomList.value)
  }).catch(e => {
    console.log(e)
  });
}

const otherBoardRoom = () => {
  axios.post('/chat/other-rooms',store.state.userId, {
    headers: {
      "Content-Type": `application/json`,
    }, auth: {
      username: store.state.username,
      password: "1234"
    }
  }).then(response => {
    otherRoomList.value = response.data;
  }).catch(e => {
    console.log(e)
    alert("로그인 요청에 문제가 발생했습니다.")
  });
}

const enterRoom = (roomId, participantId) => {
  localStorage.setItem('wschat.roomId', roomId);
  localStorage.setItem('wschat.participantId', participantId);
  router.push("/chat-detail")
}
myBoardRoom()
otherBoardRoom()
</script>
<style>
.otherRoom{
  width: 70%;
  height: 100px;
  position: fixed;
  bottom: 40%;
}
.myRoom{
  width: 70%;
  height: 10%;
  position: fixed;
  bottom: 80%;
}
.room{
  max-height: 300px;
  overflow-y: scroll;
}
.room::-webkit-scrollbar {
  width: 10px;
}
.room::-webkit-scrollbar-thumb {
  background-color: #2f3542;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
.room::-webkit-scrollbar-track {
  background-color: grey;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
</style>
