package com.github.mdevloo.cube.modbus.service;

import com.github.mdevloo.cube.CubeMeter;
import com.github.mdevloo.cube.modbus.communication.ModbusCommunication;
import com.github.mdevloo.cube.modbus.communication.conf.SerialModbusCommunication;
import com.github.mdevloo.cube.modbus.communication.result.ModbusResult;
import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;
import com.github.mdevloo.cube.modbus.service.conf.CubeServiceConfiguration;
import com.github.mdevloo.runnable.ScheduledFixedExecutorPoolService;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

public final class CubeModbusService implements ModbusService {

    private static final Logger logger = LoggerFactory.getLogger(CubeModbusService.class);

    private static final int SCHEDULED_START_DELAY = 0;

    private final CubeMeter cubeMeter;

    private final CubeServiceConfiguration cubeServiceConfiguration;

    private final CubeMeterCallback cubeMeterCallback;

    private final ScheduledFixedExecutorPoolService scheduledFixedExecutorPoolService;

    private volatile List<ModbusRegister> modbusRegisters;

    public CubeModbusService(final CubeMeter cubeMeter,
                             final CubeServiceConfiguration cubeServiceConfiguration,
                             final CubeMeterCallback cubeMeterCallback) {
        this.cubeMeter = Preconditions.checkNotNull(cubeMeter, "Not possible to start the initializations with null cubes");
        this.cubeServiceConfiguration = cubeServiceConfiguration;
        this.cubeMeterCallback = cubeMeterCallback;
        this.scheduledFixedExecutorPoolService = new ScheduledFixedExecutorPoolService(1);
    }

    @Override
    public final ScheduledFuture startSchedulingService() {
        return this.scheduledFixedExecutorPoolService.schedule(() -> {
            try (final ModbusCommunication communication = new SerialModbusCommunication(this.cubeMeter)) {
                final List<ModbusResult> modbusResults = communication.readMultipleRegisters(this.modbusRegisters);
                if (Objects.nonNull(this.cubeMeterCallback) && !modbusResults.isEmpty()) {
                    this.cubeMeterCallback.notifyRegisterUpdates(modbusResults);
                }

                logger.info("Received {} results from the meter.", modbusResults.size());
            } catch (final Exception e) {
                logger.error("Modbus communication error: [{}]", e.getMessage());
            }
        }, SCHEDULED_START_DELAY, this.cubeServiceConfiguration.getPeriod(), this.cubeServiceConfiguration.getTimeUnit());
    }

    @Override
    public final void stopSchedulingService() {
        this.scheduledFixedExecutorPoolService.close();
    }

    @Override
    public final void subscribeRegisters(final ModbusRegister... modbusRegisterList) {
        this.modbusRegisters = Arrays.asList(modbusRegisterList);
    }
}