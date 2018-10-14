package com.github.mdevloo.cube.modbus.register.energy.peak;

import com.github.mdevloo.cube.modbus.register.access.RegisterAccess;
import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;
import com.github.mdevloo.cube.modbus.register.scalers.RegisterScaling;

import static com.github.mdevloo.cube.modbus.register.energy.RegisterUtil.REGISTER_OFFSET_MODBUS;

public enum PeakHoldModbusRegister implements ModbusRegister {
    PEAK_HOLD_PH1_AMPS(3328, RegisterScaling.KI, RegisterAccess.READ_WRITE),
    PEAK_HOLD_PH2_AMPS(3329, RegisterScaling.KI, RegisterAccess.READ_WRITE),
    PEAK_HOLD_PH3_AMPS(3330, RegisterScaling.KI, RegisterAccess.READ_WRITE),
    PEAK_HOLD_PH1_VOLTS(3331, RegisterScaling.KVP, RegisterAccess.READ_WRITE),
    PEAK_HOLD_PH2_VOLTS(3332, RegisterScaling.KVP, RegisterAccess.READ_WRITE),
    PEAK_HOLD_PH3_VOLTS(3333, RegisterScaling.KVP, RegisterAccess.READ_WRITE),
    PEAK_HOLD_KW_DEMAND(3334, RegisterScaling.KP_MINUS_ONE, RegisterAccess.READ_WRITE),
    KW_DEMAND_PERIOD(3335, RegisterScaling.ONE_TO_SIXTY_MINUTES, RegisterAccess.READ_WRITE),
    KW_DEMAND(3336, RegisterScaling.KP_MINUS_ONE, RegisterAccess.READ),
    KVA_DEMAND(3337, RegisterScaling.KP_MINUS_ONE, RegisterAccess.READ),
    PEAK_HOLD_KVA_DEMAND(3338, RegisterScaling.KP_MINUS_ONE, RegisterAccess.READ_WRITE);

    private final RegisterAccess registerAccess;

    private final int registerValue;

    private final int modbusRegisterValue;

    private final RegisterScaling registerScaling;

    PeakHoldModbusRegister(final int registerValue, final RegisterScaling registerScaling, final RegisterAccess registerAccess) {
        this.registerValue = registerValue;
        this.registerAccess = registerAccess;
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