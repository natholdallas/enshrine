<script setup lang="ts">
import { Website } from '@/app/api'
import { WebsiteIn } from '@/app/models-in'
import { Heading as IHeading, Link as ILink } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Textarea, Toast } from 'vexip-ui'
import { reactive } from 'vue'

const emit = defineEmits<{
  (e: 'success', model: Website): void
}>()

const model = reactive(WebsiteIn.$new())

const { loading, send, onSuccess } = useRequest(() => Website.$create(model), {
  immediate: false
})

onSuccess((v) => {
  emit('success', v.data)
  model.$reset()
})
</script>

<template>
  <Form :model="model" :rules="model.$validations" :loading="loading">
    <FormItem label="标题" prop="title" required>
      <Input :prefix="IHeading" />
    </FormItem>
    <FormItem label="链接" prop="siteUrl">
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
