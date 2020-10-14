package com.github.mdevloo.cube.modbus.register.energy.harmonic;

import com.github.mdevloo.cube.modbus.register.access.RegisterAccess;
import com.github.mdevloo.cube.modbus.register.energy.Register;
import com.github.mdevloo.cube.modbus.register.scalers.RegisterScaling;

import static com.github.mdevloo.cube.modbus.register.energy.RegisterUtil.REGISTER_OFFSET_MODBUS;

public enum TotalHarmonicDistortionRegister implements Register {
    V1_PERCENTAGE_THD(4352, RegisterScaling.THOUSAND_EQUALS_100_PERCENTAGE),
    V2_PERCENTAGE_THD(4353, RegisterScaling.THOUSAND_EQUALS_100_PERCENTAGE),
    V3_PERCENTAGE_THD(4354, RegisterScaling.THOUSAND_EQUALS_100_PERCENTAGE),
    I1_PERCENTAGE_THD(4355, RegisterScaling.THOUSAND_EQUALS_100_PERCENTAGE),
    I2_PERCENTAGE_THD(4356, RegisterScaling.THOUSAND_EQUALS_100_PERCENTAGE),
    I3_PERCENTAGE_THD(4357, RegisterScaling.THOUSAND_EQUALS_100_PERCENTAGE);

    private final RegisterAccess registerAccess;

    private final int registerValue;

    private final int modbusRegisterValue;

    private final RegisterScaling registerScaling;

    TotalHarmonicDistortionRegister(final int registerValue, final RegisterScaling registerScaling) {
        this.registerValue = registerValue;
        registerAccess = RegisterAccess.READ_WRITE;
        this.registerScaling = registerScaling;
        modbusRegisterValue = REGISTER_OFFSET_MODBUS + this.registerValue;
    }

    @Override
    public final RegisterAccess getRegisterAccess() {
        return registerAccess;
    }

    @Override
    public final int getRegisterValue() {
        return registerValue;
    }

    @Override
    public final int getModbusRegisterValue() {
        return modbusRegisterValue;
    }

    @Override
    public final RegisterScaling getRegisterScaling() {
        return registerScaling;
    }
}