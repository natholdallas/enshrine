<script setup lang="ts">
import { Star } from '@/app/api'
import { $m, middlewares } from '@/app/core/middlewares'
import { PaginationIn } from '@/app/models-in'
import { queryAsInt } from '@/common/utils/route-util'
import ComCtl from '@/components/ComCtl.vue'
import NativePagination from '@/components/custom/NativePagination.vue'
import StarCreateForm from '@/components/form/create/StarCreateForm.vue'
import StarUpdateForm from '@/components/form/update/StarUpdateForm.vue'
import { useConfigStore } from '@/stores/configStore'
import { PenToSquare as IPenToSquare, Plus as IPlus, Trash as ITrash } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Button as VButton, Card as VCard, Divider as VDivider, Image as VImage, Modal as VModal } from 'vexip-ui'
import { reactive, ref } from 'vue'

const configStore = useConfigStore()

const pagination = reactive(PaginationIn.$new({ page: queryAsInt('page', 1), size: queryAsInt('size', 20) }))
const createStarModal = ref(false)
const updateStarModal = ref(false)

const $star = ref(Star.$new())
const {
  loading: lstar,
  data: stars,
  send: refresh
} = useRequest(() => Star.$list(pagination), {
  initialData: []
})
const {
  loading: ldestroy,
  send: destroy,
  onSuccess: onDestroySuccess
} = useRequest(() => $star.value.$destroy(), {
  immediate: false,
  middleware: middlewares($m.destroyDialog)
})
onDestroySuccess(refresh)
</script>

<template>
  <ComCtl content-class="flex flex-col gap-4" :loading="[lstar, ldestroy]" scroll>
    <div class="flex gap-2 overflow-auto">
      <div>
        <VButton type="primary" @click="createStarModal = true" :icon="IPlus" circle />
      </div>
      <NativePagination v-model="pagination" @change="refresh" :model="stars" />
    </div>
    <TransitionGroup
      name="group-fade"
      tag="div"
      class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 2xl:grid-cols-6 gap-2"
    >
      <VCard v-for="(i, index) in stars.content" :content-style="{ padding: 0 }" :key="index" shadow="hover">
        <VImage fit="cover" width="100%" height="200" :src="i.imageUrl" lazy skeleton />
        <div class="p-2">
          <div class="truncate text-lg font-bold">{{ i.name }}</div>
          <div class="text-sm break-words">{{ i.description }}</div>
          <div class="text-sm text-stone-500">创建于: {{ new Date(i.createTime).toDateString() }}</div>
          <div class="text-sm text-stone-500">上次编辑: {{ new Date(i.latestUpdate).toDateString() }}</div>
        </div>
        <div v-if="configStore.editMode">
          <VDivider />
          <div class="flex flex-row-reverse">
            <VButton type="error" :icon="ITrash" @click=";($star = i), destroy()" text circle />
            <VButton :icon="IPenToSquare" @click=";($star = i), (updateStarModal = true)" text circle />
          </div>
        </div>
      </VCard>
    </TransitionGroup>
    <template #modals>
      <VModal v-model:active="createStarModal">
        <StarCreateForm @success="refresh(), (createStarModal = false)" />
      </VModal>
      <VModal v-model:active="updateStarModal">
        <StarUpdateForm @success="refresh(), (updateStarModal = false)" :value="$star" />
      </VModal>
    </template>
  </ComCtl>
</template>

<style scoped></style>
