package org.firstinspires.ftc.teamcode.API;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by ethan.hampton on 12/1/2016.
 * <p>
 * An example program to show what we can do with the autonomous API
 */

public class ExampleAutonomous extends Autonomous {


    @Override
    public DcMotor getRightMotor(HardwareMap hwMap) {
        return hwMap.dcMotor.get("right motor");
    }

    @Override
    public DcMotor getLeftMotor(HardwareMap hwMap) {
        return hwMap.dcMotor.get("left motor");
    }

    @Override
    public int getEncoderTicksPerRotation() {
        return 1560;
    }

    @Override
    public double[][] getSteps() {
        return new double[][]{
                {0,0},
                {0,0}
        };
    }
}
