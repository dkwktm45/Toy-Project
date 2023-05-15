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
const kakaoLogin = () => {
  const url = "https://kauth.kakao.com/oauth/authorize?client_id=dd90fef97c072b58ed261dd3722b83b0&redirect_uri=http://localhost:8080/kakao/login&response_type=code";
  window.open(url,"_blank", "width=500, height=500")
}
</script>

<style scoped>

</style>