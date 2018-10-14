package com.github.mdevloo.cube.modbus.register.energy.current;

import com.github.mdevloo.cube.modbus.register.energy.Energy;

public interface Current<T1 extends Current.CurrentType> extends Energy<T1> {

    T1 getCurrentType();

    enum CurrentType{
        I1,
        I2,
        I3
    }
}