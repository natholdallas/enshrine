<script setup lang="ts">
import { Account, Config, User } from '@/app/api'
import { $m, middlewares } from '@/app/core/middlewares'
import { vexipUser } from '@/common/utils/vexip-util'
import router from '@/router'
import { urls } from '@/router/urls'
import { useConfigStore } from '@/stores/configStore'
import {
  EarthAmericas as IEarthAmericas,
  Gear as IGear,
  RightFromBracket as IRightFromBracket,
  Star as IStar,
  Video as IVideo
} from '@vexip-ui/icons'
import { useRequest } from 'alova'
import type { LayoutHeaderAction, MenuOptions } from 'vexip-ui'
import { Breadcrumb, Layout as VLayout } from 'vexip-ui'

const configStore = useConfigStore()

const menus: MenuOptions[] = [
  {
    label: urls.star,
    name: '明星',
    icon: IStar
  },
  {
    label: urls.video,
    name: '视频',
    icon: IVideo
  },
  {
    label: urls.website,
    name: '网站',
    icon: IEarthAmericas
  }
]
const actions: LayoutHeaderAction[] = [
  {
    label: urls.settings,
    icon: IGear,
    name: '帐号设置',
    divided: true
  },
  {
    label: 'logout',
    name: '退出登录',
    icon: IRightFromBracket
  }
]

const { data: user } = useRequest(User.$retrive(), {
  initialData: User.$new()
})

const { data: config, onSuccess: onConfigSuccess } = useRequest(Config.$retrieve(), {
  initialData: Config.$new()
})
onConfigSuccess(() => configStore.update(config.value))

const { send: logout, onSuccess: onLogoutSuccess } = useRequest(Account.$logout(), {
  immediate: false,
  middleware: middlewares($m.logoutDialog)
})
onLogoutSuccess(() => router.go(0))
</script>

<template>
  <VLayout
    logo="./vite.svg"
    sign-name="Enshrine"
    copyright="Enshrine ❤️ 2024 NatholDallas"
    @menu-select="$router.push"
    @user-action="(label) => (label === 'logout' ? logout() : router.push(label))"
    :user="vexipUser(user)"
    :actions="actions"
    :menus="menus"
    :dark-mode="configStore.darkMode"
    :reduced="configStore.menuReduced"
    :avatar-circle="configStore.avatarCircle"
    fit-window
    fixed-main
  >
    <template #header-main>
      <div class="hidden sm:inline">
        <Breadcrumb :router="router" />
      </div>
    </template>
    <template #main>
      <RouterView v-slot="{ Component }">
        <Transition name="vxp-fade" mode="out-in">
          <component :is="Component" />
        </Transition>
      </RouterView>
    </template>
  </VLayout>
</template>

<style scoped></style>
