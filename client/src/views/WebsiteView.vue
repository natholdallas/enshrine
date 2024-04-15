<script setup lang="ts">
import { Website } from '@/app/api'
import { $m, middlewares } from '@/app/core/middlewares'
import { PaginationIn } from '@/app/models-in'
import { queryAsInt } from '@/common/utils/route-util'
import ComCtl from '@/components/ComCtl.vue'
import NativePagination from '@/components/custom/NativePagination.vue'
import WebsiteCreateForm from '@/components/form/create/WebsiteCreateForm.vue'
import WebsiteUpdateForm from '@/components/form/update/WebsiteUpdateForm.vue'
import { useConfigStore } from '@/stores/configStore'
import { PenToSquare as IPenToSquare, Plus as IPlus, Trash as ITrash } from '@vexip-ui/icons'
import { useRequest } from 'alova'
import { Button as VButton, Card as VCard, Divider as VDivider, Linker as VLinker, Modal as VModal } from 'vexip-ui'
import { reactive, ref } from 'vue'

const configStore = useConfigStore()

const pagination = reactive(PaginationIn.$new({ page: queryAsInt('page', 1), size: queryAsInt('size', 20) }))
const createModal = ref(false)
const updateModal = ref(false)

const $website = ref(Website.$new())
const {
  loading: lwebsite,
  data: websites,
  send: refresh
} = useRequest(() => Website.$list(pagination), {
  initialData: []
})
const {
  loading: ldestroy,
  send: destroy,
  onSuccess: onDestroySuccess
} = useRequest(() => $website.value.$destroy(), {
  immediate: false,
  middleware: middlewares($m.destroyDialog)
})
onDestroySuccess(refresh)
</script>

<template>
  <ComCtl content-class="flex flex-col gap-4" :loading="[lwebsite, ldestroy]" scroll>
    <div class="flex gap-2 overflow-auto">
      <div>
        <VButton type="primary" @click="createModal = true" :icon="IPlus" circle />
      </div>
      <NativePagination v-model="pagination" @change="refresh" :model="websites" />
    </div>
    <TransitionGroup
      name="group-fade"
      tag="div"
      class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 2xl:grid-cols-5 gap-2"
    >
      <VCard v-for="(i, index) in websites.content" :content-style="{ padding: 0 }" :key="index">
        <template #header>
          <div class="truncate">
            <VLinker :to="i.siteUrl" underline>
              <div class="truncate text-lg font-bold">{{ i.title }}</div>
            </VLinker>
          </div>
        </template>
        <div class="p-2">
          <div class="text-sm break-words">{{ i.description || '暂无说明' }}</div>
          <div class="text-sm text-stone-500">创建于: {{ new Date(i.createTime).toDateString() }}</div>
          <div class="text-sm text-stone-500">上次编辑: {{ new Date(i.latestUpdate).toDateString() }}</div>
        </div>
        <div v-if="configStore.editMode">
          <VDivider />
          <div class="flex flex-row-reverse">
            <VButton type="error" :icon="ITrash" @click=";($website = i), destroy()" text />
            <VButton :icon="IPenToSquare" @click=";($website = i), (updateModal = true)" text />
          </div>
        </div>
      </VCard>
    </TransitionGroup>
    <template #modals>
      <VModal width="500" v-model:active="createModal">
        <WebsiteCreateForm @success="refresh(), (createModal = false)" />
      </VModal>
      <VModal width="500" v-model:active="updateModal">
        <WebsiteUpdateForm @success="refresh(), (updateModal = false)" :value="$website" />
      </VModal>
    </template>
  </ComCtl>
</template>

<style scoped></style>
