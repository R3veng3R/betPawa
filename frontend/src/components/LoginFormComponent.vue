<template>
    <div class="login-wrapper">
        <div class="form">
            <h4><i class="fas fa-user"></i> Login:</h4>
            <input type="email" :class="{error: hasError}" v-model="email" :placeholder="emailPlaceholder"/>
            <div class="error-block" :class="{error: hasError}"> {{ emailErrorMsg }} </div>
            <a class="login-button button" @click.prevent="login">{{ loginBtnString }}</a>
        </div>
    </div>
</template>

<script>
    import {mapActions} from 'vuex';

    export default {
        name: "LoginFormComponent",
        data() {
            return {
                loginBtnString: 'Login',
                emailPlaceholder: 'Email',
                emailErrorMsg: 'Must not be empty',
                email: '',
                hasError: false
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

            login() {
                if(this.email.length === 0) {
                    this.hasError = true;
                    return;
                }

                this.auth(this.email)
                    .then( user => this.$cookies.set("user", user));
            },

            ...mapActions(['setUser', 'auth'])
        }
    }
</script>

<style scoped lang="scss">
    @import '../scss/variables';

    .login-wrapper {
        background-color: whitesmoke;
        width: 320px;
        min-height: 100px;
        border-radius: 5px;
        margin: 50% auto;
        box-shadow: 0 9px 50px hsla(59, 54%, 82%, 0.31);
        padding: 15px;

        .form {
            margin: 0 auto;
            width: 75%;
            text-align: center;

            h4 {
                text-align: left;
                font-size: 20px;
                margin: 5px 0px -8px;
                color: darken(whitesmoke, 30%);
            }

            input {
                width: 100%;
                box-sizing: border-box;
                border: 1px solid darken(whitesmoke, 10%);
                border-radius: 5px;
                padding: 10px 18px;
                transition: border 0.5s ease-in-out;
                font-size: 15px;
                margin-top: 15px;

                &.error {
                    border: 1px solid red;
                }
            }

            .button{
                width: 100%;
                text-align: center;
                font-size: 15px;
                border-radius: 10px;

                &:hover {
                    box-shadow: 4px 3px 21px 0px rgba(217,151,178,1);
                }
            }

            .error-block {
                color: red;
                font-size: 15px;
                margin-bottom: 10px;
                visibility: hidden;

                &.error {
                    visibility: visible;
                }
            }
        }
    }
</style>