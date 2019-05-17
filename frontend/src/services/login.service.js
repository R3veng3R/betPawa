import BaseService from "@/services/base.service";
import {ErrorWrapper} from "@/util/ErrorWrapper";

const LOGIN_API = '/api/login';
const REGISTER_API = '/api/login/register';

class LoginService extends BaseService {
    authenticate(email) {
        return new Promise( (resolve, reject) => {
            return this.request().post(LOGIN_API, {email: email})
                .then( response => {resolve(response.data);})
                .catch(error => {reject(new ErrorWrapper(error))})
        });
    }

    register(user) {
        return new Promise( (resolve, reject) => {
            return this.request().post(REGISTER_API, user)
                .then( response => {resolve(response.data);})
                .catch(error => {reject(new ErrorWrapper(error))})
        });
    }
}

export default new LoginService();