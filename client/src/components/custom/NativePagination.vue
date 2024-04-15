<script setup lang="ts">
import type { PaginationIn } from '@/app/models-in'
import type { PageOut } from '@/app/models-out'
import { Pagination } from 'vexip-ui'

defineProps<{
  model: PageOut
}>()

defineEmits<{
  (e: 'change'): void
}>()

const pagination = defineModel<PaginationIn>({ required: true })
</script>

<template>
  <Pagination
    size="small"
    @change="(v) => ((pagination.page = v), $emit('change'))"
    @page-size-change="(v) => ((pagination.size = v), $emit('change'))"
    :page-size="pagination.size"
    :total="model.total"
    :plugins="['size', 'jump', 'total']"
    background
    no-border
  />
</template>

<style scoped></style>
