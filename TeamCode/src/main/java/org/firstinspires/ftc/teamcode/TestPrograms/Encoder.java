package org.firstinspires.ftc.teamcode.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.SimpleRobot;

/**
 * Created by ethan.hampton on 10/12/2016.
 * test one rotation of encoder
 */
@TeleOp(name = "Encoder", group = "Test")
public class Encoder extends OpMode {
    private SimpleRobot simpleRobot = new SimpleRobot();

    private double power;

    @Override
    public void init() {
        simpleRobot.Init(hardwareMap);
    }

    @Override
    public void loop() {
        power = gamepad1.left_stick_y;

        telemetry.addData("Status", simpleRobot.rightMotor.getCurrentPosition() + "  " + simpleRobot.leftMotor.getCurrentPosition());    //
        telemetry.update();

        simpleRobot.leftMotor.setPower(power);
        simpleRobot.rightMotor.setPower(power);
    }
}
