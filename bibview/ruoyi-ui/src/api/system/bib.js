import request from '@/utils/request'

// 查询文献管理列表
export function listBib(query) {
  return request({
    url: '/system/bib/list',
    method: 'get',
    params: query
  })
}

// 查询文献管理详细
export function getBib(id) {
  return request({
    url: '/system/bib/' + id,
    method: 'get'
  })
}

// 新增文献管理
export function addBib(data) {
  return request({
    url: '/system/bib',
    method: 'post',
    data: data
  })
}

// 修改文献管理
export function updateBib(data) {
  return request({
    url: '/system/bib',
    method: 'put',
    data: data
  })
}

// 删除文献管理
export function delBib(id) {
  return request({
    url: '/system/bib/' + id,
    method: 'delete'
  })
}

//同步文献
export function syncBib(data) {
  return request({
    url: '/system/bib/sync',
    method: 'post',
    data: data
  })
}
