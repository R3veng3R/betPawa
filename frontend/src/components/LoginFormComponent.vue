<template>
    <div class="login-wrapper">
        <div class="form">
            <h4><i class="fas fa-user"></i> Login:</h4>

            <template v-if="!isRegisterForm">
                <input type="email" :class="{error: hasError}" v-model="userForm.email" :placeholder="emailPlaceholder"/>
                <div class="error-block" :class="{error: hasError}"> {{ emailErrorMsg }} </div>
                <a class="login-button button" @click.prevent="login">{{ loginBtnString }}</a>
                <a class="register-link" href="#" @click.prevent="goRegisterForm"> Register </a>
            </template>

            <template v-else>
                <input type="email" v-model="userForm.email" :placeholder="emailPlaceholder" />
                <input type="text" v-model="userForm.name" :placeholder="namePlaceholder" />
                <input type="text" v-model="userForm.lastName" :placeholder="lastNamePlaceholder" />

                <a class="register-button button" @click.prevent="registerUser">{{ registerBtnString }}</a>
                <a class="register-link" href="#" @click.prevent="goBack"> Go back </a>
            </template>
        </div>
    </div>
</template>

<script>
    import {mapActions} from 'vuex';
    import {ERROR_WRAPPER_NAME} from "@/util/constants";

    export default {
        name: "LoginFormComponent",
        data() {
            return {
                loginBtnString: 'Login',
                registerBtnString: 'Register',

                emailPlaceholder: 'Email',
                namePlaceholder: 'Name',
                lastNamePlaceholder: 'Last Name',

                emailMustNotBeEmpty: 'Email must not be empty',
                emailErrorMsg: '',

                hasError: false,
                isRegisterForm: false,

                userForm: {
                    email: '',
                    name: '',
                    lastName: ''
                }
            }
        },

        created() {
            this.setupUser();
        },

        methods: {
            setupUser() {
                let user = this.$cookies.get("user");

                if (user) {
                    this.setUser(user);
                    this.$cookies.set("user", user);
                }
            },

            setErrorMsg(message) {
                this.hasError = true;
                this.emailErrorMsg = message;
            },

            goRegisterForm() {
                this.isRegisterForm = true;
            },

            goBack() {
                this.isRegisterForm = false;
            },

            login() {
                if(this.userForm.email.length === 0) {
                    this.setErrorMsg(this.emailMustNotBeEmpty);
                    return;
                }

                this.auth(this.userForm.email)
                    .then( responseObject => {
                        if( responseObject.name === ERROR_WRAPPER_NAME) {
                            this.setErrorMsg(responseObject.message);
                            return;
                        }

                        this.$cookies.set("user", responseObject)
                    });
            },

            registerUser(){
                if (this.isEmptyUserForm()) { return; }
                this.register(this.userForm);
            },

            isEmptyUserForm() {
                return this.userForm.email.length === 0 || this.userForm.name.length === 0 || this.userForm.lastName.length === 0;
            },

            ...mapActions(['setUser', 'auth', 'register'])
        }
    }
</script>

<style scoped lang="scss">
    @import '../scss/login-form';
</style>