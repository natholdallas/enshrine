<script setup lang="ts">
import type { User } from '@/app/api'
import ImageUploader from '@/components/custom/ImageUploader.vue'
import { Code as ICode, Envelope as IEnvelope, IdCard as IIdCard } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Toast } from 'vexip-ui'
import { computed } from 'vue'

const props = defineProps<{
  value: User
}>()
const emit = defineEmits<{
  (e: 'success'): void
}>()

const model = computed(() => props.value.$copy())

const { loading, send, onSuccess } = useRequest(() => model.value.$update(), {
  immediate: false
})
onSuccess(() => emit('success'))
</script>

<template>
  <Form :model="model" :rules="model.$validations" :loading="loading">
    <FormItem label="标识" prop="id">
      <Input :prefix="IIdCard" disabled />
    </FormItem>
    <FormItem label="邮箱" prop="email">
      <Input :prefix="IEnvelope" disabled />
    </FormItem>
    <FormItem label="昵称" prop="nickname">
      <Input :prefix="ICode" :max-length="20" />
    </FormItem>
    <FormItem label="设置头像">
      <ImageUploader v-model="model.avatarUrl" />
    </FormItem>
    <FormItem action>
      <FormSubmit @error="(v) => Toast.error(v[0])" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
