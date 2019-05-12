package com.aj.pawatask.utils.enums;

import com.aj.pawatask.utils.Constants;

public enum TaskPriority {
    LOW(Constants.TASK_PRIORITY_LOW),
    MEDIUM(Constants.TASK_PRIORITY_MEDIUM),
    HIGH(Constants.TASK_PRIORITY_HIGH);

    private final String text;

    TaskPriority(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
