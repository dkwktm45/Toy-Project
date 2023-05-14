<template>
  <div>
    <div class="mb-3 row">
      <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="inputEmail" value="email@example.com">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
      <div class="col-sm-10">
        <input type="password" class="form-control" id="inputPassword">
      </div>
    </div>
    <div class="d-flex justify-content-center">
      <div class="mb-3 row w-50">
        <button type="button" class="btn btn-primary my-3 ">로그인</button>
        <button @click.prevent="kakaoLogin" type="button" class="btn btn-warning">카카오 로그인</button>
        <button type="button" class="btn btn-dark my-3">구글 로그인</button>
      </div>
    </div>
  </div>
</template>
<script setup>
import axios from "axios";
const kakaoLogin = () => {
  axios.get("/kakao/login").then(resposne =>{
    console.log(resposne)
    window.location.href= resposne.data
  })
}
</script>

<style scoped>

</style>