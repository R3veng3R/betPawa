import state from './user-state'
import mutations from './user-mutations'
import getters from './user-getters'
import actions from './user-actions'

export default {
    namespaced: true,
    state,
    mutations,
    getters,
    actions
}