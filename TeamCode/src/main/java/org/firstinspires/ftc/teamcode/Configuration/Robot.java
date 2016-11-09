package org.firstinspires.ftc.teamcode.Configuration;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ethan.hampton on 10/25/2016.
 * <p>
 * Robot that is current to actual robot
 */

public class Robot extends SimpleRobot {

    public Servo bumper = null;

    /* Initialize standard Hardware interfaces */
    public void Init(HardwareMap ahwMap) {
        this.simpleInit(ahwMap);
        bumper = ahwMap.servo.get("bumper");
    }


}
