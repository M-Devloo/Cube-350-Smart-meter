package com.github.mdevloo.cube.modbus.register.energy.peak;

import com.github.mdevloo.cube.modbus.register.access.RegisterAccess;
import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;
import com.github.mdevloo.cube.modbus.register.scalers.RegisterScaling;

import static com.github.mdevloo.cube.modbus.register.energy.RegisterUtil.REGISTER_OFFSET_MODBUS;

public enum PeakHoldCurrentDemandModbusRegister implements ModbusRegister {
    PEAK_PH1_AMPS_DEMAND(3840, RegisterScaling.KI),
    PEAK_PH2_AMPS_DEMAND(3841, RegisterScaling.KI),
    PEAK_PH3_AMPS_DEMAND(3842, RegisterScaling.KI),
    PEAK_PH1_VOLTS_DEMAND(3843, RegisterScaling.KVP),
    PEAK_PH2_VOLTS_DEMAND(3844, RegisterScaling.KVP),
    PEAK_PH3_VOLTS_DEMAND(3845, RegisterScaling.KVP);

    private final RegisterAccess registerAccess;

    private final int registerValue;

    private final int modbusRegisterValue;

    private final RegisterScaling registerScaling;

    PeakHoldCurrentDemandModbusRegister(final int registerValue, final RegisterScaling registerScaling) {
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