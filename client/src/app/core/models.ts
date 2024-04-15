import { cloneDeep } from 'lodash'
import type { Rule } from 'vexip-ui'

type NonFunctionPropertyNames<T> = {
  [K in keyof T]: T[K] extends Function ? never : K
}[keyof T]

type NonFunctionProperties<T> = Pick<T, NonFunctionPropertyNames<T>>

export class Model {
  $initialData?: object
  $validations?: Record<string, Rule | Rule[]>

  static $new<T extends Model>(this: new () => T, data?: Partial<NonFunctionProperties<T>>) {
    const model = new this()
    model.$initialData = cloneDeep(data)
    Object.assign(model, data)
    return model
  }

  $copy<T extends Model>(this: T): T {
    return cloneDeep(this)
  }

  $data(): any {
    const $data: Model = this.$copy()
    delete $data.$initialData
    delete $data.$validations
    return $data as object
  }

  $reset() {
    // @ts-ignore
    Object.assign(this, { ...this.constructor.$new(), ...cloneDeep(this.$initialData) })
  }
}

export class ModelIn {}

export class ModelOut {}
