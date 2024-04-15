<script setup lang="ts">
import { Star } from '@/app/api'
import { StarIn } from '@/app/models-in'
import ImageUploader from '@/components/custom/ImageUploader.vue'
import { Message as IMessage, Signature as ISignature } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Input, Toast } from 'vexip-ui'
import { reactive } from 'vue'

const emit = defineEmits<{
  (e: 'success', model: Star): void
}>()

const model = reactive(StarIn.$new())

const { loading, send, onSuccess } = useRequest(() => Star.$create(model), {
  immediate: false
})
onSuccess((v) => (emit('success', v.data), model.$reset()))
</script>

<template>
  <Form :model="model" :rules="model.$validations" :loading="loading">
    <FormItem label="名称" prop="name" required>
      <Input :prefix="ISignature" />
    </FormItem>
    <FormItem label="封面">
      <ImageUploader v-model="model.imageUrl" />
    </FormItem>
    <FormItem label="注释" prop="description">
      <Input :prefix="IMessage" />
    </FormItem>
    <FormItem action>
      <FormSubmit @error="(v) => Toast.error(v[0])" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
