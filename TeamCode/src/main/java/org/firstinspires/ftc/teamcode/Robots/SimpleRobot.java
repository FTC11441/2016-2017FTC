package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by ethan.hampton on 10/4/2016.
 * <p>
 * For our robot a very simple interface
 */

public class SimpleRobot {

    /* Public OpMode members. */
    public DcMotor leftMotor;
    public DcMotor rightMotor;

    //elapsed time
    public ElapsedTime time = new ElapsedTime();


    /* Initialize standard Hardware interfaces */
    public void Init(HardwareMap ahwMap) {

        // Define and Initialize Motors
        leftMotor = ahwMap.dcMotor.get("leftMotor");
        rightMotor = ahwMap.dcMotor.get("rightMotor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}
