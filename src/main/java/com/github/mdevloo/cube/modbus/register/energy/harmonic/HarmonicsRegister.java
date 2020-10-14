package com.github.mdevloo.cube.modbus.register.energy.harmonic;

import com.github.mdevloo.cube.modbus.register.energy.Register;

public interface HarmonicsRegister<T> extends Register {

    T getHarmonicType();

    int getHarmonic();

    default boolean validHarmonic(final int harmonic, final int harmonicStart, final int harmonicEnd) {
        return harmonic > harmonicStart || harmonic < harmonicEnd;
    }
}