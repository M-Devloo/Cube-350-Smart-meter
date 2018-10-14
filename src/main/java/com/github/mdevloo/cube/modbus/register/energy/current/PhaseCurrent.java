package com.github.mdevloo.cube.modbus.register.energy.current;

public enum PhaseCurrent implements Current<Current.CurrentType> {
    PHASE_1(CurrentType.I1),
    PHASE_2(CurrentType.I2),
    PHASE_3(CurrentType.I3);

    private final CurrentType currentType;

    PhaseCurrent(final CurrentType currentType) {
        this.currentType = currentType;
    }

    @Override
    public final CurrentType getCurrentType() {
        return getType();
    }

    @Override
    public CurrentType getType() {
        return currentType;
    }
}