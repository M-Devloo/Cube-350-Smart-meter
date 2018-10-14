package com.github.mdevloo.cube.modbus.register.energy.voltage;

import com.github.mdevloo.cube.modbus.register.energy.Energy;

public interface Voltage<T1 extends Voltage.VoltageType> extends Energy<T1> {

    T1 getVoltageType();

    enum VoltageType {
        V1,
        V2,
        V3
    }
}