package org.firstinspires.ftc.teamcode.API;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by ethan.hampton on 12/1/2016.
 * <p>
 * Interface for autonomous classes to extend
 */

public abstract class Autonomous extends OpMode {
    private RobotConfig robot = new RobotConfig();
    private double TIMEOUT = 10000;
    @Override
    public void init() {
        robot.Init(getRightMotor(hardwareMap), getLeftMotor(hardwareMap), getEncoderTicksPerRotation());
        robot.steps = getSteps();
    }

    @Override
    public void loop() {
        switch (robot.currentState) {
            case INIT:
                robot.time.reset();
                robot.currentState = State.MOVE;
                break;

            case MOVE:
                double movementMode = robot.steps[robot.currentStep][0];
                robot.startTime = robot.time.milliseconds();//sets step start time for start of every movement
                //if step has default flags than do default action
                if (movementMode == 0) {
                    //sets variables to input into drive method of robot
                    double leftMotorRotations = robot.steps[robot.currentStep][1];
                    double rightMotorRotations = robot.steps[robot.currentStep][2];
                    int speed = (int) robot.steps[robot.currentStep][3];

                    robot.setDrive(leftMotorRotations, rightMotorRotations, speed);//starts robot driving
                    robot.currentState = State.UPDATEMOVE;//changes state to watch for motors to be done

                    //if stop flag then move to stop case
                } else if (movementMode == -1) {
                    robot.currentState = State.STOP;//if flag = -1 then stop program
                }else if (movementMode == 1){
                    // TODO: 12/7/2016 Implement drive by time
                } else if (movementMode == 2) {
                    // TODO: 12/7/2016 Implement wait method
                }
                break;

            case WAIT:
                //currently not using this ENUM
                if (robot.time.milliseconds() >= robot.waitTime) {
                    robot.currentState = State.MOVE;
                }
                break;

            case UPDATEMOVE:
                // TODO: 12/7/2016 Add system to do individual check methods
                if (robot.time.milliseconds() < robot.startTime + TIMEOUT) {//insure that robot is no waiting for something that won't happen
                    if (!robot.rightMotor.isBusy() && !robot.leftMotor.isBusy()) {
                        /*
                        //NOT USING WAIT METHOD RIGHT NOW BECAUSE WE THINK IT IS UNNECESSARY
                        //set proper next step
                        robot.currentState = State.WAIT;
                        //set time to wait for next step
                        robot.waitTime = robot.time.milliseconds() + 1000;
                        */
                        robot.currentState = State.MOVE;
                        //adds one to current step so that we continue with the program
                        robot.currentStep++;
                    }
                } else {//if we think that robot has timed out
                    robot.currentStep++;//update to current set
                    robot.currentState = State.MOVE;//we can skip the wait method and go directly to move method
                }
                break;

            case STOP:
                //stop and reset robot
                robot.rightMotor.setPower(0);
                robot.leftMotor.setPower(0);
                robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                break;
        }
    }

    //Below here is all abstract methods

    /**
     * @return right DC motor
     */
    public abstract DcMotor getRightMotor(HardwareMap hwMap);

    /**
     * @return left DC motor
     */
    public abstract DcMotor getLeftMotor(HardwareMap hwMap);

    /**
     * @return encoder ticks per rotation
     */
    public abstract int getEncoderTicksPerRotation();


    public abstract double[][] getSteps();

}
