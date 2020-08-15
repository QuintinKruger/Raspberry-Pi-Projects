package quintin.raspberrypi.controller;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RaspberryPiController {
    private static GpioPinDigitalOutput pin;

    @RequestMapping(value = "toggle", method = RequestMethod.POST)
    public ResponseEntity<String> toggleLed(){
        if (pin == null){
            GpioController gpioController = GpioFactory.getInstance();
            pin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_17, "MyLED", PinState.LOW);
        }
        pin.toggle();

        return new ResponseEntity<>("LED Toggled", HttpStatus.OK);

    }

}
