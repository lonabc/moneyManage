import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)



export default new Vuex.Store({
  strict: true,
  state: {
    count:1,
    areLogin: false,
    tableData:[
      {
        date1:'',
        cost:'',
        type:'',
      }
    ]
  },
  getters: {
  },
  mutations: {
    testCount(state)
    {
       state.count+=1;
    },
    testLoginStatus(state,n)
    {
       state.areLogin=n;
    }
  },
  actions: {
  },
  modules: {
  }
})
