<script setup lang="ts">
import { Account } from '@/app/api'
import { RegisterIn } from '@/app/models-in'
import SendCodeForm from '@/components/form/SendCodeForm.vue'
import { Code as ICode, Envelope as IEnvelope, Lock as ILock } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Message, Toast } from 'vexip-ui'
import { reactive } from 'vue'

const model = reactive(RegisterIn.$new())

const { loading, send, onSuccess } = useRequest(Account.$register(model), {
  immediate: false
})
onSuccess(() => {
  Message.success({ content: '注册成功, 请前往登录界面', duration: 5000 })
  model.$reset()
})
</script>

<template>
  <Form :model="model" :rules="model.$validations" :loading="loading" all-required>
    <FormItem label="邮箱" prop="email">
      <div class="w-full flex gap-2">
        <Input :prefix="IEnvelope" />
        <SendCodeForm :email="model.email" />
      </div>
    </FormItem>
    <FormItem label="验证码" prop="code">
      <Input :prefix="ICode" />
    </FormItem>
    <FormItem label="密码" prop="password">
      <Input type="password" :prefix="ILock" :max-length="20" plain-password />
    </FormItem>
    <FormItem label="确认密码" prop="confirmPassword" required>
      <Input type="password" :prefix="ILock" :max-length="20" plain-password />
    </FormItem>
    <FormItem action>
      <FormSubmit label="注册" @error="(v) => Toast.error(v[0])" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
