package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

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
@Autonomous(name = "Basic Auto", group = Group.TESTING)
public class TeamAuto extends OpMode {
    private AutonomousBase auto;

    private final double[][] redSteps = new double[][]{
            {0, 1, 1, 2},/*
            {0, -1, -1, -1},
            {0, -1, 1, -1},
            {0, 1, -1, -1},
            {2, 5},//wait
            {0, 1, 1, -1},
            {0, -1, -1, -1},*/
            {-1}
    };
    private final double[][] blueSteps = new double[][]{
            {0, 1, 1, 2},
            {-1}
    };

    @Override
    public void init() {
        auto = new AutonomousBase() {
            @Override
            public boolean checkMovement(RobotAuto robot, double movementMode) {
                return false;
            }

            @Override
            public void startMovement(RobotAuto robot, double movementMode) {

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

        // TODO: 1/23/2017 add team detecting code
    }

    @Override
    public void loop() {
        auto.loop();
    }
}
