# 康养招牌管理系统（kyzp）

基于 [ruoyi-vue-pro](https://github.com/YunaiV/ruoyi-vue-pro) v2026.08 裁剪而来的基础脚手架：只保留「系统功能 + 基础设施」两个通用模块，并已完成一键改包、项目改名和单租户化。

## 技术栈

| 分类 | 选型 |
| --- | --- |
| 后端 | JDK 21、Spring Boot 3.5.15、MyBatis Plus、Druid、Redis + Redisson |
| 数据库 | MySQL 8（`sql/mysql/` 下提供建表与初始化数据脚本） |
| 前端 | Vue 3、Vite、TypeScript、element-plus |
| 服务端口 | 后端 `48080`，前端 `80` |

## 目录结构

```
kyzp/
├── kyzp-dependencies   # Maven 依赖版本管理（bom）
├── kyzp-framework      # 技术组件：mybatis、redis、web、security、websocket、mq、job ...
├── kyzp-server         # 启动模块，打包含全部依赖的 jar
├── kyzp-module-system  # 系统功能：用户、角色、菜单、部门、字典、短信、邮件、OAuth2 ...
├── kyzp-module-infra   # 基础设施：代码生成、定时任务、文件、配置、API 日志、监控 ...
├── kyzp-module-member  # 会员：手机号登录、微信小程序登录与绑定、会员列表
├── kyzp-ui-admin-vue3  # 管理后台前端
├── sql/mysql/          # MySQL 初始化脚本（kyzp.sql、quartz.sql）
└── script/             # docker、jenkins 部署脚本
```

## 快速开始

### 1. 初始化数据库

```bash
mysql -uroot -p -e "CREATE DATABASE kyzp DEFAULT CHARACTER SET utf8mb4;"
mysql -uroot -p kyzp < sql/mysql/kyzp.sql
mysql -uroot -p kyzp < sql/mysql/quartz.sql
```

默认连接配置见 `kyzp-server/src/main/resources/application-local.yaml`（`127.0.0.1:3306/kyzp`，`root/123456`），Redis 默认 `127.0.0.1:6379` 的 `0` 库。改动连接配置后记得清空 Redis 缓存。

### 2. 启动后端

```bash
mvn clean install -DskipTests -T 1C
java -jar kyzp-server/target/kyzp-server.jar
```

> 需要 JDK 21。

### 3. 启动前端

```bash
cd kyzp-ui-admin-vue3
pnpm install
pnpm dev        # 默认 http://localhost:80
```

默认账号：`admin` / `admin123`。

## 与上游完整版的差异

**保留模块**：`system`、`infra`、`member`。

**已移除模块**：bpm、report、mp、pay、mall、crm、erp、iot、mes、wms、hrm、fms、pms、im、ai。

**会员模块只保留登录相关能力**：

| 保留 | 内容 |
| --- | --- |
| 登录接口 | 手机 + 密码登录、手机 + 验证码登录、发送/校验短信验证码、社交快捷登录、微信小程序一键登录、微信 JSAPI 签名、登出、刷新令牌 |
| 绑定接口 | 社交绑定 / 解绑、获得社交用户、微信小程序码、订阅模板列表 |
| app 账号 | 个人信息、更新资料、修改密码、修改手机号、微信授权换手机号、重置密码 |
| 后台 | 会员列表（查询、编辑） |

已去掉的会员能力：地址、等级/经验、积分、签到、标签、分组、会员配置、对外 API、用户创建 MQ 消息。`member_user` 表也只保留 `mobile`、`password`、`status`、`nickname`、`avatar`、`email`、`name`、`sex`、`birthday`、`register_ip`、`register_terminal`、`login_ip`、`login_date` 这些字段。微信小程序绑定复用 `system_social_user` / `system_social_user_bind` / `system_social_client` 三张表。

**多租户**：采用「停用」而不是删除，随时可以恢复。

| 位置 | 处理方式 |
| --- | --- |
| 后端 | `kyzp.tenant.enable: false`。`YudaoTenantAutoConfiguration` 上是 `@ConditionalOnProperty`，关闭后租户的 AOP、DB 拦截器、Filter、缓存、MQ、定时任务全部不装配 |
| 前端 | `.env` 中 `VITE_APP_TENANT_ENABLE=false`。登录页租户选择框、`tenant-id` 请求头、顶部租户切换组件都不再展示 |
| 菜单 | `system_menu` 中租户菜单与已移除模块的菜单**全部保留**，只是置为 `status=1`（关闭）且 `visible=0`（不可见），不会出现在侧边栏 |
| 数据表 | `system_tenant`、`system_tenant_package` 以及各表的 `tenant_id` 列全部保留 |

如需重新启用多租户：把上面两个开关改回 `true`，并将对应菜单的 `status` / `visible` 恢复为 `0` / `1`。

## 许可

沿用上游的 [MIT License](LICENSE)。
