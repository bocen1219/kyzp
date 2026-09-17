import request from '@/config/axios'

export interface UserVO {
  id?: number
  avatar?: string
  birthday?: number
  createTime?: number
  loginDate?: number
  loginIp?: string
  mobile?: string
  email?: string
  name?: string
  nickname?: string
  registerIp?: string
  sex?: number
  status?: number
}

// 查询会员用户列表
export const getUserPage = async (params) => {
  return await request.get<PageResult<UserVO[]>>({ url: `/member/user/page`, params })
}

// 查询会员用户详情
export const getUser = async (id: number) => {
  return await request.get<UserVO>({ url: `/member/user/get?id=` + id })
}

// 修改会员用户
export const updateUser = async (data: UserVO) => {
  return await request.put({ url: `/member/user/update`, data })
}
