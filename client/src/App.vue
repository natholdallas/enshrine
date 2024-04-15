<script setup lang="ts">
import { useConfigStore } from '@/stores/configStore'
import { ConfigProvider, type PropsOptions } from 'vexip-ui'

const configStore = useConfigStore()

const propsOptions: PropsOptions = {
  icon: {
    size: 'large'
  },
  form: {
    labelAlign: 'top'
  },
  input: {
    clearable: true
  },
  modal: {
    width: 500,
    noFooter: true
  },
  divider: {
    margin: '0'
  },
  card: {
    contentStyle: {
      padding: '0.5rem'
    }
  }
}
</script>

<template>
  <ConfigProvider :props="propsOptions" :theme="configStore.darkMode ? 'dark' : 'light'">
    <RouterView v-slot="{ Component }">
      <Transition name="vxp-fade">
        <component :is="Component" />
      </Transition>
    </RouterView>
  </ConfigProvider>
</template>

<style scoped></style>
