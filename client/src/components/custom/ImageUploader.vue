<script setup lang="ts">
import { Upload } from 'vexip-ui'

const url = defineModel<string | undefined>({ required: true })
</script>

<template>
  <Upload
    tip="仅支持 jpeg, jpg, png"
    url="/api/v1/upload"
    @success="(file, response) => (url = response.url)"
    :filter="['jpeg', 'jpg', 'png']"
    :default-files="url ? [{ url: url }] : undefined"
    with-credentials
    image
  />
</template>

<style scoped></style>
