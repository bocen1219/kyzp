<template>
  <ContentWrap>
    <!-- 搜索工作栏 -->
    <el-form
      ref="queryFormRef"
      :inline="true"
      :model="queryParams"
      class="-mb-15px"
      label-width="68px"
    >
      <el-form-item label="用户昵称" prop="nickname">
        <el-input
          v-model="queryParams.nickname"
          class="!w-240px"
          clearable
          placeholder="请输入用户昵称"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input
          v-model="queryParams.mobile"
          class="!w-240px"
          clearable
          placeholder="请输入手机号"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          class="!w-240px"
          clearable
          placeholder="请输入邮箱"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="注册时间" prop="createTime">
        <el-date-picker
          v-model="queryParams.createTime"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-240px"
          end-placeholder="结束日期"
          start-placeholder="开始日期"
          type="daterange"
          value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item label="登录时间" prop="loginDate">
        <el-date-picker
          v-model="queryParams.loginDate"
          :default-time="[new Date('1 00:00:00'), new Date('1 23:59:59')]"
          class="!w-240px"
          end-placeholder="结束日期"
          start-placeholder="开始日期"
          type="daterange"
          value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery">
          <Icon class="mr-5px" icon="ep:search" />
          搜索
        </el-button>
        <el-button @click="resetQuery">
          <Icon class="mr-5px" icon="ep:refresh" />
          重置
        </el-button>
      </el-form-item>
    </el-form>
  </ContentWrap>

  <!-- 列表 -->
  <ContentWrap>
    <el-table v-loading="loading" :data="list" :show-overflow-tooltip="true" :stripe="true">
      <el-table-column align="center" label="用户编号" prop="id" width="120px" />
      <el-table-column align="center" label="头像" prop="avatar" width="80px">
        <template #default="scope">
          <img :src="scope.row.avatar" style="width: 40px" />
        </template>
      </el-table-column>
      <el-table-column align="center" label="手机号" prop="mobile" width="120px" />
      <el-table-column align="center" label="邮箱" prop="email" width="180px" />
      <el-table-column align="center" label="昵称" prop="nickname" width="120px" />
      <el-table-column align="center" label="真实名字" prop="name" width="120px" />
      <el-table-column align="center" label="状态" prop="status" width="100px">
        <template #default="scope">
          <dict-tag :type="DICT_TYPE.COMMON_STATUS" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column
        :formatter="dateFormatter"
        align="center"
        label="登录时间"
        prop="loginDate"
        width="180px"
      />
      <el-table-column
        :formatter="dateFormatter"
        align="center"
        label="注册时间"
        prop="createTime"
        width="180px"
      />
      <el-table-column align="center" fixed="right" label="操作" width="100px">
        <template #default="scope">
          <el-button
            v-hasPermi="['member:user:update']"
            link
            type="primary"
            @click="openForm('update', scope.row.id)"
          >
            编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <Pagination
      v-model:limit="queryParams.pageSize"
      v-model:page="queryParams.pageNo"
      :total="total"
      @pagination="getList"
    />
  </ContentWrap>

  <!-- 表单弹窗：修改 -->
  <UserForm ref="formRef" @success="getList" />
</template>
<script lang="ts" setup>
import { dateFormatter } from '@/utils/formatTime'
import * as UserApi from '@/api/member/user'
import { DICT_TYPE } from '@/utils/dict'
import UserForm from './UserForm.vue'

defineOptions({ name: 'MemberUser' })

const loading = ref(true) // 列表的加载中
const total = ref(0) // 列表的总页数
const list = ref<UserApi.UserVO[]>([]) // 列表的数据
const queryParams = reactive({
  pageNo: 1,
  pageSize: 10,
  nickname: undefined as string | undefined,
  mobile: undefined as string | undefined,
  email: undefined as string | undefined,
  loginDate: [],
  createTime: []
})
const queryFormRef = ref() // 搜索的表单

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const data = await UserApi.getUserPage(queryParams)
    list.value = data.list
    total.value = data.total
  } finally {
    loading.value = false
  }
}

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.pageNo = 1
  getList()
}

/** 重置按钮操作 */
const resetQuery = () => {
  queryFormRef.value.resetFields()
  handleQuery()
}

/** 添加/修改操作 */
const formRef = ref()
const openForm = (type: string, id?: number) => {
  formRef.value.open(type, id)
}

/** 初始化 **/
onMounted(() => {
  getList()
})
</script>
