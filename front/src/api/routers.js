import Rest from '../libs/proton/Rest'

const restTemplate = new Rest('sys-menus')

export const getRouterReq = () => {
  return restTemplate.GET({uri: 'tree'})
}
