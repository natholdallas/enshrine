import type { ConfigOut } from '@/app/models-out'
import { defineStore } from 'pinia'

export type Cfg = {
  editMode: boolean
  darkMode: boolean
  menuReduced: boolean
  avatarCircle: boolean
}

export const useConfigStore = defineStore('config', {
  state: defaultValue,
  actions: {
    reset: () => (useConfigStore().$state = defaultValue()),
    update: (config: ConfigOut) => {
      useConfigStore().$state = {
        editMode: config.editMode,
        darkMode: config.darkMode,
        menuReduced: config.menuReduced,
        avatarCircle: config.avatarCircle
      }
    }
  },
  persist: {
    storage: localStorage
  }
})

function defaultValue(): Cfg {
  return {
    editMode: false,
    darkMode: false,
    menuReduced: false,
    avatarCircle: false
  }
}
