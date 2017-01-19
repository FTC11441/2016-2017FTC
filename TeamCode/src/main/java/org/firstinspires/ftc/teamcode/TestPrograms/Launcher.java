package org.firstinspires.ftc.teamcode.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.SimpleRobot;

/**
 * Created by ethan.hampton on 10/5/2016.
 * <p>
 * Created to test launching of balls
 */
@TeleOp(name = "Test Launcher using Drive Motors", group = "Test")
@Disabled
public class Launcher extends OpMode {

    private SimpleRobot simpleRobot = new SimpleRobot();

    @Override
    public void init() {

        simpleRobot.Init(hardwareMap);
    }

    @Override
    public void loop() {
        double power = gamepad1.left_stick_y;

        simpleRobot.leftMotor.setPower(power);
        simpleRobot.rightMotor.setPower(power);
    }
}
