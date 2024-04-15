<script setup lang="ts">
import type { Video } from '@/app/api'
import { Heading as IHeading, Link as ILink } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Textarea, Toast } from 'vexip-ui'
import { computed } from 'vue'

const props = defineProps<{
  value: Video
}>()
const emit = defineEmits<{
  (e: 'success', model: Video): void
}>()

const model = computed(() => props.value.$copy())

const { loading, send, onSuccess } = useRequest(() => model.value.$update(), {
  immediate: false
})

onSuccess(() => {
  emit('success', model.value.$copy())
  model.value.$reset()
})
</script>

<template>
  <Form :model="model" :rules="model.$validations" :loading="loading">
    <FormItem label="标题" prop="title" required>
      <Input :prefix="IHeading" />
    </FormItem>
    <FormItem label="链接" prop="videoUrl">
      <Input :prefix="ILink" />
    </FormItem>
    <FormItem label="说明" prop="description">
      <Textarea></Textarea>
    </FormItem>
    <FormItem action>
      <FormSubmit @error="(v) => Toast.error(v[0])" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
