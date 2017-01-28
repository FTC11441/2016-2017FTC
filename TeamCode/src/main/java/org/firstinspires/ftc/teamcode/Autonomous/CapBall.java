package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 1/23/2017.
 * <p>
 * adds team capabilities to autonomous
 */
@Autonomous(name = "Cap Ball", group = Group.RELEASE)
public class CapBall extends OpMode {
    private AutonomousBase auto;

    private final double[][] redSteps = new double[][]{
            {0, 3, 3, -1},//forward
            {5},//launch
            {0, 4, 4, -1},//forward
            {-1}
    };
    private final double[][] blueSteps = new double[][]{
            //{0, -1, -1, -1},
            {-1}
    };

    @Override
    public void init() {
        auto = new AutonomousBase() {
            private Team leftBeacon = Team.UNKNOWN;

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
                    //the launcher has a gear ratio of 2 to 1
                    robot.launcher.setTargetPosition(robot.launcher.getTargetPosition() + (Constants.Teleop.LAUNCHER_ROTATIONS));
                    robot.launcher.setPower(0.75);
                }
            }

            @Override
            public double[][] getSteps() {
                if (auto.getRobot().getTeam().equals(Team.BLUE)) {
                    return blueSteps;
                } else {
                    return redSteps;
                }
            }
        };
        auto.init(hardwareMap);
        auto.getRobot().setEncoderTicksPerRotation(Constants.ENCODER_TICKS_PER_ROTATION_60);

        if (auto.getRobot().teamTouch.isPressed()) {
            auto.getRobot().setTeam(Team.BLUE);
        } else {
            auto.getRobot().setTeam(Team.RED);
        }

        auto.getRobot().leftMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
        auto.getRobot().rightMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
    }

    @Override
    public void loop() {
        auto.loop();
    }
}
