package com.github.mdevloo.cube.modbus.communication;

import com.github.mdevloo.cube.modbus.communication.result.ModbusResult;
import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;

import java.util.List;

public interface ModbusCommunication extends AutoCloseable {

    List<ModbusResult> readMultipleRegisters(List<ModbusRegister> modbusRegisters);

    ModbusResult<ModbusRegister> readRegister(final ModbusRegister register);

    enum BaudRate {
        RATE_4800(4800),
        RATE_9600(9600),
        RATE_19200(19200);

        private final int baud;

        BaudRate(final int baud) {
            this.baud = baud;
        }

        public final int getBaud() {
            return baud;
        }
    }
}