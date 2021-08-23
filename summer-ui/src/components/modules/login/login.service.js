import store from '~@/store';
import router from '~@/router';

export default {
  login: (username, password) => {
    store.commit('change', '123456');
    router.push({ name: 'dashboard' });
  },
};
