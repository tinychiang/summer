import { createApp } from 'vue';
import ElementPlus from 'element-plus';
import Router from '~@/router';
import Store from '~@/store';
import App from '@/App.vue';
import 'element-plus/lib/theme-chalk/index.css';

createApp(App).use(Store).use(Router).use(ElementPlus).mount('#app');
