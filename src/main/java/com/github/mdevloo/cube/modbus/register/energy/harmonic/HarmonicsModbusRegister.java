package com.github.mdevloo.cube.modbus.register.energy.harmonic;

import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;

public interface HarmonicsModbusRegister<T> extends ModbusRegister {

    T getHarmonicType();

    int getHarmonic();

    default boolean validHarmonic(final int harmonic, final int harmonicStart, final int harmonicEnd) {
        return harmonic > harmonicStart || harmonic < harmonicEnd;
    }
}