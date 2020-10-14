package com.github.mdevloo.cube.modbus.communication.conf;

import com.github.mdevloo.cube.modbus.communication.ModbusCommunication;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public final class ModbusSerialConfiguration implements ModbusConfiguration {

    private final String port;

    private final ModbusCommunication.BaudRate baudRate;

    private final int timeOut;

    public ModbusSerialConfiguration(final String port,
                                     final ModbusCommunication.BaudRate baudRate,
                                     final int timeOut) {
        Preconditions.checkArgument(Strings.isNullOrEmpty(port), "Port cannot be null or empty");
        this.port = port;
        this.baudRate = Preconditions.checkNotNull(baudRate, "Baud rate cannot be null");
        this.timeOut = timeOut;
    }

    @Override
    public final String getPort() {
        return this.port;
    }

    @Override
    public final ModbusCommunication.BaudRate getBaudRate() {
        return this.baudRate;
    }

    @Override
    public final int getTimeOut() {
        return this.timeOut;
    }
}