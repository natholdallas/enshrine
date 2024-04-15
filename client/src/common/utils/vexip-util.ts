import type { User } from '@/app/api'
import type { LayoutUser, TableExposed } from 'vexip-ui'

export function vexipUser(user: User): LayoutUser {
  return {
    name: user.nickname ? user.nickname : user.id,
    email: user.email,
    avatar: user.avatarUrl ? user.avatarUrl : undefined
  }
}

export function rowOptions(array: string[]) {
  return array.map((x) => ({
    label: x,
    value: x
  }))
}

export function tableToSelections<T = any>(table?: TableExposed): T[] {
  return table?.getSelected() ?? []
}

export function tableToSelectionIds(table?: TableExposed): number[] {
  return (table?.getSelected() ?? []).map((x) => x.id)
}
