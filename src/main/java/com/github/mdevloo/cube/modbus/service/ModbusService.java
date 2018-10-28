package com.github.mdevloo.cube.modbus.service;

import com.github.mdevloo.cube.modbus.communication.result.CombinedModbusResult;
import com.github.mdevloo.cube.modbus.communication.result.ModbusResult;

import java.util.List;

public interface ModbusService {

    List<ModbusResult> readModbusRegisters();

    List<CombinedModbusResult> readEnergyModbusRegister(final Scaler kwh);

    enum Scaler {
        WH(3),
        KWH(6);

        private final int energyScaler;

        Scaler(final int energyScaler) {
            this.energyScaler = energyScaler;
        }

        public final int getEnergyScaler() {
            return this.energyScaler;
        }
    }
}