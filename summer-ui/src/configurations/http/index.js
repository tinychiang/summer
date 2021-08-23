import axios from 'axios';
import { ElMessage } from 'element-plus';
import store from '~@/store';

/**
 * axios配置
 */
const instance = axios.create({
  // 接口域名
  baseURL: import.meta.env.VITE_APP_URL,
  // 超时时限
  timeout: 6000,
  // 请求头
  headers: {
    'Content-Type': 'application/json;charset=UTF-8;',
  },
});

/**
 * 请求拦截器
 */
instance.interceptors.request.use(
  (config) => {
    const token = store.state.token;
    token && (config.headers.token = token);
    if (config.method === 'POST') {
      config.data = JSON.stringify(config.data);
    }
    return config;
  },
  (error) => Promise.reject(error)
);

/**
 * 响应拦截器
 */
instance.interceptors.response.use(
  (response) => {
    if (response.status != 200 && valid(response.data)) {
      throw new Error(response);
    }
    return handle(response.data);
  },
  (error) => Promise.reject(error)
);

/**
 * 响应参数格式检验
 * @param {*} object response.data
 * @returns true: 满足, false: 不满足
 */
const valid = (object) => {
  if (!object.code || !object.message || !object.data) {
    console.error('data struct is not match');
    return false;
  }
  return true;
};

/**
 * 出参业务判定处理
 * @param {*} object response.data
 * @returns
 * 200: object.data
 * 401: 移除登录信息
 * 其他: 弹出异常
 */
const handle = (object) => {
  if (code === '200') {
    return object.data;
  } else if (object.code === '401') {
    store.commit('remove');
    console.warn('token invalid');
  } else {
    ElMessage({
      type: 'error',
      showClose: true,
      message: '[' + object.code + ']' + ' ' + object.message,
    });
  }
};

export default instance;
