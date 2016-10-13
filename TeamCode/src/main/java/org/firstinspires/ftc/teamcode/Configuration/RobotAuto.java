package org.firstinspires.ftc.teamcode.Configuration;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.Constants;

import java.util.concurrent.TimeUnit;

/**
 * Created by ethan.hampton on 10/11/2016.
 * <p>
 * Robot setup for autonomous
 */

// TODO: 10/11/2016 add description to all turning and other stuff added
public class RobotAuto {


    /* Public OpMode members. */
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;

    //sets speed when moving while relying on encoders
    private int speed = 75;


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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void leftTurn(int rotations) {
        resetEncoders();
        leftMotor.setTargetPosition(Constants.ENCODER_TICKS_PER_ROTATION * rotations);
        startEncoders();
        leftMotor.setPower(speed);
    }

    public void rightTurn(int rotations) {
        resetEncoders();
        rightMotor.setTargetPosition(Constants.ENCODER_TICKS_PER_ROTATION * rotations);
        startEncoders();
        rightMotor.setPower(speed);
    }

    public void straight(int rotations) {
        resetEncoders();
        leftMotor.setTargetPosition(Constants.ENCODER_TICKS_PER_ROTATION * rotations);
        rightMotor.setTargetPosition(Constants.ENCODER_TICKS_PER_ROTATION * rotations);
        startEncoders();
        leftMotor.setPower(speed);
        rightMotor.setPower(speed);
    }

    public void resetEncoders() {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void startEncoders() {
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * calculates how much time it should take to go a certain distance
     *
     * @param rotations how many rotations the robot needs to complete
     * @param speed     at what speed will the robot run at
     * @return time in seconds
     */
    public int calculateTime(int rotations, int speed) {
        int time = rotations * (Constants.MOTOR_RPM / 60) * (speed / 100);
        time += 3;//add 3 seconds just to be safe
        return time;
    }

    //timeout after a number of seconds
    public boolean Timeout(int time) {
        ElapsedTime runtime = new ElapsedTime();
        runtime.reset();
        while (true) {
            if (!((leftMotor.isBusy() || rightMotor.isBusy()) && runtime.seconds() < time)) {
                return false;
            } else {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
