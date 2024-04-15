import router from '@/router'

export function query<T extends string>(query: string, defaultValue?: T) {
  const result = router.currentRoute.value.query[query] as T
  if (!result && defaultValue) return defaultValue
  return result
}

export function queryAsInt(query: string, defaultValue?: number): number {
  const result = parseInt(router.currentRoute.value.query[query] as string)
  if (isNaN(result) && defaultValue) return defaultValue
  return result
}
