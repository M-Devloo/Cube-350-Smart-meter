package com.github.mdevloo.cube.modbus.communication.result;

import com.ghgande.j2mod.modbus.procimg.Register;
import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;

public final class ModbusResult<T extends ModbusRegister> {

    private final Register register;

    private final T modbusRegister;

    public ModbusResult(final Register register, final T modbusRegister) {
        this.register = register;
        this.modbusRegister = modbusRegister;
    }

    public final Register getRegister() {
        return this.register;
    }

    public final T getModbusRegister() {
        return this.modbusRegister;
    }
}