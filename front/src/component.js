import Vue from 'vue'
const pool = {}
import SysDepartment from '_v/system/Department/index.vue'
import * as SysDepartmentMeta from '_v/system/Department/meta.js'
Vue.component('c-sys-departments', SysDepartment)
pool['c-sys-departments'] = {
  __file: SysDepartment,
  meta: SysDepartmentMeta
}

import SysPermission from '_v/system/Permission/index.vue'
import * as SysPermissionMeta from '_v/system/Permission/meta.js'
Vue.component('c-sys-permissions', SysPermission)
pool['c-sys-permissions'] = {
  __file: SysPermission,
  meta: SysPermissionMeta
}

import SysRolePermission from '_v/system/RolePermission/index.vue'
import * as SysRolePermissionMeta from '_v/system/RolePermission/meta.js'
Vue.component('c-sys-role-permissions', SysRolePermission)
pool['c-sys-role-permissions'] = {
  __file: SysRolePermission,
  meta: SysRolePermissionMeta
}

import SysRole from '_v/system/Role/index.vue'
import * as SysRoleMeta from '_v/system/Role/meta.js'
Vue.component('c-sys-roles', SysRole)
pool['c-sys-roles'] = {
  __file: SysRole,
  meta: SysRoleMeta
}

import SysMenu from '_v/system/Menu/index.vue'
import * as SysMenuMeta from '_v/system/Menu/meta.js'
Vue.component('c-sys-menus', SysMenu)
pool['c-sys-menus'] = {
  __file: SysMenu,
  meta: SysMenuMeta
}

import SysUser from '_v/system/User/index.vue'
import * as SysUserMeta from '_v/system/User/meta.js'
Vue.component('c-sys-users', SysUser)
pool['c-sys-users'] = {
  __file: SysUser,
  meta: SysUserMeta
}

import SysScheduleJob from '_v/system/ScheduleJob/index.vue'
import * as SysScheduleJobMeta from '_v/system/ScheduleJob/meta.js'
Vue.component('c-sys-schedule-jobs', SysScheduleJob)
pool['c-sys-schedule-jobs'] = {
  __file: SysScheduleJob,
  meta: SysScheduleJobMeta
}

import SysScheduleJobLog from '_v/system/ScheduleJobLog/index.vue'
import * as SysScheduleJobLogMeta from '_v/system/ScheduleJobLog/meta.js'
Vue.component('c-sys-schedule-job-logs', SysScheduleJobLog)
pool['c-sys-schedule-job-logs'] = {
  __file: SysScheduleJobLog,
  meta: SysScheduleJobLogMeta
}

import SysAuditEventLog from '_v/system/AuditEventLog/index.vue'
import * as SysAuditEventLogMeta from '_v/system/AuditEventLog/meta.js'
Vue.component('c-sys-audit-event-logs', SysAuditEventLog)
pool['c-sys-audit-event-logs'] = {
  __file: SysAuditEventLog,
  meta: SysAuditEventLogMeta
}

import SysSystemLog from '_v/system/SystemLog/index.vue'
import * as SysSystemLogMeta from '_v/system/SystemLog/meta.js'
Vue.component('c-sys-system-logs', SysSystemLog)
pool['c-sys-system-logs'] = {
  __file: SysSystemLog,
  meta: SysSystemLogMeta
}


export default pool