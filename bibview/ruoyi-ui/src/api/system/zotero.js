import request from '@/utils/request'

// 查询文献配置列表
export function listZotero(query) {
  return request({
    url: '/system/zotero/list',
    method: 'get',
    params: query
  })
}

// 查询文献配置详细
export function getZotero(key) {
  return request({
    url: '/system/zotero/' + key,
    method: 'get'
  })
}

// 新增文献配置
export function addZotero(data) {
  return request({
    url: '/system/zotero',
    method: 'post',
    data: data
  })
}

// 修改文献配置
export function updateZotero(data) {
  return request({
    url: '/system/zotero',
    method: 'put',
    data: data
  })
}

// 删除文献配置
export function delZotero(key) {
  return request({
    url: '/system/zotero/' + key,
    method: 'delete'
  })
}
