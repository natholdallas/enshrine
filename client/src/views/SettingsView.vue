<script setup lang="ts">
import { Config, User } from '@/app/api'
import ComCtl from '@/components/ComCtl.vue'
import PatchEmailForm from '@/components/form/patch/PatchEmailForm.vue'
import PatchPasswordForm from '@/components/form/patch/PatchPasswordForm.vue'
import ConfigUpdateForm from '@/components/form/update/ConfigUpdateForm.vue'
import UserUpdateForm from '@/components/form/update/UserUpdateForm.vue'
import { useRequest } from 'alova'
import { Card as VCard } from 'vexip-ui'

const {
  loading: luser,
  data: user,
  send: refreshUser
} = useRequest(User.$retrive(), {
  initialData: User.$new()
})

const {
  loading: lconfig,
  data: config,
  send: refreshConfig
} = useRequest(Config.$retrieve(), {
  initialData: Config.$new()
})
</script>

<template>
  <ComCtl content-class="flex flex-col gap-2 sm:mx-auto sm:max-w-xl" :loading="[luser, lconfig]" scroll>
    <VCard title="用户设置">
      <ConfigUpdateForm @success="refreshConfig" :value="config" />
    </VCard>
    <VCard title="用户信息">
      <template #extra>
        <div>创建于: {{ new Date(user.createTime).toDateString() }}</div>
        <div>上次更新: {{ new Date(user.latestUpdate).toDateString() }}</div>
      </template>
      <UserUpdateForm @success="refreshUser" :value="user" />
    </VCard>
    <VCard title="重新设置密码">
      <PatchPasswordForm :email="user.email" />
    </VCard>
    <VCard title="重新设置邮箱">
      <PatchEmailForm />
    </VCard>
  </ComCtl>
</template>

<style scoped></style>
