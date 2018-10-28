package com.github.mdevloo.cube.modbus.register.energy.instantaneous;

import com.github.mdevloo.cube.modbus.register.access.RegisterAccess;
import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;
import com.github.mdevloo.cube.modbus.register.scalers.RegisterScaling;

import static com.github.mdevloo.cube.modbus.register.energy.RegisterUtil.REGISTER_OFFSET_MODBUS;

public enum InstantaneousModbusRegister implements ModbusRegister {
    SYSTEM_KW(2816, RegisterScaling.KP),
    SYSTEM_KVA(2817, RegisterScaling.KP),
    SYSTEM_KVAR(2818, RegisterScaling.KP),
    SYSTEM_PF(2819, RegisterScaling.THOUSAND_EQUALS_ONE),
    FREQUENCY(2820, RegisterScaling.FIVE_THOUSAND_EQUALS_FIFTY),
    PHASE_1_VOLTS(2821, RegisterScaling.KVP),
    PHASE_1_AMPS(2822, RegisterScaling.KI),
    PHASE_1_KW(2823, RegisterScaling.KP),
    PHASE_2_VOLTS(2824, RegisterScaling.KVP),
    PHASE_2_AMPS(2825, RegisterScaling.KI),
    PHASE_2_KW(2826, RegisterScaling.KP),
    PHASE_3_VOLTS(2827, RegisterScaling.KVP),
    PHASE_3_AMPS(2828, RegisterScaling.KI),
    PHASE_3_KW(2829, RegisterScaling.KP),
    PHASE_1_PF(2830, RegisterScaling.THOUSAND_EQUALS_ONE),
    PHASE_2_PF(2831, RegisterScaling.THOUSAND_EQUALS_ONE),
    PHASE_3_PF(2832, RegisterScaling.THOUSAND_EQUALS_ONE),
    PH1_PH2_VOLTS(2833, RegisterScaling.KVL),
    PH2_PH3_VOLTS(2834, RegisterScaling.KVL),
    PH3_PH1_VOLTS(2835, RegisterScaling.KVL),
    NEUTRAL_CURRENT(2836, RegisterScaling.KI),
    AMPS_SCALE_KI(2837, RegisterScaling.NONE),
    PHASE_VOLTS_SCALE_KVP(2838, RegisterScaling.NONE),
    LINE_VOLTS_SCALE_KVI(2839, RegisterScaling.NONE),
    POWER_SCALE(2840, RegisterScaling.NONE),
    PHASE_1_KVA(3072, RegisterScaling.KP),
    PHASE_2_KVA(3073, RegisterScaling.KP),
    PHASE_3_KVA(3074, RegisterScaling.KP),
    PHASE_1_KVAR(3075, RegisterScaling.KP),
    PHASE_2_KVAR(3076, RegisterScaling.KP),
    PHASE_3_KVAR(3077, RegisterScaling.KP);

    private final RegisterAccess registerAccess;

    private final int registerValue;

    private final int modbusRegisterValue;

    private final RegisterScaling registerScaling;

    InstantaneousModbusRegister(final int registerValue, final RegisterScaling registerScaling) {
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