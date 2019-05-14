import BaseService from "@/services/base.service";
import {ErrorWrapper} from "@/util/helpers";

const COMMENT_API = '/api/comments';

class CommentService extends BaseService {

    addComment(comment) {
        return new Promise( (resolve, reject) => {
            return this.request().post(COMMENT_API, comment)
                .then( response => resolve(response.data))
                .catch(error => {reject(new ErrorWrapper(error))})
        });
    }
}

export default new CommentService();