import Http from '@/util/Http';

export default class BaseService {
    request() {
        return new Http();
    }
}