import { Method, createAlova } from 'alova'
import GlobalFetch from 'alova/GlobalFetch'
import VueHook from 'alova/vue'
import { isEmpty } from 'lodash'
import { PaginationIn } from '../models-in'
import { PageOut } from '../models-out'
import { BadRequestEvent, ForbiddenEvent, InternalServerErrorEvent, OkEvent, UnauthorizedEvent } from './event'
import { Model } from './models'

export const alova = createAlova({
  baseURL: '/api/v1',
  statesHook: VueHook,
  requestAdapter: GlobalFetch(),
  beforeRequest: (method) => {
    if (isEmpty(method.data)) return
    method.data = (method.data as Model).$data()
  },
  localCache: { GET: 0 },
  responded: {
    onSuccess: async (response: Response, method: Method): Promise<object> => {
      switch (response.status) {
        case 200:
          return new OkEvent().emit(response, method)
        case 400:
          return new BadRequestEvent().emit(response)
        case 401:
          return new UnauthorizedEvent().emit()
        case 403:
          return new ForbiddenEvent().emit()
        case 500:
          return new InternalServerErrorEvent().emit(response)
        default:
          return {}
      }
    },
    onError: (error: any) => {
      throw error
    }
  }
})

export type RequestConfig<T = any> = {
  params?: object
  headers?: object
  transformData?: (data: any, headers: any) => T | Promise<T>
}

export class SimpleRequest {
  constructor(public url: string = '') {}

  get<T>(url: string = '', config?: RequestConfig<T>) {
    return alova.Get(`${this.url}${url}`, config)
  }

  post<T>(url: string = '', data?: Model, config?: RequestConfig<T>) {
    return alova.Post(`${this.url}${url}`, data, config)
  }

  put<T>(url: string = '', data?: Model, config?: RequestConfig<T>) {
    return alova.Put(`${this.url}${url}`, data, config)
  }

  patch<T>(url: string = '', data?: Model, config?: RequestConfig<T>) {
    return alova.Patch(`${this.url}${url}`, data, config)
  }

  delete<T>(url: string = '', data?: Model, config?: RequestConfig<T>) {
    return alova.Delete(`${this.url}${url}`, data, config)
  }
}

export class GenericRequest<T extends Model = Model> extends SimpleRequest {
  constructor(public url: string = '', public type: typeof Model = Model) {
    super(url)
  }

  $list(url: string = '', params: PaginationIn = PaginationIn.$new()) {
    return super.get<PageOut<T>>(url, {
      params: (() => {
        const data = params.$data()
        data.page--
        return data
      })(),
      transformData: (data: any) => {
        data.content = data.content.map((x: any) => this.type.$new(x) as T)
        return PageOut.$new(data) as PageOut<T>
      }
    })
  }

  $retrieve(url: string = '') {
    return super.get<T>(url, {
      transformData: (data: any) => this.type.$new(data) as T
    })
  }

  $create(url: string = '', data?: Model) {
    return super.post<T>(url, data, {
      transformData: (data: any) => this.type.$new(data) as T
    })
  }

  $update(url: string = '', data?: Model) {
    return super.put(url, data)
  }

  $patch(url: string = '', data?: Model) {
    return super.patch(url, data)
  }

  $destroy(url: string = '', data?: Model) {
    return super.delete(url, data)
  }
}
