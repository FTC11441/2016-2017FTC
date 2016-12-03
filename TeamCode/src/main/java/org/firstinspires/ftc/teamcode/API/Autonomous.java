package org.firstinspires.ftc.teamcode.API;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by ethan.hampton on 12/1/2016.
 *
 * Interface for autonomous classes to extend
 */

public abstract class Autonomous extends OpMode {
    private RobotConfig robot = new RobotConfig();

    @Override
    public void init() {
        robot.Init(getRightMotor(),getLeftMotor(),getEncoderTicksPerRotation());
        robot.steps = getSteps();
    }

    @Override
    public void loop() {

    }

    //Below here is all abstract methods
    /**
     * @return right DC motor
     */
    public abstract DcMotor getRightMotor();

    /**
     *
     * @return left DC motor
     */
    public abstract DcMotor getLeftMotor();

    /**
     *
     * @return encoder ticks per rotation
     */
    public abstract int getEncoderTicksPerRotation();


    public abstract double[][] getSteps();

}
