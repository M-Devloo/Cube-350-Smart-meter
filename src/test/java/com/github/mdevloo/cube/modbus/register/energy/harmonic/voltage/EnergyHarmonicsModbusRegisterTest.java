package com.github.mdevloo.cube.modbus.register.energy.harmonic.voltage;

import com.github.mdevloo.cube.modbus.register.energy.current.PhaseCurrent;
import com.github.mdevloo.cube.modbus.register.energy.voltage.PhaseVoltage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;

class EnergyHarmonicsModbusRegisterTest {

    private static final int MINIMUM_VALID_HARMONIC = 2;

    private static final int MAXIMUM_VALID_HARMONIC = 15;

    private static IntStream validRange() {
        return IntStream.range(MINIMUM_VALID_HARMONIC, MAXIMUM_VALID_HARMONIC);
    }

    @ParameterizedTest
    @EnumSource(PhaseVoltage.class)
    final void voltageHarmonicsRegisterEnum(final PhaseVoltage phaseVoltage) {
        final EnergyHarmonicsModbusRegister harmonicsRegister = new EnergyHarmonicsModbusRegister(phaseVoltage, 5);
        Assertions.assertEquals(harmonicsRegister.getHarmonicType(), phaseVoltage);
    }

    @ParameterizedTest
    @MethodSource("validRange")
    final void voltageHarmonicsRegisterRange(final int harmonics) {
        final EnergyHarmonicsModbusRegister harmonicsRegister = new EnergyHarmonicsModbusRegister(PhaseVoltage.PHASE_1, harmonics);
        Assertions.assertEquals(harmonicsRegister.getHarmonic(), harmonics);
    }

    @ParameterizedTest
    @EnumSource(PhaseCurrent.class)
    final void currentHarmonicsRegisterEnum(final PhaseCurrent phaseCurrent) {
        final EnergyHarmonicsModbusRegister harmonicsRegister = new EnergyHarmonicsModbusRegister(phaseCurrent, 5);
        Assertions.assertEquals(harmonicsRegister.getHarmonicType(), phaseCurrent);
    }
}