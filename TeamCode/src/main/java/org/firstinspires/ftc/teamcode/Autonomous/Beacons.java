package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Autonomous.API.BeaconFinderUtils;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 1/23/2017.
 * <p>
 * adds team capabilities to autonomous
 */
@Autonomous(name = "Beacons", group = Group.DEV)
public class Beacons extends OpMode {
    private AutonomousBase auto;

    private final double[][] redSteps = new double[][]{
            {0, 1.5, 1.5, Constants.DEFAULT_SPEED},//forward
            {0, 2, 5, Constants.TURNING_SPEED},//slight turn
            {0, 1.67, 1.67, Constants.DEFAULT_SPEED},//move forward again
            {0, 3.5, 0.3, Constants.TURNING_SPEED},//turn to go parallel to the wall
            {3},//align with the first beacon
            {0, -0.2, -0.2, Constants.DEFAULT_SPEED},//align with left beacon side
            {4, 0.5},//move pusher forward
            {6},//read left beacon color and store it
            {7},//push or move then push
            {2, 1},//wait a little
            {0, 3, 3, Constants.DEFAULT_SPEED},//move forward again to next beacon and repeat
            {3},//align with the second beacon
            {0, -0.2, -0.2, Constants.DEFAULT_SPEED},//align with left beacon side
            {4, 0.5},//move pusher forward
            {6},//read left beacon color and store it
            {7},//push or move then push
            {-1}
    };
    private final double[][] blueSteps = new double[][]{
            //{0, -1, -1, -1},
            {0,-redSteps[0][1],-redSteps[0][2],redSteps[0][3]},
            {0,-redSteps[1][2],-redSteps[1][1],redSteps[1][3]},
            {0,-redSteps[2][1],-redSteps[2][2],redSteps[2][3]},
            {0,-redSteps[3][2],-redSteps[3][1],redSteps[3][3]},
            {-1}
    };

    @Override
    public void init() {
        auto = new AutonomousBase() {
            private Team leftBeacon = Team.UNKNOWN;

            @Override
            public boolean checkMovement(RobotAuto robot, double movementMode) {
                return BeaconFinderUtils.checkMovement(robot, movementMode);
            }

            @Override
            public void startMovement(RobotAuto robot, double movementMode) {
                BeaconFinderUtils.startMovement(robot, movementMode, leftBeacon, this.getSteps());
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
        auto.getRobot().setEncoderTicksPerRotation(Constants.ENCODER_TICKS_PER_ROTATION_40);

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
        telemetry.addLine(auto.getRobot().debug());
    }
}
