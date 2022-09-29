<template>
  <ol class="list-group ">
    <div class="fw-bold">
      <div class="input-group">
        <div class="input-group-prepend">
          <label class="input-group-text">방제목</label>
        </div>
        <input type="text" class="form-control" v-model="board_title" @keyup.enter="createBoard(board_title)">
        <div class="input-group-append">
          <button class="btn btn-primary" type="button" @click="createBoard(board_title)">채팅방 개설</button>
        </div>
      </div>

    </div>
    <li v-for="data in boardList" v-on:click="enterBoard(data.boardId)"
        class="list-group-item d-flex justify-content-between align-items-start">
      <div class="ms-2 me-auto">
        <div class="fw-bold"><h5 class="mb-1">{{ data.boardTitle }}</h5></div>
        닉네임 : {{ data.boardWriter }}
      </div>
      <span class="btn float-end btn-outline-danger">마감</span>
    </li>
  </ol>
  <b-modal v-model="modalShow">Hello From Modal!</b-modal>
</template>

<script setup>
import axios from "axios";
import {ref} from 'vue'
import {useStore} from "vuex";
import {useRouter} from "vue-router/dist/vue-router";

const modalShow = false;
const store = useStore();
const boardList = ref([])
const router = useRouter();
const modal = ref(false);
const board_title = '';
const myPositions = ref([]);
const otherPositions = ref([]);

const createBoard = (board_title) => {
  console.log(board_title)
  if("" === board_title) {
    alert("방 제목을 입력해 주십시요.");
    return;
  } else {
    axios.post('/board-create', {boardTitle : board_title,boardWriter: store.state.username, user: {userId : store.state.userId}}).then(response => {
      console.log("게시판 성공")
      getBoardList()
    }).catch(e =>{
      alert(e.getMessage())
    })
  }

}

const getBoardList = () => {
  axios.post('/board-all', {}, {
    auth: {
      username: store.state.username,
      password: "1234"
    }
  }).then(
      response => {
        console.log("불러오기 성공")
        boardList.value = response.data
      }
  )
      .catch(response => {
        alert(response);
      });
}

const enterBoard = (boardId) => {
  console.log("들어갈려는 모집 공고" + boardId)
  axios.post(`/chat/room?boardId=${boardId}&userName=${store.state.username}&userId=${store.state.userId}`, {}, {
    headers: {
      'Content-Type': 'application/json',
    }, auth: {
      username: store.state.username,
      password: "1234"
    }
  }).then(res => {
    localStorage.setItem('wschat.roomId', res.data.participants.roomId);
    localStorage.setItem('wschat.participantId', res.data.participants.participantId);
    router.push("/chat-detail")
  }).catch(error => {
    const err = error;
    if (err.response) {
      alert("state : " + err.response.data);
    }
  })
}
getBoardList()
</script>

<style scoped>

</style>