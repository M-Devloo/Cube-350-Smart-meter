package com.github.mdevloo.cube.modbus.service;

import com.github.mdevloo.cube.CubeMeter;
import com.github.mdevloo.cube.modbus.communication.ModbusCommunication;
import com.github.mdevloo.cube.modbus.communication.conf.SerialModbusCommunication;
import com.github.mdevloo.cube.modbus.communication.result.CombinedModbusResult;
import com.github.mdevloo.cube.modbus.communication.result.ModbusResult;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public final class CubeModbusService implements ModbusService {

    private static final Logger logger = LoggerFactory.getLogger(CubeModbusService.class);

    private final CubeMeter cubeMeter;

    public CubeModbusService(final CubeMeter cubeMeter) {
        this.cubeMeter = Preconditions.checkNotNull(cubeMeter, "Not possible to start the initializations with null cubes");
    }

    @Override
    public final List<ModbusResult> readModbusRegisters() {
        try (final ModbusCommunication communication = new SerialModbusCommunication(this.cubeMeter)) {
            final List<ModbusResult> modbusResults = communication.readMultipleRegisters(this.cubeMeter.getModbusRegisters());
            logger.info("Received {} results from the meter.", modbusResults.size());

            return modbusResults;
        } catch (final Exception e) {
            logger.error("Modbus communication error: [{}]", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public final List<CombinedModbusResult> readEnergyModbusRegister(final Scaler scaler) {
        try (final ModbusCommunication communication = new SerialModbusCommunication(this.cubeMeter)) {
            return communication.readCombinedRegister(this.cubeMeter.getCombinedModbusRegisters(), scaler);
        } catch (final Exception e) {
            logger.error("Modbus communication error: [{}]", e.getMessage());
            return new ArrayList<>();
        }
    }
}