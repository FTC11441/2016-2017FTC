package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;

/**
 * Created by ethan.hampton on 1/23/2017.
 * <p>
 * adds team capabilities to autonomous
 */
@Autonomous(name = "Cap Ball Straight Distance", group = Group.RELEASE)
public class CapBallStraight2 extends OpMode {
    private AutonomousBase auto;

    private final double[][] steps = new double[][]{
            {0, 3.5, 3.5, -1},//forward
            {5},//launch
            {0, 2.5, 2.5, -1},//forward
            {-1}
    };


    @Override
    public void init() {
        auto = new AutonomousBase() {

            @Override
            public boolean checkMovement(RobotAuto robot, double movementMode) {
                 if (movementMode == 5) {
                    if (!robot.launcher.isBusy()) {//stop checking motors and stop them if we are done moving
                        robot.launcher.setPower(0);
                        return true;
                    }
                } else {
                    return true;
                }


                return false;
            }

            @Override
            public void startMovement(RobotAuto robot, double movementMode) {
                if (movementMode == 5) {
                    robot.launcher.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    //the LAUNCHER has a gear ratio of 2 to 1
                    robot.launcher.setTargetPosition(robot.launcher.getTargetPosition() + (Constants.Teleop.LAUNCHER_ROTATIONS));
                    robot.launcher.setPower(0.8);
                }
            }

            @Override
            public double[][] getSteps() {
                return steps;
            }
        };
        auto.init(hardwareMap);
        auto.getRobot().setEncoderTicksPerRotation(Constants.ENCODER_TICKS_PER_ROTATION_60);


        auto.getRobot().leftMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
        auto.getRobot().rightMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
    }

    @Override
    public void loop() {
        auto.loop();
    }
}
