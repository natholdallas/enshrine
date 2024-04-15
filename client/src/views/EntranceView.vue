<script setup lang="ts">
import { options } from '@/common/particles.config'
import LoginForm from '@/components/form/LoginForm.vue'
import RegisterForm from '@/components/form/RegisterForm.vue'
import { Card as VCard, TabPanel as VTabPanel, Tabs as VTabs } from 'vexip-ui'
</script>

<template>
  <div class="fixed flex flex-col w-full h-full justify-center items-center">
    <vue-particles class="z-[-1]" id="tsparticles" :options="options" />
    <VCard class="w-100 p-4">
      <VTabs>
        <VTabPanel label="登录">
          <LoginForm />
        </VTabPanel>
        <VTabPanel label="注册">
          <RegisterForm />
        </VTabPanel>
      </VTabs>
    </VCard>
  </div>
</template>

<style scoped></style>
