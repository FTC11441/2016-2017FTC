package org.firstinspires.ftc.teamcode.Autonomous.API;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by ethan.hampton on 12/1/2016.
 * <p>
 * An example program to show what we can do with the autonomous API
 */
@Autonomous(name = "Example Autonomous", group = "Example")
public class ExampleAutonomous extends OpMode {
    AutonomousBase auto = new AutonomousBase() {
        @Override
        public void startMovement(RobotConfig robot, double movementMode) {

        }

        @Override
        public boolean checkMovement(RobotConfig robot, double movementMode) {
            return false;
        }


        @Override
        public DcMotor getRightMotor() {
            return hardwareMap.dcMotor.get("right motor");
        }

        @Override
        public DcMotor getLeftMotor() {
            return hardwareMap.dcMotor.get("left motor");
        }

        @Override
        public int getEncoderTicksPerRotation() {
            return 1560;
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
        auto.init();
    }

    @Override
    public void loop() {
        auto.loop();
    }
}
