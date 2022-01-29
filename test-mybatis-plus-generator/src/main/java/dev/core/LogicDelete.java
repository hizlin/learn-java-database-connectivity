package dev.core;

public enum LogicDelete {
    NOT(0),
    YES(1);

    LogicDelete(int value) {
        this.value = value;
    }

    private final Integer value;

    public Integer getValue() {
        return this.value;
    }
}
