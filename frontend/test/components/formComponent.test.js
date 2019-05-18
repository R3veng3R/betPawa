import FormComponent from '@/components/FormComponent';
import {shallowMount} from "@vue/test-utils";
import {FORM_PRIORITY_LOW} from "@/util/constants";

import Vuex from 'vuex';

describe('FormComponent test suit: ------------------------', () => {
    let store;
    let actions;
    let getters;
    let wrapper;

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
        });

        wrapper = shallowMount(FormComponent, {store});
    });

    it('should return correct priority text', () => {
        let actual = wrapper.vm.getPriorityText(FORM_PRIORITY_LOW);
        expect(actual).toBe('Low');
    });

    it('should return correct format for last name', () => {
        let lastName = 'Smith';
        let actual = wrapper.vm.formatLastName(lastName);

        expect(actual).toBe('S');
    });
});
