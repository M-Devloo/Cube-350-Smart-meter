package com.github.mdevloo.cube.modbus.communication.conf;

import com.github.mdevloo.cube.modbus.communication.ModbusCommunication;

public interface ModbusConfiguration {
    String getPort();

    ModbusCommunication.BaudRate getBaudRate();

    int getTimeOut();
}