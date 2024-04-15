import { defineConfig, presetUno, transformerDirectives } from 'unocss'

defineConfig({
  rules: [],
  presets: [presetUno()],
  transformers: [
    transformerDirectives({
      applyVariable: ['--uno']
    })
  ]
})
