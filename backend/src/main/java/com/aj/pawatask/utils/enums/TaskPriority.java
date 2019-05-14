package com.aj.pawatask.utils.enums;

public enum TaskPriority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private final String text;

    TaskPriority(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
