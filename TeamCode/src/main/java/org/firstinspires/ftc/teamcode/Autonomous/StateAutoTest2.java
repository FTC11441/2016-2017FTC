package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Configuration.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.State;

/**
 * Created by ethan.hampton on 11/9/2016.
 * second attempt at state machine
 */

@Autonomous(name = "State Auto 2", group = "Test")
public class StateAutoTest2 extends OpMode {
    private RobotAuto robot = new RobotAuto();

    @Override
    public void init() {
        robot.init(hardwareMap);

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
                double leftMotorRotations = robot.steps[robot.currentStep][0];
                double rightMotorRotations = robot.steps[robot.currentStep][1];
                int speed = (int)robot.steps[robot.currentStep][2];
                robot.setDrive(leftMotorRotations,rightMotorRotations,speed);
                robot.currentState = State.CHECK;
                break;
            case CHECK:
                if(robot.leftMotorDone()){
                    robot.leftMotor.setPower(0);
                }
                if(robot.rightMotorDone()){
                    robot.rightMotor.setPower(0);
                }
                if(robot.rightMotorDone() && robot.leftMotorDone()){
                    robot.currentState = State.MOVE;
                }
                break;
            case STOP:

                break;
        }
    }
}
