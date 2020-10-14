package com.github.mdevloo.cube.modbus.communication.result;

import com.ghgande.j2mod.modbus.procimg.Register;
import com.github.mdevloo.cube.modbus.register.energy.energy.CombinedEnergyRegister;
import com.github.mdevloo.cube.modbus.service.ModbusService;
import com.google.common.base.Preconditions;

import java.util.List;

public final class CombinedModbusResult<T extends CombinedEnergyRegister> {

    private static final int COMBINED_RESULT_SIZE = 2;

    private static final int ENERGY_SCALING_MULTIPLIER = 65536;

    private static final int SCALING_FACTOR = 10;

    private final List<Register> multipleRegisters;

    private final T combinedRegister;

    private final ModbusResult escaleResult;

    private final ModbusService.Scaler scaler;

    public CombinedModbusResult(final List<Register> multipleRegisters,
                                final T combinedRegister,
                                final ModbusResult escaleResult,
                                final ModbusService.Scaler scaler) {
        Preconditions.checkNotNull(multipleRegisters);
        Preconditions.checkArgument(!multipleRegisters.isEmpty() && multipleRegisters.size() == COMBINED_RESULT_SIZE);

        this.multipleRegisters = multipleRegisters;
        this.combinedRegister = combinedRegister;
        this.escaleResult = escaleResult;
        this.scaler = scaler;
    }

    public final List<Register> getMultipleRegisters() {
        return this.multipleRegisters;
    }

    public final T getCombinedRegister() {
        return this.combinedRegister;
    }

    public final double calculateEnergyScaling() {
        final Register highWord = this.multipleRegisters.get(0);
        final Register lowWord = this.multipleRegisters.get(1);

        final double scalerResult = Math.pow(SCALING_FACTOR, (double) this.escaleResult.getRegister().getValue() - this.scaler.getEnergyScaler());
        return ((ENERGY_SCALING_MULTIPLIER * highWord.getValue()) + lowWord.getValue()) * scalerResult;
    }

    public final ModbusResult getEscaleResult() {
        return this.escaleResult;
    }

    public final ModbusService.Scaler getScaler() {
        return this.scaler;
    }
}