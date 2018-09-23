package com.github.mdevloo.cube.modbus.service;

import com.github.mdevloo.cube.CubeMeter;
import com.github.mdevloo.cube.modbus.communication.ModbusCommunication;
import com.github.mdevloo.cube.modbus.communication.conf.SerialModbusCommunication;
import com.github.mdevloo.cube.modbus.communication.result.ModbusResult;
import com.github.mdevloo.cube.modbus.service.conf.CubeServiceConfiguration;
import com.github.mdevloo.runnable.ScheduledFixedExecutorPoolService;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

public final class CubeModbusService implements ModbusService {

    private static final Logger logger = LoggerFactory.getLogger(CubeModbusService.class);

    private final CubeMeter cubeMeter;

    private final CubeServiceConfiguration cubeServiceConfiguration;

    private final CubeMeterCallback cubeMeterCallback;

    private final ScheduledFixedExecutorPoolService<List<ModbusResult>> scheduledFixedExecutorPoolService;

    public CubeModbusService(final CubeMeter cubeMeter,
                             final CubeServiceConfiguration cubeServiceConfiguration,
                             final CubeMeterCallback cubeMeterCallback) {
        this.cubeMeter = Preconditions.checkNotNull(cubeMeter, "Not possible to start the initializations with null cubes");
        this.cubeServiceConfiguration = cubeServiceConfiguration;
        this.cubeMeterCallback = cubeMeterCallback;
        this.scheduledFixedExecutorPoolService = new ScheduledFixedExecutorPoolService<>(1);
    }

    @Override
    public final ScheduledFuture<List<ModbusResult>> startSchedulingService() {
        final Callable<List<ModbusResult>> callable = (() -> {
            try (final ModbusCommunication communication = new SerialModbusCommunication(this.cubeMeter)) {
                final List<ModbusResult> modbusResults = communication.readMultipleRegisters(this.cubeMeter.getModbusRegisters());
                if (Objects.nonNull(this.cubeMeterCallback) && !modbusResults.isEmpty()) {
                    this.cubeMeterCallback.notifyRegisterUpdates(modbusResults);
                }

                logger.info("Received {} results from the meter.", modbusResults.size());

                return modbusResults;
            } catch (final Exception e) {
                logger.error("Modbus communication error: [{}]", e.getMessage());
                return new ArrayList<>();
            }
        });

        return this.scheduledFixedExecutorPoolService.schedule(callable,
                this.cubeServiceConfiguration.getPeriod(),
                this.cubeServiceConfiguration.getTimeUnit());
    }

    @Override
    public final void stopSchedulingService() {
        this.scheduledFixedExecutorPoolService.close();
    }
}