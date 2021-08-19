import Vuex from "vuex";

/**
 * Web Storage
 */
export default new Vuex.Store({
  state: {
    token: localStorage.getItem("token"),
  },
  mutations: {
    /**
     * 改变token
     * @param {*} state 存储信息
     * @param {*} token 登录口令
     */
    change(state, token) {
      state.token = token;
      localStorage.setItem("token", token);
    },
    /**
     * 删除token
     * @param {*} state 存储信息
     */
    remove(state) {
      state.token = null;
      localStorage.removeItem("token");
    },
  },
});
