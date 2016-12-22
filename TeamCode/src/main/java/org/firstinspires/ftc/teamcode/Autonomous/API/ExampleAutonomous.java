package org.firstinspires.ftc.teamcode.Autonomous.API;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robots.RobotAuto;

/**
 * Created by ethan.hampton on 12/1/2016.
 * <p>
 * An example program to show what we can do with the autonomous API
 */
@Autonomous(name = "Example Autonomous", group = "Example")
@Deprecated
@Disabled
public class ExampleAutonomous extends OpMode {
    AutonomousBase auto = new AutonomousBase() {
        @Override
        public boolean checkMovement(RobotAuto robot, double movementMode) {
            return false;
        }

        @Override
        public void startMovement(RobotAuto robot, double movementMode) {

        }

        @Override
        public double[][] getSteps() {
            return new double[][]{
                    {0, 1, 1, 75},
                    {0, -1, 1, 75},
                    {-1}
            };
        }
    };


    @Override
    public void init() {
        auto.init(hardwareMap);
    }

    @Override
    public void loop() {
        auto.loop();
    }
}
