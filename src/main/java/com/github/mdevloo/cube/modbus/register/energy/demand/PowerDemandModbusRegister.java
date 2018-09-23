package com.github.mdevloo.cube.modbus.register.energy.demand;

import com.github.mdevloo.cube.modbus.register.access.RegisterAccess;
import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;
import com.github.mdevloo.cube.modbus.register.scalers.RegisterScaling;

import static com.github.mdevloo.cube.modbus.register.energy.RegisterUtil.REGISTER_OFFSET_MODBUS;

public enum PowerDemandModbusRegister implements ModbusRegister {
    KW_DEMAND(4608, RegisterScaling.KP_MINUS_ONE),
    KVA_DEMAND(4609, RegisterScaling.KP_MINUS_ONE),
    KVAR_DEMAND(4610, RegisterScaling.KP_MINUS_ONE),
    PEAK_HOLD_KW_DEMAND(4611, RegisterScaling.KP_MINUS_ONE),
    PEAK_HOLD_KVA_DEMAND(4612, RegisterScaling.KP_MINUS_ONE),
    PEAK_HOLD_KVAR_DEMAND(4613, RegisterScaling.KP_MINUS_ONE);

    private final RegisterAccess registerAccess;

    private final int registerValue;

    private final int modbusRegisterValue;

    private final RegisterScaling registerScaling;

    PowerDemandModbusRegister(final int registerValue, final RegisterScaling registerScaling) {
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