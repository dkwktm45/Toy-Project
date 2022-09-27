import { createApp } from 'vue'
import { createPinia } from 'pinia'
import Chat from './Chat.vue'
import router from './router/index.js'
import './assets/main.css'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import { BootstrapIconsPlugin } from 'bootstrap-icons-vue';
import store from "./stores/counter.js"
import axios from 'axios'


axios.defaults.baseURL = 'http://localhost:8080';

const app = createApp(Chat)
app.use(store)
app.use(createPinia())
app.use(BootstrapIconsPlugin)
app.use(router)
app.config.globalProperties.axios = axios;

app.mount('#app')
