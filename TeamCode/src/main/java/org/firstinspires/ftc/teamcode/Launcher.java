package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by ethan.hampton on 10/5/2016.
 * <p>
 * Created to test launching of balls
 */
@TeleOp(name = "Launcher", group = "Test")
public class Launcher extends OpMode {

    private Robot robot = new Robot();

    private double power;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        power = gamepad1.left_stick_y;

        robot.leftMotor.setPower(power);
        robot.rightMotor.setPower(power);
    }
}
