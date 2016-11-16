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

@Autonomous(name = "State Auto 2", group = "Test")
public class StateAutoTest extends OpMode {
    private RobotAuto robot = new RobotAuto();

    @Override
    public void init() {
        robot.Init(hardwareMap);

        robot.steps = new double[][]{
                {0, 0},
                {0, 0},
                {0, 0}
        };
    }

    @Override
    public void loop() {
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
                    robot.currentState = State.CHECK;
                    //if stop flag then move to stop case
                } else if (robot.steps[robot.currentStep][3] == -1) {
                    robot.currentState = State.STOP;
                }
                break;
            case CHECK:
                if (!robot.leftMotor.isBusy()) {
                    robot.leftMotor.setPower(0);
                }
                if (!robot.rightMotor.isBusy()) {
                    robot.rightMotor.setPower(0);
                }
                if (!robot.rightMotor.isBusy() && !robot.leftMotor.isBusy()) {
                    robot.currentState = State.MOVE;
                    //adds one to current step so that we continue with the program
                    robot.currentStep++;
                }
                break;
            case STOP:
                robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                break;
        }
    }
}
