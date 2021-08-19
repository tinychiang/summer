import { createApp } from "vue";
import router from "./router";
import elementPlus from "element-plus";
import App from "./App.vue";
import "element-plus/lib/theme-chalk/index.css";

createApp(App).use(router).use(elementPlus).mount("#app");
