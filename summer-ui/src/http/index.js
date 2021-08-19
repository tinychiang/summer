import axios from "axios";
import store from "../store";

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
    "Content-Type": "application/json;charset=UTF-8;",
  },
});

/**
 * 请求拦截器
 */
instance.interceptors.request.use(
  (config) => {
    const token = store.state.token;
    token && (config.headers.token = token);
    if (config.method === "POST") {
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
  (response) => response.data,
  (error) => Promise.reject(error)
);

export default instance;
