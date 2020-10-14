package com.github.mdevloo.cube.modbus.register.energy;

import com.github.mdevloo.cube.modbus.register.access.RegisterAccess;
import com.github.mdevloo.cube.modbus.register.scalers.RegisterScaling;

import static com.github.mdevloo.cube.modbus.register.energy.RegisterUtil.REGISTER_OFFSET_MODBUS;

public enum EnergyModbusRegister implements ModbusRegister {
    ESCALE_HIGH_WORD(512, RegisterAccess.READ),
    ESCALE_LOW_WORD(513, RegisterAccess.READ),
    KWH_HIGH_WORD(514, RegisterAccess.READ_WRITE),
    KWH_LOW_WORD(515, RegisterAccess.READ_WRITE),
    KVAH_HIGH_WORD(516, RegisterAccess.READ_WRITE),
    KVAH_LOW_WORD(517, RegisterAccess.READ_WRITE),
    KVARH_INDUCTIVE_HIGH_WORD(518, RegisterAccess.READ_WRITE),
    KVARH_INDUCTIVE_LOW_WORD(519, RegisterAccess.READ_WRITE),
    KVARH_CAPACITIVE_HIGH_WORD(520, RegisterAccess.READ_WRITE),
    KVARH_CAPACITIVE_LOW_WORD(521, RegisterAccess.READ_WRITE),
    IMPORT_KVARH_HIGH_WORD(522, RegisterAccess.READ_WRITE),
    IMPORT_KVARH_LOW_WORD(523, RegisterAccess.READ_WRITE),
    EXPORT_KWH_HIGH_WORD(524, RegisterAccess.READ_WRITE),
    EXPORT_KWH_LOW_WORD(525, RegisterAccess.READ_WRITE),
    EXPORT_KVARH_HIGH_WORD(526, RegisterAccess.READ_WRITE),
    EXPORT_KVARH_LOW_WORD(527, RegisterAccess.READ_WRITE),
    HOURS_RUN_HIGH_WORD(528, RegisterAccess.READ_WRITE),
    HOURS_RUN_LOW_WORD(529, RegisterAccess.READ_WRITE);

    private final int registerValue;

    private final RegisterAccess registerAccess;

    private final int modbusRegisterValue;

    private final RegisterScaling registerScaling;

    EnergyModbusRegister(final int registerValue, final RegisterAccess registerAccess) {
        this.registerValue = registerValue;
        this.registerAccess = registerAccess;
        registerScaling = RegisterScaling.NONE;
        modbusRegisterValue = REGISTER_OFFSET_MODBUS + this.registerValue;
    }

    @Override
    public final int getRegisterValue() {
        return registerValue;
    }

    @Override
    public final RegisterAccess getRegisterAccess() {
        return registerAccess;
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