<template>
  <div class="user-avatar-dropdown">
    <Dropdown @on-click="handleClick">
      <Badge :dot="!!messageUnreadCount">
        <Avatar style="color: rgba(254,255,255,0.98);background-color: rgb(80, 90, 110)">{{ userName }}</Avatar>
      </Badge>
      <Icon :size="18" type="md-arrow-dropdown"></Icon>
      <DropdownMenu slot="list">
        <DropdownItem name="changePassword">修改密码</DropdownItem>
        <DropdownItem name="logout">退出登录</DropdownItem>
      </DropdownMenu>
    </Dropdown>

    <Modal v-model="modal" width="556" ok-text="确定" cancel-text="取消" @on-ok="changePasswordSubmit">
      <p slot="header" style="color:#8bbfff;text-align:left">
        <Icon type="ios-information-circle"></Icon>
        <span>修改密码</span>
      </p>
      <div style="height:150px;padding: 5px 50px;">

        <Form label-position="right" :label-width="0" :model="model" :rules="rules" ref="form">
          <FormItem prop="currentPassword">
            <Input v-model="model.currentPassword" ref="currentPassword"
                   placeholder="请输入原密码" type="password" icon="ios-key">
              <span slot="prepend">&nbsp;旧&nbsp;密&nbsp;码&nbsp;</span>
            </Input>
          </FormItem>
          <FormItem prop="newPassword">
            <Input v-model="model.newPassword" ref="newPassword"
                   placeholder="请输入新密码" type="password" icon="ios-key">
              <span slot="prepend">&nbsp;新&nbsp;密&nbsp;码&nbsp;</span>
            </Input>
          </FormItem>
          <FormItem prop="newPassword2">
            <Input v-model="model.newPassword2" ref="newPassword2"
                   placeholder="请再次输入新密码" type="password" icon="ios-key">
              <span slot="prepend">再次输入</span>
            </Input>
          </FormItem>
        </Form>

      </div>
    </Modal>
  </div>
</template>

<script>
    import './user.less'
    import {mapActions} from 'vuex'
    import Rest from '@/libs/proton/Rest'

    export default {
        name: 'User',
        props: {
            userAvatar: {
                type: String,
                default: undefined
            },
            userName: {
                type: String,
                default: ''
            },
            messageUnreadCount: {
                type: Number,
                default: 0
            }
        },
        data() {
            return {
                modal: false,
                modal_loading: false,
                model: {
                    currentPassword: null,
                    newPassword: null,
                    newPassword2: null
                },
                rules: {
                    currentPassword: [
                        {required: true, message: '请填写原密码', trigger: 'change'}
                    ],
                    newPassword: [
                        {required: true, message: '请输入新密码', trigger: 'change'},
                        {
                            validator: (rules, value, callback) => {
                                if (value === this.model.currentPassword) {
                                    callback(new Error('新密码与原密码一致，请重新输入'))
                                } else {
                                    callback()
                                }
                            }
                        }
                    ],
                    newPassword2: [
                        {required: true, message: '请再次输入新密码', trigger: 'change'},
                        {
                            validator: (rules, value, callback) => {
                                if (value !== this.model.newPassword) {
                                    callback(new Error('新密码不一致，请重新输入'))
                                } else {
                                    callback()
                                }
                            }
                        }
                    ]
                }
            };
        },
        methods: {
            ...mapActions([
                'handleLogOut',
                'removeMenuList'
            ]),
            logout() {
                this.handleLogOut().then(() => {
                    localStorage.removeItem('id_token')
                    this.removeMenuList().then(() => {
                    })
                    this.$router.push({
                        name: 'login'
                    })
                })
            },
            message() {
                this.$router.push({
                    name: 'message_page'
                })
            },
            changePassword() {
                this.modal = true
            },
            changePasswordSubmit() {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        let currentPassword = this.model.currentPassword
                        let newPassword = this.model.newPassword
                        new Rest('account/password').POST({
                            data: {currentPassword, newPassword}
                        }).then((res) => {
                            if (res) {
                                this.$Message.success('操作成功')
                                this.$set(this.model, 'currentPassword', undefined)
                                this.$set(this.model, 'newPassword', undefined)
                                this.$set(this.model, 'newPassword2', undefined)
                                this.modal = false;
                            }
                        }, (error) => {
                            const {response: {data: {detail}}} = error
                            this.$Message.error(detail)
                            this.modal = true
                            this.$refs.currentPassword.focus()
                        })
                    }
                })
            },
            handleClick(name) {
                switch (name) {
                    case 'logout':
                        this.logout()
                        break
                    case 'message':
                        this.message()
                        break
                    case 'changePassword':
                        this.changePassword()
                        break
                }
            }
        }
    }
</script>
