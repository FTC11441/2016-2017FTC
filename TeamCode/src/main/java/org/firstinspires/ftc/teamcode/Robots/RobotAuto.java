package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.MultiplexColorSensor;
import org.firstinspires.ftc.teamcode.Utils.State;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 10/11/2016.
 * <p>
 * SimpleRobot setup for autonomous
 */

public class RobotAuto extends Robot {

    //what time to wait till
    public double waitTime = 0;
    //what time that current step started
    public double startTime = 0;

    //the step we are on
    public int currentStep = 0;

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
    public Module[] steps;
    //our current state
    public State currentState = State.INIT;

    //The target positions for both motors
    private int leftTarget = 0;
    private int rightTarget = 0;
    public double rightSpeed = 0;
    public double leftSpeed = 0;


    private int[] ports = {0, 1, 2};
    public MultiplexColorSensor colorSensors;
    public int leftFloorReflection = 1200;
    public int rightFloorReflection = 1200;

    private int encoderTicksPerRotation = Constants.Robot.MOTOR_ENCODERS_USED;
    public Team leftBeacon = Team.UNKNOWN;
    /* Initialize standard Hardware interfaces */
    public void Init(HardwareMap ahwMap) {
        super.Init(ahwMap);

        int milliSeconds = 48;
        colorSensors = new MultiplexColorSensor(ahwMap, "mux", "ada",
                ports, milliSeconds,
                MultiplexColorSensor.GAIN_16X);
        colorSensors.startPolling();

        // Set all motors to run with encoders to use encoders to track position
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //must reset both else it turns in a circle
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    /**
     * default simple drive
     *
     * @param leftRotations  how many rotations left motor should go, can be negative
     * @param rightRotations how many rotations left motor should go, can be negative
     * @param speed          -1 if using default speed should start to be {@link Deprecated}, assumes speed is always positive
     */
    public void setDrive(double leftRotations, double rightRotations, double speed) {
        //if speed isn't specified then use default speed
        if (speed == -1) {
            speed = Constants.DEFAULT_SPEED;
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
        leftTarget = (int) (leftRotations * encoderTicksPerRotation);
        rightTarget = (int) (rightRotations * encoderTicksPerRotation);

        resetAllEncoders();
        leftMotor.setTargetPosition(leftTarget);
        rightMotor.setTargetPosition(rightTarget);
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

    @Override
    public String debug() {
        return "Left target:  " + leftTarget + " Right target:  " + rightTarget + " \n"
                + "Left current: " + leftMotor.getCurrentPosition() + " Right current: " + rightMotor.getCurrentPosition() + " \n"
                + "Left speed: " + leftMotor.getPower() + " Right speed: " + leftMotor.getPower() + "\n"
                + "Left Light: " + colorSensors.getCRGB(Constants.Robot.LEFT_COLOR)[0] + " Right Light: " + colorSensors.getCRGB(Constants.Robot.RIGHT_COLOR)[0] + "\n"
                + "Left floor default: " + leftFloorReflection+ " Right floor default: " + rightFloorReflection;
    }

    public void nextStep() {
        currentStep++;//update to current set
        currentState = State.MOVE;//we can skip the wait method and go directly to move method
    }

    public boolean motorDone(DcMotor motor) {
        double current = Math.abs(motor.getCurrentPosition());
        double target = Math.abs(motor.getTargetPosition());

        return current + 15 >= target || current - 15 >= target;
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
        int time = rotations * (Constants.MAX_MOTOR_RPM / 60) * (speed / 100);
        time += 2;//add 2 seconds just to be safe
        return time;
    }

    public int getRightTarget() {
        return rightTarget;
    }

    public void setRightTarget(int rightTarget) {
        this.rightTarget = rightTarget;
    }

    public int getLeftTarget() {
        return leftTarget;
    }

    public void setLeftTarget(int leftTarget) {
        this.leftTarget = leftTarget;
    }

    public int getEncoderTicksPerRotation() {
        return encoderTicksPerRotation;
    }

    public void setEncoderTicksPerRotation(int ENCODER_TICKS_PER_ROTATION) {
        this.encoderTicksPerRotation = ENCODER_TICKS_PER_ROTATION;
    }

}
