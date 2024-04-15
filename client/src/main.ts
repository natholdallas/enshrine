import { createPinia } from 'pinia'
import { loadFull } from 'tsparticles'
import { createApp } from 'vue'

import VueParticles from '@tsparticles/vue3'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import App from './App.vue'
import router from './router'

import { Confirm, Loading, Message, Notice, Toast } from 'vexip-ui'
import 'vexip-ui/css/dark/index.css'
import 'vexip-ui/css/index.css'
import 'virtual:uno.css'
import './assets/main.css'

const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

const app = createApp(App)
app.use(pinia)
app.use(router)

// vexip-ui
app.use(Notice)
app.use(Toast)
app.use(Message)
app.use(Loading)
app.use(Confirm, { iconProps: { scale: 3 } })

// vue-particles
app.use(VueParticles, { init: async (engine) => await loadFull(engine) })

app.mount('#app')
