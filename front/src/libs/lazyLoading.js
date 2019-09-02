export default (url, leaf, parent) => {
  if (!leaf) {
    if (!parent) {
      return () => import('@/components/main')
    } else {
      return () => import('@/components/parent-view')
    }
  } else {
    return () => import(`_v/${url}.vue`)
  }
}

