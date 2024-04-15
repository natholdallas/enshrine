import Cookies from 'js-cookie'

export type VerifyFunc = () => boolean

export const isLogin: VerifyFunc = () => !!Cookies.get('token')
