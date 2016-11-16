package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.State;

/**
 * Created by ethan.hampton on 10/11/2016.
 * <p>
 * SimpleRobot setup for autonomous
 */

public class RobotAuto extends SimpleRobot {
    //elapsed time
    public ElapsedTime time = new ElapsedTime();

    //the step we are on, tells us speed, left motor distance, right motor distance and any flags
    public int currentStep = 0;

    /*
    to reference step one you would do something like steps[0][x]
    to go backwards, change motor distances not speed, this means you can turn on a dime
    the values per step are as follows:
    1. left motor distance in rotations
    2. right motor distance in rotations
    3. Speed from 1 - 100 (if you want to use default then use -1 and that will auto fill to the current default value, default is 75)
    4. any flags that may be needed, -1 signifies that the program should stop  (optional)
    */
    public double[][] steps;
    //our current state
    public State currentState = State.INIT;

    //The target positions for both motors
    private double leftTarget = 0;
    private double rightTarget = 0;


    /* Initialize standard Hardware interfaces */
    public void Init(HardwareMap ahwMap) {
        super.Init(ahwMap);

        // Set all motors to run with encoders to use encoders to track position
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * default simple drive
     *
     * @param leftRotations  how many rotations left motor should go
     * @param rightRotations how many rotations left motor should go
     * @param speed          -1 if using default speed
     */
    public void setDrive(double leftRotations, double rightRotations, int speed) {
        //if speed isn't specified then use default speed
        if (speed == -1) {
            speed = Constants.DEFAULT_SPEED;
        }

        //create speed for each motors in order to scale properly
        double leftSpeed = speed;
        double rightSpeed = speed;

        //reverse speed as necessary, if we need negative rotations
        if (leftRotations < 0) {
            leftSpeed = -leftSpeed;
        }
        if (rightRotations < 0) {
            rightSpeed = -rightSpeed;
        }

        //scale speed so that turns are relatively smooth, doesn't change anything if they are the same
        if (leftRotations > rightRotations) {
            double scale = rightRotations / leftRotations;
            rightSpeed = rightSpeed * scale;
        } else if (rightRotations > leftRotations) {
            double scale = leftRotations / rightRotations;
            leftSpeed = leftSpeed * scale;
        }


        //sets targets
        leftTarget = leftRotations * Constants.ENCODER_TICKS_PER_ROTATION;
        rightTarget = rightRotations * Constants.ENCODER_TICKS_PER_ROTATION;

        resetAllEncoders();
        leftMotor.setTargetPosition((int) leftTarget);
        rightMotor.setTargetPosition((int) rightTarget);
        startAllEncoders();

        //if either motor doesn't need to move then don't move it
        if (!(leftTarget == 0)) {
            leftMotor.setPower(leftSpeed);
        }
        if (!(rightTarget == 0)) {
            rightMotor.setPower(rightSpeed);
        }
    }


    public void resetAllEncoders() {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void startAllEncoders() {
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
        time += 2;//add 2 seconds just to be safe
        return time;
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
