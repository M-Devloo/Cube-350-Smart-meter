package com.github.mdevloo.cube.modbus.register.scalers;

public enum RegisterScaling {
    NONE(1),
    ONE(1000),
    KI(2000),
    KVP(2000),
    KVL(4000),
    KP(4000),
    THOUSAND_EQUALS_ONE(1),
    FIVE_THOUSAND_EQUALS_FIFTY(10),
    KP_MINUS_ONE(KP.getScalingValue() - 1),
    ONE_TO_SIXTY_MINUTES(0),
    THOUSAND_EQUALS_100_PERCENTAGE(1);

    private final int scalingValue;

    RegisterScaling(final int scalingValue) {
        this.scalingValue = scalingValue;
    }

    public final int getScalingValue() {
        return scalingValue;
    }
}