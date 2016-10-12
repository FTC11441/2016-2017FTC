package org.firstinspires.ftc.teamcode.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Configuration.Robot;
import org.firstinspires.ftc.teamcode.Utils.Mode;

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

        robot.init(hardwareMap, Mode.TELEOP);
    }

    @Override
    public void loop() {
        power = gamepad1.left_stick_y;

        robot.leftMotor.setPower(power);
        robot.rightMotor.setPower(power);
    }
}
