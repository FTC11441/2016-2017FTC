package org.firstinspires.ftc.teamcode.Configuration;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.State;

import java.util.concurrent.TimeUnit;

/**
 * Created by ethan.hampton on 10/11/2016.
 * <p>
 * SimpleRobot setup for autonomous
 */

public class RobotAuto {
    /* Public OpMode members. */
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;

    //elapsed time
    public ElapsedTime time = new ElapsedTime();

    //default speed to run robot at
    private int defaultSpeed = 75;

    //the step we are on, tells us speed, left motor distance, right motor distance and any flags
    public int currentStep = 0;

    /*
    to reference step one you would do something like steps[0][x]
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
    public double leftTarget = 0;
    public double rightTarget = 0;


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
            speed = defaultSpeed;
        }

        //sets targets
        leftTarget = leftRotations * Constants.ENCODER_TICKS_PER_ROTATION;
        rightTarget = rightRotations * Constants.ENCODER_TICKS_PER_ROTATION;

        resetAllEncoders();
        leftMotor.setTargetPosition((int)leftTarget);
        rightMotor.setTargetPosition((int)rightTarget);
        startAllEncoders();

        //if either motor doesn't need to move then don't move it
        if (!(leftTarget == 0)) {
            leftMotor.setPower(speed);
        }
        if (!(rightTarget == 0)) {
            rightMotor.setPower(speed);
        }
    }

    /**
     * returns true if left motor is done moving
     */
    public boolean leftMotorDone() {
        if (leftTarget != 0) {
            if (leftMotor.getCurrentPosition() > leftTarget) {
                return true;
            }

        }
        return false;
    }

    /**
     * returns true if right motor is done moving
     */
    public boolean rightMotorDone() {
        if (rightTarget != 0) {
            if (rightMotor.getCurrentPosition() > rightTarget) {
                return true;
            }

        }
        return false;
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
        time += 3;//add 3 seconds just to be safe
        return time;
    }

}
