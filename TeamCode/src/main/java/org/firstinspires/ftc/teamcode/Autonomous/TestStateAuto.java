package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Configuration.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Motor;

/**
 * Created by ethan.hampton on 10/18/2016.
 * <p>
 * test of states
 */
@Autonomous(name = "State Auto", group = "Test")
public class TestStateAuto extends OpMode {

    //Left motor is first var, right motor is second
    int[][] steps = new int[][]{
            {0, 0},
            {0, 0},
            {0, 0}
    };
    private int currentState = 0;

    private RobotAuto robot = new RobotAuto();

    @Override
    public void init() {
        robot.Init(hardwareMap);
    }

    @Override
    public void loop() {
        switch (currentState) {
            case 0:
                //simpleInit state
                robot.resetAllEncoders();
                currentState = 1;
                startState(1);
                break;
            case 1:
                if (doneWithState(1)) {

                } else {

                }

                break;
            case 2:

                break;

        }

    }

    private void startState(int i) {

    }

    private boolean doneWithState(int i) {
        //  return (!mLeftMotor.isBusy() && !mRightMotor.isBusy());
        return ((Math.abs(robot.leftMotor.getCurrentPosition() - steps[i][Motor.LEFT.getValue()]) < 10) &&
                (Math.abs(robot.rightMotor.getCurrentPosition() - steps[i][Motor.RIGHT.getValue()]) < 10));
    }
}
