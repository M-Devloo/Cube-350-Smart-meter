package com.github.mdevloo.cube.modbus.register.energy.voltage;

public enum PhaseVoltage implements Voltage<Voltage.VoltageType> {
    PHASE_1(VoltageType.V1),
    PHASE_2(VoltageType.V2),
    PHASE_3(VoltageType.V3);

    private final VoltageType voltageType;

    PhaseVoltage(final VoltageType voltageType) {
        this.voltageType = voltageType;
    }

    @Override
    public final VoltageType getType() {
        return voltageType;
    }

    @Override
    public final VoltageType getVoltageType() {
        return getType();
    }
}