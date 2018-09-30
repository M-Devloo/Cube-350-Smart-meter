# Cube 350 Energy meter [![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/M-Devloo/Cube-350-Smart-meter/issues)

This library allows you to communicate to a Northern Design Cube 350 Smart meter through two-wired modbus (RS485)

This library is still under development till a master release is provided.


## Travis build status 
**Master**: [![Build Status](https://travis-ci.com/M-Devloo/Cube-350-Smart-meter.svg?branch=master)](https://travis-ci.com/M-Devloo/Cube-350-Smart-meter)  
**Development**: [![Build Status](https://travis-ci.com/M-Devloo/Cube-350-Smart-meter.svg?branch=development)](https://travis-ci.com/M-Devloo/Cube-350-Smart-meter)


## Getting Started
These instructions will provide you a copy of the project and allows you to get the library up and running on your local machine.

### Prerequisites
```
* A minimum of Java 8 is required for this library.
* If running on Linux, you will need to add your user to the dialout group.
```

### Required hardware
```
* Cheap CH340 USB to RS485 converter (about 1.5$) or other variants
* Cube 350 Kwh meter
```

### Serial port user [Linux]
To able to access the serial port, you need to add your user that you want to run this library to the dialout group.  
This will add your current user to the dialout group.   
```
sudo adduser $USER dialout
```

### Set the slave ID, Baudrate & other things like CT for your cube 350

__(See Section 4. Programming of the Cube350 A4 Operating Manual July 2006 A4.pdf)__

> (SW) properties are required as input parameters for this library.

***
Quick summary:
* Go into programming mode (See Section 4. Programming of the Cube350 A4 Operating Manual July 2006 A4.pdf)
* Press I & V together for 5 seconds.
> Depending you have set a security code through Modbus or the programming menu, you will or will not have to provide a code.
* Press I to go to another property.
* (SW) CT -> Set the current of your attached 0.33mV CT (50A, 100A, 200A, 800A, ...)
* Un (Nominal Line-Line Voltage) ~ Most is 400V (I needed to tweak mine to 390V)
* Plr, Plt, Pto is depending on the Pulse which is the two external hardware pulse outputs
* (SW) Br -> Baud rate (Support 4800, 9600 & 19200)
* (SW) Addr -> Modbus Slave ID. This ID also needs to be used in the software.
* Saving to flash & ready to use.
***

## Getting Started (Library)

### How to use single-threaded

> This library has been written as a Multi-threaded polling service in mind which works with a `CubeMeterCallback` that updates the user when Modbus registers are available.  
Although when wanted, it is also possible to use this single-threaded but just calling the get() method from the returned ScheduledFuture<?>  
This will block the main thread till all the registers are read out.

```java
final class Application {
    void cubeReadout() {
        final int id = 5;
        final ModbusConfiguration modbusConfiguration = new ModbusSerialConfiguration("/dev/ttyUSB0",
                ModbusCommunication.BaudRate.RATE_9600, 10);
        
        final CubeMeter cubeMeter = new CubeMeter.Builder(id, modbusConfiguration)
                .registers(InstantaneousModbusRegister.AMPS_SCALE_KI, InstantaneousModbusRegister.PH1_PH2_VOLTS, InstantaneousModbusRegister.PH2_PH3_VOLTS, InstantaneousModbusRegister.PH3_PH1_VOLTS)
                .build();

        final CubeServiceConfiguration cubeServiceConfiguration = new CubeServiceConfiguration(10, TimeUnit.SECONDS);
        final ModbusService cubeModbusService = new CubeModbusService(cubeMeter, cubeServiceConfiguration, null); // callback if using the service else null

        try {
            final List<ModbusResult> modbusResults = cubeModbusService.startSchedulingService().get(); // Single-threaded OR use the call-back to get notified as a service.
            for (final ModbusResult<?> result : modbusResults) {
                final ModbusRegister modbusRegister = result.getModbusRegister();
                final Register register = result.getRegister();
                final int value = register.getValue();

                logger.info("Value of Register: {} - {}", modbusRegister, value);
            }
        } catch (final InterruptedException | ExecutionException e) {
            logger.error("{}", e.getMessage());
        }
    }
}
```

## Exceptions
````
[pool-1-thread-1] ERROR com.github.mdevloo.cube.modbus.service.CubeModbusService - Modbus communication error: [Modbus connection exception: [Port [ttyUSB0] cannot be opened or does not exist - Valid ports are: [ttyUSB0]]]
````
* Add your user to the dialout group. See: [Serial port user [Linux]](#serial-port-user-linux)  
* It is also possible that your cube has the wrong Slave ID. See: [How to set your Slave ID for your Cube 350](#set-the-slave-id-baudrate--other-things-like-ct-for-your-cube-350)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details