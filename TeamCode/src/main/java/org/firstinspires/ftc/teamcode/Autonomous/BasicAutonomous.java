package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Autonomous.API.RobotConfig;

/**
 * Created by ethan.hampton on 12/17/2016.
 * Simple autonomous program
 */

public class BasicAutonomous extends OpMode {
    AutonomousBase auto;

    @Override
    public void init() {
        auto = new AutonomousBase() {
            @Override
            public boolean checkMovement(RobotConfig robot, double movementMode) {
                return false;
            }

            @Override
            public void startMovement(RobotConfig robot, double movementMode) {

            }

            @Override
            public DcMotor getRightMotor() {
                return null;
            }

            @Override
            public DcMotor getLeftMotor() {
                return null;
            }

            @Override
            public int getEncoderTicksPerRotation() {
                return 0;
            }

            @Override
            public double[][] getSteps() {
                return new double[0][];
            }
        };
        auto.init();
    }

    @Override
    public void loop() {
        auto.loop();
    }
}
