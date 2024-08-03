<template>
	<a-card :bordered="false">
		<a-form ref="searchFormRef" name="advanced_search" :model="searchFormState" class="ant-advanced-search-form">
			<a-row :gutter="24">
				<a-col :span="6">
					<a-form-item label="名称" name="name">
						<a-input v-model:value="searchFormState.name" placeholder="请输入名称" />
					</a-form-item>
				</a-col>
				<a-col :span="6">
					<a-form-item label="编码" name="code">
						<a-input v-model:value="searchFormState.code" placeholder="请输入编码" />
					</a-form-item>
				</a-col>
				<a-col :span="6">
					<a-form-item label="状态" name="state">
						<a-input v-model:value="searchFormState.state" placeholder="请输入状态" />
					</a-form-item>
				</a-col>
				<a-col :span="6" v-show="advanced">
					<a-form-item label="类型" name="type">
						<a-select v-model:value="searchFormState.type" placeholder="请选择类型" :options="typeOptions" />
					</a-form-item>
				</a-col>
				<a-col :span="6" v-show="advanced">
					<a-form-item label="负责人" name="ponPer">
						<a-input v-model:value="searchFormState.ponPer" placeholder="请输入负责人" />
					</a-form-item>
				</a-col>
				<a-col :span="6" v-show="advanced">
					<a-form-item label="备注" name="ps">
						<a-input v-model:value="searchFormState.ps" placeholder="请输入备注" />
					</a-form-item>
				</a-col>
				<a-col :span="6">
					<a-button type="primary" @click="tableRef.refresh()">查询</a-button>
					<a-button style="margin: 0 8px" @click="reset">重置</a-button>
					<a @click="toggleAdvanced" style="margin-left: 8px">
						{{ advanced ? '收起' : '展开' }}
						<component :is="advanced ? 'up-outlined' : 'down-outlined'"/>
					</a>
				</a-col>
			</a-row>
		</a-form>
		<s-table
			ref="tableRef"
			:columns="columns"
			:data="loadData"
			:alert="options.alert.show"
			bordered
			:row-key="(record) => record.id"
			:tool-config="toolConfig"
			:row-selection="options.rowSelection"
		>
			<template #operator class="table-operator">
				<a-space>
					<a-button type="primary" @click="formRef.onOpen()" v-if="hasPerm('bizSimpleAdd')">
						<template #icon><plus-outlined /></template>
						新增
					</a-button>
					<xn-batch-delete
						v-if="hasPerm('bizSimpleBatchDelete')"
						:selectedRowKeys="selectedRowKeys"
						@batchDelete="deleteBatchBizSimple"
					/>
				</a-space>
			</template>
			<template #bodyCell="{ column, record }">
				<template v-if="column.dataIndex === 'type'">
					{{ $TOOL.dictTypeData('BIZ_TYPE', record.type) }}
				</template>
				<template v-if="column.dataIndex === 'action'">
					<a-space>
						<a @click="formRef.onOpen(record)" v-if="hasPerm('bizSimpleEdit')">编辑</a>
						<a-divider type="vertical" v-if="hasPerm(['bizSimpleEdit', 'bizSimpleDelete'], 'and')" />
						<a-popconfirm title="确定要删除吗？" @confirm="deleteBizSimple(record)">
							<a-button type="link" danger size="small" v-if="hasPerm('bizSimpleDelete')">删除</a-button>
						</a-popconfirm>
					</a-space>
				</template>
			</template>
		</s-table>
	</a-card>
	<Form ref="formRef" @successful="tableRef.refresh()" />
</template>

<script setup name="simple">
	import tool from '@/utils/tool'
	import { cloneDeep } from 'lodash-es'
	import Form from './form.vue'
	import bizSimpleApi from '@/api/biz/bizSimpleApi'
	const searchFormState = ref({})
	const searchFormRef = ref()
	const tableRef = ref()
	const formRef = ref()
	const toolConfig = { refresh: true, height: true, columnSetting: true, striped: false }
	// 查询区域显示更多控制
	const advanced = ref(false)
	const toggleAdvanced = () => {
		advanced.value = !advanced.value
	}
	const columns = [
		{
			title: '名称',
			dataIndex: 'name',
			ellipsis: true
		},
		{
			title: '编码',
			dataIndex: 'code'
		},
		{
			title: '状态',
			dataIndex: 'state'
		},
		{
			title: '类型',
			dataIndex: 'type'
		},
		{
			title: '所属组织',
			dataIndex: 'orgId'
		},
		{
			title: '负责人',
			dataIndex: 'ponPer'
		},
		{
			title: '相关人',
			dataIndex: 'relPer'
		},
		{
			title: '附件',
			dataIndex: 'enclosure'
		},
		{
			title: '备注',
			dataIndex: 'ps'
		},
	]
	// 操作栏通过权限判断是否显示
	if (hasPerm(['bizSimpleEdit', 'bizSimpleDelete'])) {
		columns.push({
			title: '操作',
			dataIndex: 'action',
			align: 'center',
			width: 150
		})
	}
	const selectedRowKeys = ref([])
	// 列表选择配置
	const options = {
		// columns数字类型字段加入 needTotal: true 可以勾选自动算账
		alert: {
			show: true,
			clear: () => {
				selectedRowKeys.value = ref([])
			}
		},
		rowSelection: {
			onChange: (selectedRowKey, selectedRows) => {
				selectedRowKeys.value = selectedRowKey
			}
		}
	}
	const loadData = (parameter) => {
		const searchFormParam = cloneDeep(searchFormState.value)
		return bizSimpleApi.bizSimplePage(Object.assign(parameter, searchFormParam)).then((data) => {
			return data
		})
	}
	// 重置
	const reset = () => {
		searchFormRef.value.resetFields()
		tableRef.value.refresh(true)
	}
	// 删除
	const deleteBizSimple = (record) => {
		let params = [
			{
				id: record.id
			}
		]
		bizSimpleApi.bizSimpleDelete(params).then(() => {
			tableRef.value.refresh(true)
		})
	}
	// 批量删除
	const deleteBatchBizSimple = (params) => {
		bizSimpleApi.bizSimpleDelete(params).then(() => {
			tableRef.value.clearRefreshSelected()
		})
	}
	const typeOptions = tool.dictList('BIZ_TYPE')
</script>
