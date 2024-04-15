import { Model } from './core/models'
import { validateEmail, validateEquals, validatePassword, validateRange, validateUrl } from './validator'

export class RegisterIn extends Model {
  email: string = ''
  code: string = ''
  password: string = ''
  confirmPassword: string = ''

  $validations = {
    email: validateEmail(),
    username: validateRange(4, 20),
    password: validatePassword(),
    confirmPassword: validateEquals('password')
  } as const
}

export class LoginIn extends Model {
  email: string = ''
  password: string = ''

  $validations = {
    email: validateEmail(),
    password: validatePassword()
  } as const
}

export class CodeIn extends Model {
  email: string = ''

  $validations = {
    email: validateEmail()
  } as const
}

export class EmailIn extends Model {
  code: string = ''
  email: string = ''

  $validations = {
    email: validateEmail()
  } as const
}

export class PasswordIn extends Model {
  code: string = ''
  password: string = ''

  $validations = {
    password: validatePassword()
  } as const
}

export class ConfigIn extends Model {
  editMode: boolean = false
  darkMode: boolean = false
  menuReduced: boolean = false
  avatarCircle: boolean = false
}

export class StarIn extends Model {
  name: string = ''
  imageUrl: string = ''
  description: string = ''

  $validations = {
    name: validateRange(0, 100),
    description: validateRange(0, 200)
  } as const
}

export class VideoIn extends Model {
  title: string = ''
  videoUrl: string = ''
  description: string = ''

  $validations = {
    title: validateRange(0, 100),
    description: validateRange(0, 200)
  } as const
}

export class WebsiteIn extends Model {
  title: string = ''
  siteUrl: string = ''
  description: string = ''

  $validations = {
    title: validateRange(0, 100),
    siteUrl: validateUrl(),
    description: validateRange(0, 200)
  } as const
}

export class PaginationIn extends Model {
  page: number = 1
  size: number = 20
}
