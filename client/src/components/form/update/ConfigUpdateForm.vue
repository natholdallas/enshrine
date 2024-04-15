<script setup lang="ts">
import { Config } from '@/app/api'
import { useConfigStore } from '@/stores/configStore'
import { useRequest } from 'alova'
import { Form, FormItem, FormSubmit, Switch, Toast } from 'vexip-ui'
import { computed } from 'vue'

const props = defineProps<{
  value: Config
}>()
const emit = defineEmits<{
  (e: 'success'): void
}>()

const configStore = useConfigStore()

const model = computed(() => props.value.$copy())

const { loading, send, onSuccess } = useRequest(() => model.value.$update(), {
  immediate: false
})
onSuccess(() => {
  emit('success')
  configStore.update(model.value)
})
</script>

<template>
  <Form label-align="left" :model="model" :rules="model.$validations" :loading="loading">
    <FormItem help="默认为关闭，开启后每项数据都会有编辑面板" label="编辑模式" prop="editMode">
      <Switch />
    </FormItem>
    <FormItem help="设置是否为暗黑主题/明亮主题" label="暗黑主题" prop="darkMode">
      <Switch />
    </FormItem>
    <FormItem help="设置侧边栏默认为折叠" label="折叠侧边栏" prop="menuReduced">
      <Switch />
    </FormItem>
    <FormItem help="设置主页右上角为圆形头像，而不是方形" label="圆形头像" prop="avatarCircle">
      <Switch />
    </FormItem>
    <FormItem action>
      <FormSubmit @error="(v) => Toast.error(v[0])" @submit="send" block />
    </FormItem>
  </Form>
</template>

<style scoped></style>
