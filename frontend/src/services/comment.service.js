import BaseService from "@/services/base.service";
import {ErrorWrapper} from "@/util/ErrorWrapper";

const COMMENT_API = '/api/comments';

/**
 * We wrap axios Promise into another Promise to get all the errors from server.
 * pass them to actions and handle them appropriately with console message,
 * instead of dropping to console with red error.
 */
class CommentService extends BaseService {

    addComment(comment) {
        return new Promise( (resolve, reject) => {
            return this.request().post(COMMENT_API, comment)
                .then( response => {
                    response.data = { id: comment.taskId };
                    resolve(response.data);
                })
                .catch(error => {reject(new ErrorWrapper(error))})
        });
    }

    getTaskCommentsByTaskId(id) {
        return new Promise( (resolve, reject) => {
            return this.request().get(COMMENT_API + '/' + id)
                .then( response => resolve(response.data))
                .catch(error => {reject(new ErrorWrapper(error))})
        });
    }
}

export default new CommentService();