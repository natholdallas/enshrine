<script setup lang="ts">
import { Account } from '@/app/api'
import { LoginIn } from '@/app/models-in'
import { urls } from '@/router/urls'
import { Lock as ILock, User as IUser } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input } from 'vexip-ui'
import { reactive } from 'vue'

const model = reactive(LoginIn.$new())

const { loading, send, onSuccess } = useRequest(Account.$login(model), {
  immediate: false
})
onSuccess(() => (location.href = urls.home))
</script>

<template>
  <Form label-align="top" :model="model" :rules="model.$validations" :loading="loading" all-required>
    <FormItem label="邮箱" prop="email">
      <Input placeholder="请输入" :prefix="IUser" />
    </FormItem>
    <FormItem label="密码" prop="password">
      <Input type="password" placeholder="请输入" :prefix="ILock" :max-length="20" plain-password />
    </FormItem>
    <FormItem action>
      <FormSubmit label="登录" :loading="loading" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
