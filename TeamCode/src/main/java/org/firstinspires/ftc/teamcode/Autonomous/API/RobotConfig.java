package org.firstinspires.ftc.teamcode.Autonomous.API;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by ethan.hampton on 10/11/2016.
 * <p>
 * setup for autonomous library
 */

public class RobotConfig {
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    private int ENCODER_TICKS_PER_ROTATION;

    //elapsed time
    ElapsedTime time = new ElapsedTime();
    //what time to wait till
    double waitTime = 0;
    //what time that current step started
    double startTime = 0;

    //the step we are on, tells us speed, left motor distance, right motor distance and any flags
    int currentStep = 0;

    /*
    see https://goo.gl/V4g35h for steps reference
    to reference step one you would do something like steps[0][x]
    to go backwards, change motor distances not speed, this means you can turn on a dime
    the values per step are as follows:
    1. any flags 0 signifies default driving, -1 signifies that the program should stop
    2. left motor distance in rotations
    3. right motor distance in rotations
    4. Speed from 1 - 100 (if you want to use default then use -1 and that will auto fill to the current default value, default is 75)
    */
    double[][] steps;
    //our current state
    State currentState = State.INIT;

    //The target positions for both motors
    private double leftTarget = 0;
    private double rightTarget = 0;
    private double rightSpeed = 0;
    private double leftSpeed = 0;

    /**
     * @param rightMotor              assumes motor has been configured correctly
     * @param leftMotor               assumes motor has been configured correctly
     * @param encoderTicksPerRotation how many encoder ticks per rotation
     */
    public void Init(DcMotor rightMotor, DcMotor leftMotor, int encoderTicksPerRotation) {
        // Set all motors to run with encoders to use encoders to track position
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.rightMotor = rightMotor;
        this.leftMotor = leftMotor;
        ENCODER_TICKS_PER_ROTATION = encoderTicksPerRotation;
    }

    /**
     * default simple drive
     *
     * @param leftRotations  how many rotations left motor should go, can be negative
     * @param rightRotations how many rotations left motor should go, can be negative
     * @param speed          -1 if using default speed, assumes speed is always positive
     */
    void setDrive(double leftRotations, double rightRotations, double speed) {
        //if speed isn't specified then use default speed
        if (speed == -1) {
            speed = 75;//75 is default speed
        }

        /*
        if the speed is from a scale from 0 to 100 instead of 0 to 1
        change it so it is in a scale the robot will work with
         */
        if (speed > 1) {
            speed = speed / 100;
        }

        //create speed for each motors in order to scale properly
        leftSpeed = speed;
        rightSpeed = speed;

        //reverse speed as necessary, if we need negative rotations
        if (leftRotations < 0) {
            leftSpeed = -leftSpeed;
        }
        if (rightRotations < 0) {
            rightSpeed = -rightSpeed;
        }

        /*
        scale speed so that turns are relatively smooth, doesn't change anything if they are the same
        note that we are scaling by 2 so we have a differential effort
        */
        if (Math.abs(leftRotations) > Math.abs(rightRotations)) {
            double scale = Math.abs(rightRotations) / Math.abs(leftRotations);
            rightSpeed = rightSpeed * scale;
        } else if (Math.abs(rightRotations) > Math.abs(leftRotations)) {
            double scale = Math.abs(leftRotations) / Math.abs(rightRotations);
            leftSpeed = leftSpeed * scale;
        }


        //sets targets
        leftTarget = leftRotations * ENCODER_TICKS_PER_ROTATION;
        rightTarget = rightRotations * ENCODER_TICKS_PER_ROTATION;

        resetAllEncoders();
        leftMotor.setTargetPosition((int) leftTarget);
        rightMotor.setTargetPosition((int) rightTarget);
        startAllEncoders();

        //if either motor doesn't need to move then don't move it
        if (!(leftTarget == 0)) {
            leftMotor.setPower(leftSpeed);
        } else {
            leftMotor.setPower(0);
        }
        if (!(rightTarget == 0)) {
            rightMotor.setPower(rightSpeed);
        } else {
            rightMotor.setPower(0);
        }
    }


    private void resetAllEncoders() {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void startAllEncoders() {
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    public double getRightTarget() {
        return rightTarget;
    }

    public void setRightTarget(double rightTarget) {
        this.rightTarget = rightTarget;
    }

    public double getLeftTarget() {
        return leftTarget;
    }

    public void setLeftTarget(double leftTarget) {
        this.leftTarget = leftTarget;
    }
}
