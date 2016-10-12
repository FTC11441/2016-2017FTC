package org.firstinspires.ftc.teamcode.Configuration;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utils.Constants;

/**
 * Created by ethan.hampton on 10/11/2016.
 * <p>
 * Robot setup for autonomous
 */

// TODO: 10/11/2016 add description to all turning and other stuff added today
public class RobotAuto {


    /* Public OpMode members. */
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;


    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {

        // Define and Initialize Motors
        leftMotor = ahwMap.dcMotor.get("left motor");
        rightMotor = ahwMap.dcMotor.get("right motor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        // Set all motors to run with encoders to a position
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void leftTurn(int rotations) {
        resetEncoders();
        leftMotor.setTargetPosition(Constants.ENCODER_TICKS_PER_ROTATION * rotations);
        startEncoders();
    }

    public void rightTurn(int rotations) {
        resetEncoders();
        rightMotor.setTargetPosition(Constants.ENCODER_TICKS_PER_ROTATION * rotations);
        startEncoders();
    }

    public void straight(int rotations) {
        resetEncoders();
        leftMotor.setTargetPosition(Constants.ENCODER_TICKS_PER_ROTATION * rotations);
        rightMotor.setTargetPosition(Constants.ENCODER_TICKS_PER_ROTATION * rotations);
        startEncoders();
    }

    public void resetEncoders() {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void startEncoders() {
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
