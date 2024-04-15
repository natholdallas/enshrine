<script setup lang="ts">
import { Star } from '@/app/api'
import ImageUploader from '@/components/custom/ImageUploader.vue'
import { Signature as ISignature } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Textarea, Toast } from 'vexip-ui'
import { computed } from 'vue'

const props = defineProps<{
  value: Star
}>()
const emit = defineEmits<{
  (e: 'success', model: Star): void
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
  <Form ref="form" :model="model" :rules="model.$validations" :loading="loading">
    <FormItem label="名称" prop="name" required>
      <Input :prefix="ISignature" />
    </FormItem>
    <FormItem label="封面">
      <ImageUploader v-model="model.imageUrl" />
    </FormItem>
    <FormItem label="注释" prop="description">
      <Textarea></Textarea>
    </FormItem>
    <FormItem action>
      <FormSubmit @error="(v) => Toast.error(v[0])" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
