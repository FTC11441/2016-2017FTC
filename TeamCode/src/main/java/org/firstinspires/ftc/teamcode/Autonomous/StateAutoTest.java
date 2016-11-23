package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.State;

/**
 * Created by ethan.hampton on 11/9/2016.
 * second attempt at state machine
 */

@Autonomous(name = "State Auto 1", group = "Test")
public class StateAutoTest extends OpMode {
    private RobotAuto robot = new RobotAuto();
    private final double WAIT = 10 * 1000;//wait up to 10 seconds else continue on to next method

    @Override
    public void init() {
        robot.Init(hardwareMap);

        robot.steps = new double[][]{
                {5, 5, 35, 0},
                //{5, 0.5, -1, 0},
                {0, 0, -1, -1}
        };
    }

    @Override
    public void loop() {
        telemetry.addData("Current step", robot.currentStep);
        telemetry.addData("Current state", robot.currentState.toString());
        telemetry.addData("Current wait", robot.waitTime);
        telemetry.addData("Current set right speed", robot.rightSpeed);
        telemetry.addData("Current set left speed", robot.leftSpeed);
        telemetry.addData("Current actual right speed", robot.rightMotor.getPower());
        telemetry.addData("Current actual left speed", robot.leftMotor.getPower());
        telemetry.update();
        switch (robot.currentState) {
            case INIT:
                robot.time.reset();
                robot.currentState = State.MOVE;
                break;

            case MOVE:
                //if step has default flags than do default action
                if (robot.steps[robot.currentStep][3] == 0) {
                    //sets variables to input into drive method of robot
                    double leftMotorRotations = robot.steps[robot.currentStep][0];
                    double rightMotorRotations = robot.steps[robot.currentStep][1];
                    int speed = (int) robot.steps[robot.currentStep][2];

                    robot.setDrive(leftMotorRotations, rightMotorRotations, speed);//starts robot driving
                    robot.startTime = robot.time.milliseconds();//sets step start time
                    robot.currentState = State.CHECK;//changes state to watch for motors to be done

                    //if stop flag then move to stop case
                } else if (robot.steps[robot.currentStep][3] == -1) {
                    robot.currentState = State.STOP;//if flag = -1 then stop program
                }
                break;

            case WAIT:
                if (robot.time.milliseconds() >= robot.waitTime) {
                    robot.currentState = State.MOVE;
                }
                break;

            case CHECK:
                if (robot.time.milliseconds() < robot.startTime + WAIT) {//insure that robot is no waiting for something that won't happen
                    if (!robot.rightMotor.isBusy() && !robot.leftMotor.isBusy()) {
                        //set proper next step
                        robot.currentState = State.WAIT;
                        //set time to wait for next step
                        robot.waitTime = robot.time.milliseconds() + 1000;
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
}
