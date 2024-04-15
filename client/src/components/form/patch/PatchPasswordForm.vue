<script setup lang="ts">
import { User } from '@/app/api'
import { PasswordIn } from '@/app/models-in'
import SendCodeForm from '@/components/form/SendCodeForm.vue'
import { Code as ICode, Envelope as IEnvelope } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Notice, Toast } from 'vexip-ui'
import { reactive } from 'vue'

defineProps<{
  email: string
}>()

const model = reactive(PasswordIn.$new())

const { loading, send, onSuccess } = useRequest(User.$patchPassword(model), {
  immediate: false
})

onSuccess(() => {
  Notice.success('密码重置成功, 下次登录时将生效')
  model.$reset()
})
</script>

<template>
  <Form :model="model" :rules="model.$validations" :loading="loading" all-required>
    <FormItem label="新密码" prop="password">
      <div class="w-full flex gap-2">
        <Input :prefix="IEnvelope" />
        <SendCodeForm :email="email" />
      </div>
    </FormItem>
    <FormItem label="验证码" prop="code">
      <Input :prefix="ICode" />
    </FormItem>
    <FormItem action>
      <FormSubmit @error="(v) => Toast.error(v[0])" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
