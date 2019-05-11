import FormComponent from '@/components/FormComponent';
import {shallowMount} from "@vue/test-utils";
import {FORM_PRIORITY_LOW} from "@/util/constants";

import Vuex from 'vuex';

describe('FormComponent test suit: ------------------------', () => {
    let store;
    let actions;
    let getters;

    beforeEach(() => {
        actions = {
            closeForm: () => {}
        };

        getters = {
            getFormType: () => 'edit',
            getFormItem: () => {},
            getFormOpenStatus: () => false
        };

        store = new Vuex.Store({            // MOCK STORE
            actions,
            getters
        })
    });

    it('should return correct priority text', () => {
        const wrapper = shallowMount(FormComponent, {store});

        let expected = 'Low';
        let actual = wrapper.vm.getPriorityText(FORM_PRIORITY_LOW);

        expect(actual).toBe(expected);
    });

});
