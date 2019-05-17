export const FORM_TYPE_EDIT = 'EDIT';
export const FORM_TYPE_READ = 'READ';
export const FORM_TYPE_CREATE = 'CREATE';

export const FORM_PRIORITY_LOW = 'LOW';
export const FORM_PRIORITY_HIGH = 'HIGH';
export const FORM_PRIORITY_MEDIUM = 'MEDIUM';

export const DATE_FORMAT = 'DD/MM/YYYY';
export const DB_DATE_FORMAT = 'YYYY-MM-DD';

export const ERROR_WRAPPER_NAME = 'ErrorWrapper';

export const FORM_PRIORITY_LIST = [
    {value: FORM_PRIORITY_LOW, label: 'Low'},
    {value: FORM_PRIORITY_HIGH, label: 'High'},
    {value: FORM_PRIORITY_MEDIUM, label: 'Medium'},
];

export const DEFAULT_TASK_OBJ = {
    title: '',
    description: '',
    dueDate: new Date(),
    priority: FORM_PRIORITY_MEDIUM
};