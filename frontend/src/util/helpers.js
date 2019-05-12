/**
 * Create instant, which represent error object
 * @param {Object} [error] - axios error object
 * @param {String} [message] - custom message to display
 */
export class ErrorWrapper {
    constructor (error) {
        this.name = 'ErrorWrapper';
        this.success = error.response ? error.response.data.success : false;
        this.status = error.response ? error.response.status : false;
        this.statusMessage = error.response.statusText;
        this.message = this._getStatusMessage(this.status);
    }

    _getStatusMessage (status) {
        let message = '';

        switch (status) {
            case 400:
                message = 'Bad Request';
                break;
            case 401:
                message = 'Need auth';
                break;
            case 404:
                message = 'Not found';
                break;
            case 503:
            case 504:
                message = 'Service is Unavailable';
                break;
            default:
                message = 'Something wrong. Contact your local administrator';
                break
        }

        return message;
    }
}