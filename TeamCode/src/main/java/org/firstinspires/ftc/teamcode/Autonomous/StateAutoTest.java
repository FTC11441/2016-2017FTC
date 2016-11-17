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

    @Override
    public void init() {
        robot.Init(hardwareMap);

        robot.steps = new double[][]{
                {5, -5, -1, 0},
                {7, 2, -1, 0},
                {0, 0, -1, -1}
        };
    }

    @Override
    public void loop() {
        telemetry.addData("Current step", robot.currentStep);
        telemetry.addData("Current state", robot.currentState.toString());
        telemetry.addData("Current wait", robot.waitTime);
        telemetry.addData("Current right speed", robot.rightSpeed);
        telemetry.addData("Current left speed", robot.leftSpeed);
        telemetry.update();
        switch (robot.currentState) {
            case INIT:
                robot.time.reset();
                robot.currentState = State.MOVE;
                break;
            case MOVE:
                //if step has default flags than do default action
                if (robot.steps[robot.currentStep][3] == 0) {
                    double leftMotorRotations = robot.steps[robot.currentStep][0];
                    double rightMotorRotations = robot.steps[robot.currentStep][1];
                    int speed = (int) robot.steps[robot.currentStep][2];
                    robot.setDrive(leftMotorRotations, rightMotorRotations, speed);
                    robot.startTime = robot.time.milliseconds();
                    robot.currentState = State.CHECK;
                    //if stop flag then move to stop case
                } else if (robot.steps[robot.currentStep][3] == -1) {
                    robot.currentState = State.STOP;
                }
                break;
            case WAIT:
                if (robot.time.milliseconds() >= robot.waitTime) {
                    robot.currentState = State.MOVE;
                }
                break;
            case CHECK:
                if (!robot.rightMotor.isBusy() && !robot.leftMotor.isBusy()) {// TODO: 11/16/2016 Implement wait time in order to stop program from catching 
                    //set proper next step
                    robot.currentState = State.WAIT;
                    //set time to wait for next step
                    robot.waitTime = robot.time.milliseconds() + 1000;
                    //adds one to current step so that we continue with the program
                    robot.currentStep++;
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
