import { TriangleExclamation as ITriangleExclamation } from '@vexip-ui/icons'
import type { AlovaFrontMiddleware } from 'alova'
import { isArray } from 'lodash'
import { Confirm } from 'vexip-ui'

type ExpressionFunc = () => Promise<boolean>
type Middleware = AlovaFrontMiddleware<any, any, any, any, any, any, any>

export function middlewares(func: ExpressionFunc | ExpressionFunc[]): Middleware {
  return async (context, next) => {
    const $bool = await (isArray(func) ? func.some((x) => x()) : func())
    if ($bool) await next()
  }
}

export const $m = {
  async logoutDialog() {
    const isConfirm = await Confirm.open({
      title: '退出登录',
      content: '是否退出登录? 退出登录将清除登录状态, 但会保存你的配置文件在本地',
      confirmType: 'warning',
      icon: ITriangleExclamation
    })
    if (isConfirm) return true
    return false
  },

  async destroyDialog() {
    const isConfirm = await Confirm.open({
      title: '确认删除 ?',
      content: '确认删除数据 ?',
      confirmType: 'warning',
      icon: ITriangleExclamation
    })
    if (isConfirm) return true
    return false
  }
}
