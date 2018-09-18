package com.github.mdevloo.cube.modbus.register.energy.harmonic.voltage;

import com.github.mdevloo.cube.modbus.register.access.RegisterAccess;
import com.github.mdevloo.cube.modbus.register.energy.Energy;
import com.github.mdevloo.cube.modbus.register.energy.current.Current;
import com.github.mdevloo.cube.modbus.register.energy.harmonic.HarmonicsRegister;
import com.github.mdevloo.cube.modbus.register.energy.voltage.Voltage;
import com.github.mdevloo.cube.modbus.register.scalers.RegisterScaling;
import com.google.common.base.Preconditions;

import static com.github.mdevloo.cube.modbus.register.energy.RegisterUtil.REGISTER_OFFSET_MODBUS;

public final class EnergyHarmonicsRegister implements HarmonicsRegister<Energy> {

    private static final int V1_START_ADDRESS = 7936;

    private static final int V2_START_ADDRESS = 8192;

    private static final int V3_START_ADDRESS = 8448;

    private static final int I1_START_ADDRESS = 8704;

    private static final int I2_START_ADDRESS = 8960;

    private static final int I3_START_ADDRESS = 9216;

    private static final int HARMONIC_START = 2;

    private static final int HARMONIC_END = 15;

    private final Energy energy;

    private final RegisterAccess registerAccess;

    private final int harmonic;

    private final int registerValue;

    private final int modbusRegisterValue;

    private final RegisterScaling registerScaling;

    EnergyHarmonicsRegister(final Energy energy, final int harmonic) {
        this.energy = Preconditions.checkNotNull(energy, "Voltage cannot be null");

        Preconditions.checkArgument(validHarmonic(harmonic, HARMONIC_START, HARMONIC_END),
                "Illegal harmonic, value needs to be between [%s] and [%s]", HARMONIC_START, HARMONIC_END);

        this.harmonic = harmonic;
        registerValue = startAddress(energy) + this.harmonic;
        modbusRegisterValue = registerValue + REGISTER_OFFSET_MODBUS;
        registerAccess = RegisterAccess.READ;
        registerScaling = RegisterScaling.THOUSAND_EQUALS_100_PERCENTAGE;
    }

    @Override
    public final Energy getHarmonicType() {
        return energy;
    }

    @Override
    public final int getHarmonic() {
        return harmonic;
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

    private int startAddress(final Energy energy) {
        if (energy instanceof Voltage) {
            return voltageAddress((Voltage) energy);
        } else if (energy instanceof Current) {
            return currentAddress((Current) energy);
        }

        throw new IllegalStateException("Unknown class for [" + energy + "]");
    }

    private int voltageAddress(final Voltage voltage) {
        switch (voltage.getVoltageType()) {
            case V1:
                return V1_START_ADDRESS;
            case V2:
                return V2_START_ADDRESS;
            case V3:
                return V3_START_ADDRESS;
            default:
                throw new IllegalStateException("Unknown voltage state");
        }
    }

    private int currentAddress(final Current current) {
        switch (current.getCurrentType()) {
            case I1:
                return I1_START_ADDRESS;
            case I2:
                return I2_START_ADDRESS;
            case I3:
                return I3_START_ADDRESS;
            default:
                throw new IllegalStateException("Unknown current state");
        }
    }
}