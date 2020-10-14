package com.github.mdevloo.cube.modbus.communication.conf;

import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.facade.ModbusSerialMaster;
import com.ghgande.j2mod.modbus.net.AbstractSerialConnection;
import com.ghgande.j2mod.modbus.procimg.Register;
import com.ghgande.j2mod.modbus.util.SerialParameters;
import com.github.mdevloo.cube.CubeMeter;
import com.github.mdevloo.cube.modbus.communication.ModbusCommunication;
import com.github.mdevloo.cube.modbus.communication.exception.ModbusConnectionException;
import com.github.mdevloo.cube.modbus.communication.exception.ModbusReadException;
import com.github.mdevloo.cube.modbus.communication.result.ModbusResult;
import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class SerialModbusCommunication implements ModbusCommunication {

    private static final Logger logger = LoggerFactory.getLogger(SerialModbusCommunication.class);

    private static final int DATA_BITS = 8;

    private static final int SINGLE_REGISTER = 1;

    private static final int TRANSMISSION_DELAY_BETWEEN_FRAMES = 0;

    private final ModbusSerialMaster master;

    private final CubeMeter cubeMeter;

    public SerialModbusCommunication(final CubeMeter cubeMeter) {
        final ModbusConfiguration conf = cubeMeter.getModbusSerialConfiguration();
        final BaudRate serialBaudRate = conf.getBaudRate();

        final SerialParameters serialParameters = new SerialParameters(conf.getPort(),
                serialBaudRate.getBaud(),
                AbstractSerialConnection.FLOW_CONTROL_DISABLED,
                AbstractSerialConnection.FLOW_CONTROL_DISABLED,
                DATA_BITS,
                AbstractSerialConnection.ONE_STOP_BIT,
                AbstractSerialConnection.NO_PARITY, true);
        this.master = new ModbusSerialMaster(serialParameters, conf.getTimeOut(), TRANSMISSION_DELAY_BETWEEN_FRAMES);
        this.cubeMeter = cubeMeter;

        final AbstractSerialConnection connection = this.master.getConnection();
        if (Objects.nonNull(connection) && !connection.isOpen()) {
            try {
                this.master.connect();
            } catch (final Exception e) {
                throw new ModbusConnectionException("Modbus connection exception: [" + e.getMessage() + "]");
            }
        }
    }

    @Override
    public final void close() {
        synchronized (this.master) {
            final AbstractSerialConnection connection = this.master.getConnection();
            if (Objects.nonNull(connection) && connection.isOpen()) {
                this.master.disconnect();
            } else {
                logger.warn("Already disconnected the modbus connection");
            }
        }
    }

    @Override
    public final ModbusResult<ModbusRegister> readRegister(final ModbusRegister register) {
        return new ModbusResult<>(this.readSingleRegister(register), register);
    }

    @Override
    public final List<ModbusResult> readMultipleRegisters(final List<ModbusRegister> modbusRegisters) {
        return modbusRegisters.stream()
                .map(reg -> new ModbusResult<>(this.readSingleRegister(reg), reg))
                .collect(Collectors.toList());
    }

    private Register readSingleRegister(final ModbusRegister modbusRegister) {
        synchronized (this.master) {
            try {
                final Register[] registers = this.master.readMultipleRegisters(this.cubeMeter.getSlaveId(), modbusRegister.getModbusRegisterValue(), SINGLE_REGISTER);
                return Arrays.stream(registers).findFirst()
                        .orElseThrow(() -> new ModbusReadException("No registers values returned when reading Register [" + modbusRegister + "]"));
            } catch (final ModbusException e) {
                throw new ModbusReadException("Read exception: [" + e.getMessage() + "]");
            }
        }
    }
}