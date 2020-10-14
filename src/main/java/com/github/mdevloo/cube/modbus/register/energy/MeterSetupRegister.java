package com.github.mdevloo.cube.modbus.register.energy;

import com.github.mdevloo.cube.modbus.register.access.RegisterAccess;
import com.github.mdevloo.cube.modbus.register.scalers.RegisterScaling;

import static com.github.mdevloo.cube.modbus.register.energy.RegisterUtil.REGISTER_OFFSET_MODBUS;

public enum MeterSetupRegister implements Register {
    CT_PRIMARY(3584, RegisterAccess.READ_WRITE),
    NOMINAL_VOLTS(3585, RegisterAccess.READ_WRITE),
    PULSE_1_RATE(3586, RegisterAccess.READ_WRITE),
    PULSE_2_RATE(3587, RegisterAccess.READ_WRITE),
    BAUD(3588, RegisterAccess.READ_WRITE),
    MODBUS_ID(3589, RegisterAccess.READ_WRITE),
    METER_MODEL(3590, RegisterAccess.READ),
    METER_TYPE(3591, RegisterAccess.READ),
    FIRMWARE_VERSION(3592, RegisterAccess.READ),
    CURRENT_DEMAND_PERIOD(3593, RegisterAccess.READ_WRITE),
    PULSE_ON_TIME(3594, RegisterAccess.READ_WRITE),
    SECURITY_CODE(3595, RegisterAccess.READ_WRITE),
    HOUS_RUN_TRIP_POINT(3596, RegisterAccess.READ_WRITE);

    private final RegisterAccess registerAccess;

    private final int registerValue;

    private final int modbusRegisterValue;

    private final RegisterScaling registerScaling;

    MeterSetupRegister(final int registerValue, final RegisterAccess registerAccess) {
        this.registerValue = registerValue;
        this.registerAccess = registerAccess;
        modbusRegisterValue = REGISTER_OFFSET_MODBUS + this.registerValue;
        registerScaling = RegisterScaling.NONE;
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