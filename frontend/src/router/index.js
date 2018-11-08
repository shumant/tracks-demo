import Vue from 'vue'
import Router from 'vue-router'
import TrackTable from '@/components/TrackTable'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'TrackTable',
      component: TrackTable
    }
  ]
})
