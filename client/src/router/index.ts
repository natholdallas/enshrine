import { isLogin, type VerifyFunc } from '@/common/permission'
import { createRouter, createWebHistory, type NavigationGuardWithThis } from 'vue-router'
import { urls } from './urls'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      name: '主页',
      path: urls.home,
      redirect: urls.star,
      beforeEnter: routeCtl({ auth: [isLogin], backPath: urls.entrance }),
      component: () => import('@/views/HomeView.vue'),
      children: [
        {
          name: '设置',
          path: urls.settings,
          component: () => import('@/views/SettingsView.vue')
        },
        {
          name: '收藏的明星',
          path: urls.star,
          component: () => import('@/views/StarView.vue')
        },
        {
          name: '收藏的视频',
          path: urls.video,
          component: () => import('@/views/VideoView.vue')
        },
        {
          name: '收藏的网站',
          path: urls.website,
          component: () => import('@/views/WebsiteView.vue')
        }
      ]
    },
    {
      path: urls.entrance,
      beforeEnter: routeCtl({ auth: [isLogin], nextPath: urls.home }),
      component: () => import('@/views/EntranceView.vue')
    }
  ]
})

type RouteCTL = {
  auth?: VerifyFunc[]
  backPath?: string
  nextPath?: string
}

type Navi = NavigationGuardWithThis<undefined>

function routeCtl({ auth, backPath, nextPath }: RouteCTL = {}): Navi | Navi[] {
  return async (to, from, next) => {
    if (!auth) return next()
    const $auth = auth.some((e) => e())
    const path = $auth ? nextPath : backPath
    if (!path) return next()
    to = router.resolve(path)
    return next(to)
  }
}

export default router
