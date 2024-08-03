<template>
	<xn-form-container
		:title="formData.id ? '编辑简单业务' : '增加简单业务'"
		:width="700"
		v-model:open="open"
		:destroy-on-close="true"
		@close="onClose"
	>
		<a-form ref="formRef" :model="formData" :rules="formRules" layout="vertical">
			<a-form-item label="名称：" name="name">
				<a-input v-model:value="formData.name" placeholder="请输入名称" allow-clear />
			</a-form-item>
			<a-form-item label="编码：" name="code">
				<a-input v-model:value="formData.code" placeholder="请输入编码" allow-clear />
			</a-form-item>
			<a-form-item label="状态：" name="state">
				<a-input v-model:value="formData.state" placeholder="请输入状态" allow-clear />
			</a-form-item>
			<a-form-item label="类型：" name="type">
				<a-select v-model:value="formData.type" placeholder="请选择类型" :options="typeOptions" />
			</a-form-item>
			<a-form-item label="所属组织：" name="orgId">
				<a-input v-model:value="formData.orgId" placeholder="请输入所属组织" allow-clear />
			</a-form-item>
			<a-form-item label="负责人：" name="ponPer">
				<a-input v-model:value="formData.ponPer" placeholder="请输入负责人" allow-clear />
			</a-form-item>
			<a-form-item label="相关人：" name="relPer">
				<a-input v-model:value="formData.relPer" placeholder="请输入相关人" allow-clear />
			</a-form-item>
			<a-form-item label="附件：" name="enclosure">
				<a-input v-model:value="formData.enclosure" placeholder="请输入附件" allow-clear />
			</a-form-item>
			<a-form-item label="备注：" name="ps">
				<a-textarea v-model:value="formData.ps" placeholder="请输入备注" :auto-size="{ minRows: 3, maxRows: 5 }" />
			</a-form-item>
		</a-form>
		<template #footer>
			<a-button style="margin-right: 8px" @click="onClose">关闭</a-button>
			<a-button type="primary" @click="onSubmit" :loading="submitLoading">保存</a-button>
		</template>
	</xn-form-container>
</template>

<script setup name="bizSimpleForm">
	import tool from '@/utils/tool'
	import { cloneDeep } from 'lodash-es'
	import { required } from '@/utils/formRules'
	import bizSimpleApi from '@/api/biz/bizSimpleApi'
	// 抽屉状态
	const open = ref(false)
	const emit = defineEmits({ successful: null })
	const formRef = ref()
	// 表单数据
	const formData = ref({})
	const submitLoading = ref(false)
	const typeOptions = ref([])

	// 打开抽屉
	const onOpen = (record) => {
		open.value = true
		if (record) {
			let recordData = cloneDeep(record)
			formData.value = Object.assign({}, recordData)
		}
		typeOptions.value = tool.dictList('BIZ_TYPE')
	}
	// 关闭抽屉
	const onClose = () => {
		formRef.value.resetFields()
		formData.value = {}
		open.value = false
	}
	// 默认要校验的
	const formRules = {
		name: [required('请输入名称')],
		type: [required('请输入类型')],
	}
	// 验证并提交数据
	const onSubmit = () => {
		formRef.value
			.validate()
			.then(() => {
				submitLoading.value = true
				const formDataParam = cloneDeep(formData.value)
				bizSimpleApi
					.bizSimpleSubmitForm(formDataParam, formDataParam.id)
					.then(() => {
						onClose()
						emit('successful')
					})
					.finally(() => {
						submitLoading.value = false
					})
			})
			.catch(() => {})
	}
	// 抛出函数
	defineExpose({
		onOpen
	})
</script>
