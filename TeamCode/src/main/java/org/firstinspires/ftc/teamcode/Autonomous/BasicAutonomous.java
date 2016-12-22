package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;

/**
 * Created by ethan.hampton on 12/17/2016.
 * Simple autonomous program
 */

public class BasicAutonomous extends OpMode {
    private AutonomousBase auto;

    private double[][] steps = new double[][]{
            {},
            {}
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
    }
}
