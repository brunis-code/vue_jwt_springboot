import Vue from 'vue';

const API_URL = '/api/test/';

class UserService {
  getPublicContent() {
    return Vue.prototype.$http.get(API_URL + 'all');
  }

  getUserBoard() {
    return Vue.prototype.$http.get(API_URL + 'user');
  }

  getModeratorBoard() {
    return Vue.prototype.$http.get(API_URL + 'mod');
  }

  getAdminBoard() {
    return Vue.prototype.$http.get(API_URL + 'admin');
  }
}

export default new UserService();
