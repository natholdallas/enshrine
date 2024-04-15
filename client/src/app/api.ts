import { GenericRequest, SimpleRequest } from './core/api'
import {
  EmailIn,
  type CodeIn,
  type LoginIn,
  type PaginationIn,
  type PasswordIn,
  type RegisterIn,
  type StarIn,
  type VideoIn,
  type WebsiteIn
} from './models-in'
import { ConfigOut, StarOut, UserOut, VideoOut, WebsiteOut } from './models-out'

export class Account {
  static request = new SimpleRequest()

  static $register(data: RegisterIn) {
    return Account.request.post('/register', data)
  }

  static $login(data: LoginIn) {
    return Account.request.post('/login', data)
  }

  static $logout() {
    return Account.request.post('/logout')
  }
}

export class Code {
  static request = new SimpleRequest('/code')

  static $create(data: CodeIn) {
    return Code.request.post(undefined, data)
  }
}

export class User extends UserOut {
  static request = new GenericRequest<User>('/user', User)

  static $retrive() {
    return User.request.$retrieve()
  }

  static $patchEmail(data: EmailIn) {
    return User.request.$patch('/email', data)
  }

  static $patchPassword(data: PasswordIn) {
    return User.request.$patch('/password', data)
  }

  $update() {
    return User.request.$update(undefined, this)
  }

  $destroy() {
    return User.request.$destroy(`/${this.id}`)
  }
}

export class Config extends ConfigOut {
  static request = new GenericRequest<Config>('/config', Config)

  static $retrieve() {
    return Config.request.$retrieve()
  }

  $update() {
    return Config.request.$update(undefined, this)
  }
}

export class Star extends StarOut {
  static request = new GenericRequest<Star>('/star', Star)

  static $list(params?: PaginationIn) {
    return Star.request.$list(undefined, params)
  }

  static $retrieve(id: number) {
    return Star.request.$retrieve(`/${id}`)
  }

  static $create(data: StarIn) {
    return Star.request.$create(undefined, data)
  }

  $update() {
    return Star.request.$update(`/${this.id}`, this)
  }

  $destroy() {
    return Star.request.$destroy(`/${this.id}`)
  }
}

export class Video extends VideoOut {
  static request = new GenericRequest<Video>('/video', Video)

  static $list(params?: PaginationIn) {
    return Video.request.$list(undefined, params)
  }

  static $retrieve(id: number) {
    return Video.request.$retrieve(`/${id}`)
  }

  static $create(data: VideoIn) {
    return Video.request.$create(undefined, data)
  }

  $update() {
    return Video.request.$update(`/${this.id}`, this)
  }

  $destroy() {
    return Video.request.$destroy(`/${this.id}`)
  }
}

export class Website extends WebsiteOut {
  static request = new GenericRequest<Website>('/website', Website)

  static $list(params?: PaginationIn) {
    return Website.request.$list(undefined, params)
  }

  static $retrieve(id: number) {
    return Website.request.$retrieve(`/${id}`)
  }

  static $create(data: WebsiteIn) {
    return Website.request.$create(undefined, data)
  }

  $update() {
    return Website.request.$update(`/${this.id}`, this)
  }

  $destroy() {
    return Website.request.$destroy(`/${this.id}`)
  }
}
