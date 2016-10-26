package org.firstinspires.ftc.teamcode.Configuration;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ethan.hampton on 10/25/2016.
 * <p>
 * Robot that is current to actual robot
 */

public class Robot extends SimpleRobot {

    Servo leftBumper;
    Servo rightBumper;

    /* Initialize standard Hardware interfaces */
    public void Init(HardwareMap ahwMap) {
        this.simpleInit(ahwMap);
        leftBumper = ahwMap.servo.get("Left bumper");
        rightBumper = ahwMap.servo.get("Right bumper");
        leftBumper.setDirection(Servo.Direction.REVERSE);


    }

}
