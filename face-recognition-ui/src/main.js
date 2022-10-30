import Vue from 'vue'
import App from './App'
import store from './store'

Vue.config.productionTip = false;
Vue.prototype.$store = store;

App.mpType = 'app';

const app = new Vue({
  ...App
});
app.$mount();

Vue.prototype.$showLoading = () => {
  store.commit('request_show_loading');
}; //全局显示动画可以 this.$showLoading();
Vue.prototype.$hideLoading = () => {
  store.commit('request_hide_loading');
};
