package com.github.mdevloo.cube.modbus.service;

import com.github.mdevloo.cube.modbus.communication.result.CombinedModbusResult;
import com.github.mdevloo.cube.modbus.communication.result.ModbusResult;
import com.github.mdevloo.cube.modbus.register.energy.energy.CombinedEnergyRegister;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

public interface ModbusService {

    ScheduledFuture<List<ModbusResult>> startSchedulingService();

    void stopSchedulingService();

    CombinedModbusResult readEnergyModbusRegister(final CombinedEnergyRegister combinedEnergyRegister, final Scaler kwh);

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