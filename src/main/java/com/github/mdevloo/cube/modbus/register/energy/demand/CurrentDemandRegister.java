package com.github.mdevloo.cube.modbus.register.energy.demand;

import com.github.mdevloo.cube.modbus.register.access.RegisterAccess;
import com.github.mdevloo.cube.modbus.register.energy.Register;
import com.github.mdevloo.cube.modbus.register.scalers.RegisterScaling;

import static com.github.mdevloo.cube.modbus.register.energy.RegisterUtil.REGISTER_OFFSET_MODBUS;

public enum CurrentDemandRegister implements Register {
    PH1_AMPS_DEMAND(4096, RegisterScaling.KI),
    PH2_AMPS_DEMAND(4097, RegisterScaling.KI),
    PH3_AMPS_DEMAND(4098, RegisterScaling.KI),
    PH1_VOLTS_DEMAND(4099, RegisterScaling.KVP),
    PH2_VOLTS_DEMAND(4100, RegisterScaling.KVP),
    PH3_VOLTS_DEMAND(4101, RegisterScaling.KVP);

    private final RegisterAccess registerAccess;

    private final int registerValue;

    private final int modbusRegisterValue;

    private final RegisterScaling registerScaling;

    CurrentDemandRegister(final int registerValue, final RegisterScaling registerScaling) {
        this.registerValue = registerValue;
        registerAccess = RegisterAccess.READ;
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