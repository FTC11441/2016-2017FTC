package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;

/**
 * Created by ethan.hampton on 12/17/2016.
 * Simple autonomous program
 */
@Autonomous(name = "Ramp", group = Group.TESTING)
public class Ramp extends OpMode {
    private AutonomousBase auto;

    private final double[][] steps = new double[][]{
            {0, 1, 1, Constants.DEFAULT_SPEED},
            {0, 0, 4, Constants.TURNING_SPEED},
            {0, 3, 3, Constants.DEFAULT_SPEED},
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
                return steps;
            }
        };
        auto.init(hardwareMap);
    }

    @Override
    public void loop() {
        auto.loop();
        telemetry.addLine(auto.getRobot().debug());
    }
}