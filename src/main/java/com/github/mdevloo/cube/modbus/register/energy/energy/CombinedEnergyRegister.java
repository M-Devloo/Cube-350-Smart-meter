package com.github.mdevloo.cube.modbus.register.energy.energy;

import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;

public enum CombinedEnergyRegister implements CombinedModbusRegister {
    ESCALE(EnergyModbusRegister.ESCALE_HIGH_WORD, EnergyModbusRegister.ESCALE_LOW_WORD),
    KWH(EnergyModbusRegister.KWH_HIGH_WORD, EnergyModbusRegister.KWH_LOW_WORD),
    KVAH(EnergyModbusRegister.KVAH_HIGH_WORD, EnergyModbusRegister.KVAH_LOW_WORD),
    KVARH_INDUCTIVE(EnergyModbusRegister.KVARH_INDUCTIVE_HIGH_WORD, EnergyModbusRegister.KVARH_INDUCTIVE_LOW_WORD),
    KVARH_CAPACITIVE(EnergyModbusRegister.KVARH_CAPACITIVE_HIGH_WORD, EnergyModbusRegister.KVARH_CAPACITIVE_LOW_WORD),
    IMPORT_KVARH(EnergyModbusRegister.IMPORT_KVARH_HIGH_WORD, EnergyModbusRegister.IMPORT_KVARH_LOW_WORD),
    EXPORT_KWH(EnergyModbusRegister.EXPORT_KWH_HIGH_WORD, EnergyModbusRegister.EXPORT_KWH_LOW_WORD),
    EXPORT_KVARH(EnergyModbusRegister.EXPORT_KVARH_HIGH_WORD, EnergyModbusRegister.EXPORT_KVARH_LOW_WORD),
    HOURS_RUN(EnergyModbusRegister.HOURS_RUN_HIGH_WORD, EnergyModbusRegister.HOURS_RUN_LOW_WORD);

    private final ModbusRegister escaleHighWord;

    private final ModbusRegister escaleLowWord;

    CombinedEnergyRegister(final ModbusRegister escaleHighWord, final ModbusRegister escaleLowWord) {
        this.escaleHighWord = escaleHighWord;
        this.escaleLowWord = escaleLowWord;
    }

    @Override
    public final ModbusRegister getLowModbusRegister() {
        return this.escaleLowWord;
    }

    @Override
    public final ModbusRegister getHighModbusRegister() {
        return this.escaleHighWord;
    }
}