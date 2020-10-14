package com.github.mdevloo.cube;

import com.github.mdevloo.cube.modbus.communication.conf.ModbusConfiguration;
import com.github.mdevloo.cube.modbus.register.energy.ModbusRegister;
import com.github.mdevloo.cube.modbus.register.energy.energy.CombinedModbusRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CubeMeter {

    private final int slaveId;

    private final List<ModbusRegister> modbusRegisters;

    private final List<CombinedModbusRegister> combinedModbusRegisters;

    private final ModbusConfiguration modbusSerialConfiguration;

    private CubeMeter(final Builder builder) {
        this.modbusRegisters = builder.modbusRegisters;
        this.slaveId = builder.slaveId;
        this.modbusSerialConfiguration = builder.modbusConfiguration;
        this.combinedModbusRegisters = builder.combinedModbusRegisters;
    }

    public final List<ModbusRegister> getModbusRegisters() {
        return this.modbusRegisters;
    }

    public final int getSlaveId() {
        return this.slaveId;
    }

    public final ModbusConfiguration getModbusSerialConfiguration() {
        return this.modbusSerialConfiguration;
    }

    public final List<CombinedModbusRegister> getCombinedModbusRegisters() {
        return this.combinedModbusRegisters;
    }

    public static final class Builder {
        private final int slaveId;
        private final ModbusConfiguration modbusConfiguration;
        private List<ModbusRegister> modbusRegisters = new ArrayList<>();
        private List<CombinedModbusRegister> combinedModbusRegisters = new ArrayList<>();

        public Builder(final int slaveId, final ModbusConfiguration modbusConfiguration) {
            this.slaveId = slaveId;
            this.modbusConfiguration = modbusConfiguration;
        }

        public final Builder registers(final ModbusRegister... modbusRegisters) {
            this.modbusRegisters = Arrays.asList(modbusRegisters);
            return this;
        }

        public final Builder combinedRegisters(final CombinedModbusRegister... combinedModbusRegisters) {
            this.combinedModbusRegisters = Arrays.asList(combinedModbusRegisters);
            return this;
        }

        public final Builder fromPrototype(final CubeMeter prototype) {
            this.modbusRegisters = prototype.modbusRegisters;
            this.combinedModbusRegisters = prototype.combinedModbusRegisters;
            return this;
        }

        public final CubeMeter build() {
            return new CubeMeter(this);
        }
    }
}