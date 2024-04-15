<script setup lang="ts">
import { Code } from '@/app/api'
import { CodeIn } from '@/app/models-in'
import { useRequest } from 'alova'
import { Captcha, Form, FormItem, FormSubmit, Modal, Toast, type CaptchaExposed } from 'vexip-ui'
import { computed, ref } from 'vue'

const props = defineProps<{
  email: string
}>()

const model = computed(() => CodeIn.$new({ email: props.email }))
const captchaModal = ref(false)
const captcha = ref<CaptchaExposed>()
const countdown = ref<number>(0)

const { loading, send, onSuccess } = useRequest(() => Code.$create(model.value), {
  immediate: false
})
onSuccess(() => {
  loading.value = true
  countdown.value = 60
  const interval = setInterval(() => {
    if (countdown.value === 0) {
      clearInterval(interval)
      loading.value = false
      return
    }
    --countdown.value
  }, 1000)
})
</script>

<template>
  <div>
    <div>
      <Form :model="model" :rules="model.$validations" :loading="loading">
        <FormItem prop="email" v-show="false" />
        <FormItem action>
          <FormSubmit
            :label="`${countdown ? countdown : ''} 发送验证码`"
            :loading="loading"
            @error="(v) => Toast.error(v[0])"
            @submit="captchaModal = true"
          />
          <Modal width="auto" :mask-close="false" v-model:active="captchaModal">
            <Captcha
              v-if="captchaModal"
              ref="captcha"
              image="https://www.vexipui.com/picture-2.jpg"
              @success="send(), (captchaModal = false)"
            />
          </Modal>
        </FormItem>
      </Form>
    </div>
  </div>
</template>

<style scoped></style>
