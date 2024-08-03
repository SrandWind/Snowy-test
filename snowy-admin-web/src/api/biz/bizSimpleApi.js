import { baseRequest } from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/biz/simple/` + url, ...arg)

/**
 * 简单业务Api接口管理器
 *
 * @author wsk
 * @date  2024/08/02 15:16
 **/
export default {
	// 获取简单业务分页
	bizSimplePage(data) {
		return request('page', data, 'get')
	},
	// 提交简单业务表单 edit为true时为编辑，默认为新增
	bizSimpleSubmitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除简单业务
	bizSimpleDelete(data) {
		return request('delete', data)
	},
	// 获取简单业务详情
	bizSimpleDetail(data) {
		return request('detail', data, 'get')
	}
}
