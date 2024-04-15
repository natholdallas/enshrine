import { Model } from './core/models'

export class UserOut extends Model {
  readonly id: string = ''
  email: string = ''
  nickname: string = ''
  avatarUrl: string = ''
  readonly createTime: number = 0
  readonly latestUpdate: number = 0
}

export class ConfigOut extends Model {
  readonly id: String = ''
  editMode: boolean = false
  darkMode: boolean = false
  menuReduced: boolean = false
  avatarCircle: boolean = false
}

export class StarOut extends Model {
  readonly id: number = -1
  name: string = ''
  imageUrl: string = ''
  description: string = ''
  readonly createTime: number = 0
  readonly latestUpdate: number = 0
}

export class VideoOut extends Model {
  readonly id: number = -1
  title: string = ''
  videoUrl: string = ''
  description: string = ''
  readonly createTime: number = 0
  readonly latestUpdate: number = 0
}

export class WebsiteOut extends Model {
  readonly id: number = -1
  title: string = ''
  siteUrl: string = ''
  description: string = ''
  readonly createTime: number = 0
  readonly latestUpdate: number = 0
}

export class PageOut<T extends Model = Model> extends Model {
  total: number = -1
  page: number = -1
  content: T[] = []
}

export type InvalidOut = {
  count: number
  errors: {
    name: string
    message: string
  }[]
}
