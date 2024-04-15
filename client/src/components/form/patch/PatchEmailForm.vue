<script setup lang="ts">
import { User } from '@/app/api'
import { EmailIn } from '@/app/models-in'
import SendCodeForm from '@/components/form/SendCodeForm.vue'
import { Code as ICode, Envelope as IEnvelope } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Toast } from 'vexip-ui'
import { reactive } from 'vue'

const model = reactive(EmailIn.$new())

const { loading, send } = useRequest(User.$patchEmail(model), {
  immediate: false
})
</script>

<template>
  <Form :model="model" :rules="model.$validations" :loading="loading" all-required>
    <FormItem label="新邮箱" prop="email">
      <div class="w-full flex gap-2">
        <Input :prefix="IEnvelope" />
        <SendCodeForm :email="model.email" />
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
