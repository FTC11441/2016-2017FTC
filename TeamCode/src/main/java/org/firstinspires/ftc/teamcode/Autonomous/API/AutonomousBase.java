package org.firstinspires.ftc.teamcode.Autonomous.API;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.State;

/**
 * Created by ethan.hampton on 12/1/2016.
 * <p>
 * Interface for autonomous classes to extend
 */

public abstract class AutonomousBase {
    private RobotAuto robot = new RobotAuto();

    public void init(HardwareMap map) {
        robot.Init(map);
        robot.steps = getSteps();
    }

    public void loop() {
        switch (robot.currentState) {
            case INIT:
                robot.time.reset();
                robot.currentState = State.MOVE;
                break;

            case MOVE:
                double movementMode = robot.steps[robot.currentStep][0];
                robot.startTime = robot.time.milliseconds();//sets step start time for start of every movement
                robot.currentState = State.UPDATEMOVE;//changes state to watch for motors to be done
                //if step has default flags than do default action
                if (movementMode == 0) {
                    //sets variables to input into drive method of robot
                    double leftMotorRotations = robot.steps[robot.currentStep][1];
                    double rightMotorRotations = robot.steps[robot.currentStep][2];
                    double speed = robot.steps[robot.currentStep][3];
                    robot.setDrive(leftMotorRotations, rightMotorRotations, speed);//starts robot driving

                } else if (movementMode == -1) {//if stop flag then move to stop case
                    robot.currentState = State.STOP;//if flag = -1 then stop program

                } else if (movementMode == 1) {//mode 1 is drive via time
                    //sets variables to input into drive method of robot
                    double leftMotorTime = robot.steps[robot.currentStep][1];
                    double rightMotorTime = robot.steps[robot.currentStep][2];
                    double speed = robot.steps[robot.currentStep][3];
                    if (speed == -1) {
                        speed = Constants.DEFAULT_SPEED;//75 is default speed
                    }
                    if (speed > 1) {
                        speed = speed / 100;
                    }
                    robot.rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    robot.leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                    robot.rightMotor.setPower(speed);
                    robot.leftMotor.setPower(speed);
                    // FIXME: 2/3/2017 Fix this should be not time
                    robot.setLeftTarget((int) (leftMotorTime + robot.startTime));
                    robot.setRightTarget((int) (rightMotorTime + robot.startTime));

                } else if (movementMode == 2) {
                    double timeInMilliseconds = robot.steps[robot.currentStep][1] * 1000;
                    robot.waitTime = robot.startTime + timeInMilliseconds;
                    robot.stopMotors();
                } else {
                    //if movementMode is a custom mode then call method to start mode
                    startMovement(robot, movementMode);
                }
                break;

            case WAIT:
                //currently not using this ENUM at the moment
                if (robot.time.milliseconds() >= robot.waitTime) {
                    robot.currentState = State.MOVE;
                }
                break;

            case UPDATEMOVE:
                double movementCheckMode = robot.steps[robot.currentStep][0];
                //if mode is encoder movement then check with this method
                if (movementCheckMode == 0) {
                    if (robot.time.milliseconds() < robot.startTime + Constants.TIMEOUT) {//insure that robot is no waiting for something that won't happen
                        if ((!robot.rightMotor.isBusy() && !robot.leftMotor.isBusy()) || (robot.motorDone(robot.leftMotor) && robot.motorDone(robot.rightMotor))) {
                            robot.stopMotors();
                            robot.nextStep();
                        }
                    } else {//if we think that robot has timed out
                        robot.nextStep();
                    }

                } else if (movementCheckMode == 1) {
                    boolean leftDone = false;
                    boolean rightDone = false;
                    //if time is up for either motor then stop that motor
                    if (robot.time.milliseconds() >= robot.getLeftTarget()) {
                        robot.leftMotor.setPower(0);
                        leftDone = true;
                    }
                    if (robot.time.milliseconds() >= robot.getRightTarget()) {
                        robot.rightMotor.setPower(0);
                        rightDone = true;
                    }
                    //if both motors are done moving then move on to the next step
                    if (leftDone && rightDone) {
                        robot.nextStep();
                    }

                } else if (movementCheckMode == 2) {
                    if (robot.time.milliseconds() >= robot.waitTime) {
                        robot.nextStep();
                    }
                } else {
                    //if custom movement mode is done moving then update step and state
                    if (checkMovement(robot, movementCheckMode)) {
                        robot.nextStep();
                    }
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


    public RobotAuto getRobot() {
        return robot;
    }

    //Below here is all abstract methods


    /**
     * Is called when not using default movement and needs to check to see if movement is done
     * Automatically iterates step and state if returns true
     *
     * @param robot        robot object to get and set values from
     * @param movementMode what mode of movement should be checked
     * @return {@code true} if movement step is done returns {@code false} if movement step is not done
     */
    public abstract boolean checkMovement(RobotAuto robot, double movementMode);

    /**
     * Is called at the start of every movement
     *
     * @param robot        robot object to get and set values from
     * @param movementMode what mode of movement should be started
     */
    public abstract void startMovement(RobotAuto robot, double movementMode);


    public abstract double[][] getSteps();

}
