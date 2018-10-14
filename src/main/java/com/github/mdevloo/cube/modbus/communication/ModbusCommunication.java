package com.github.mdevloo.cube.modbus.communication;

import com.github.mdevloo.cube.modbus.communication.result.CombinedModbusResult;
import com.github.mdevloo.cube.modbus.communication.result.ModbusResult;
import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;
import com.github.mdevloo.cube.modbus.register.energy.energy.CombinedEnergyRegister;
import com.github.mdevloo.cube.modbus.service.ModbusService;

import java.util.List;

public interface ModbusCommunication extends AutoCloseable {

    List<ModbusResult> readMultipleRegisters(final List<ModbusRegister> modbusRegisters);

    ModbusResult<ModbusRegister> readRegister(final ModbusRegister register);

    CombinedModbusResult readCombinedRegister(final CombinedEnergyRegister combinedModbusRegister, final ModbusService.Scaler scaler);

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