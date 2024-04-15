<script setup lang="ts">
import { Video } from '@/app/api'
import { $m, middlewares } from '@/app/core/middlewares'
import ComCtl from '@/components/ComCtl.vue'
import VideoCreateForm from '@/components/form/create/VideoCreateForm.vue'
import VideoUpdateForm from '@/components/form/update/VideoUpdateForm.vue'
import { useConfigStore } from '@/stores/configStore'
import { PenToSquare as IPenToSquare, Plus as IPlus, Trash as ITrash } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Button as VButton, Card as VCard, Divider as VDivider, Linker as VLinker, Modal as VModal } from 'vexip-ui'
import { ref } from 'vue'

const configStore = useConfigStore()

const createModal = ref(false)
const updateModal = ref(false)

const $video = ref(Video.$new())
const {
  loading: lvideo,
  data: videos,
  send: refresh
} = useRequest(Video.$list(), {
  initialData: []
})
const {
  loading: ldestroy,
  send: destroy,
  onSuccess: onDestroySuccess
} = useRequest(() => $video.value.$destroy(), {
  immediate: false,
  middleware: middlewares($m.destroyDialog)
})
onDestroySuccess(refresh)
</script>

<template>
  <ComCtl content-class="flex flex-col gap-4" :loading="[lvideo, ldestroy]" scrolls>
    <div class="flex gap-2 overflow-auto">
      <VButton type="primary" @click="createModal = true" :icon="IPlus" />
    </div>
    <TransitionGroup
      name="group-fade"
      tag="div"
      class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 2xl:grid-cols-5 gap-2"
    >
      <VCard v-for="(i, index) in videos.content" :key="index">
        <VLinker :to="i.videoUrl" underline>
          {{ i.title }}
        </VLinker>
        <div class="text-sm break-words">{{ i.description || '暂无说明' }}</div>
        <div v-if="configStore.editMode">
          <VDivider />
          <div class="flex flex-row-reverse">
            <VButton :icon="IPenToSquare" @click=";($video = i), (updateModal = true)" text />
            <VButton type="error" :icon="ITrash" @click=";($video = i), destroy()" text />
          </div>
        </div>
      </VCard>
    </TransitionGroup>
    <template #modals>
      <VModal width="500" v-model:active="createModal">
        <VideoCreateForm @success="refresh(), (createModal = false)" />
      </VModal>
      <VModal width="500" v-model:active="updateModal">
        <VideoUpdateForm @success="refresh(), (updateModal = false)" :value="$video" />
      </VModal>
    </template>
  </ComCtl>
</template>

<style scoped></style>
