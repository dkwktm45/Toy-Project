<template>
  <form>
  <div class=" mb-3">
    <label for="exampleInputEmail1" class="form-label">login</label>
    <input type="username" v-model="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
    <div id="emailHelp" class="form-text">Please enter your email</div>
    </div>
    <div class="mb-3">
      <label for="exampleInputPassword1" class="form-label">Password</label>
      <input type="password" v-model="password" class="form-control" id="exampleInputPassword1">
    </div>
    <div class="mb-3 form-check">
      <input type="checkbox" class="form-check-input" id="exampleCheck1">
      <label class="form-check-label" for="exampleCheck1">Check me out</label>
    </div>
    <button type="button" class="btn btn-primary" @click="login()">Submit</button>
  </form>
</template>
<script setup>
import { useRouter } from 'vue-router';
import { useStore } from "vuex";
import axios from 'axios';
import { ref } from 'vue'
import qs from "eventsource/example/eventsource-polyfill";

const username = ref("")
const password = ref("")
const store = useStore()
const router = useRouter();

const login = () =>{

  axios.post("/loginInsert", {"userName" : username.value,"userPwd" : password.value } ,{
    auth : {
      username: username.value,
      password: password.value
    },headers :{
      'Accept': 'application/json',
      'Content-Type': `application/json`,
    }
  }).then(res => {
    store.commit("setUsername",res.data.userName)
    store.commit("setUserId",res.data.userId)
    store.commit("setToken",res.data.token)

    router.push("/board-list")
  }).catch(e => {
    console.log(e.response.data);
    console.log(e.response.status);
    console.log(e.response.headers);
  })
}

</script>
