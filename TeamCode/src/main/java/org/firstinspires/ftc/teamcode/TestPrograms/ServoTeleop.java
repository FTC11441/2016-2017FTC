package org.firstinspires.ftc.teamcode.TestPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;

/**
 * Created by ethan.hampton on 10/4/2016.
 * <p>
 * Very simple Teleop
 */

@TeleOp(name = "Servo Only", group = Group.FEATURE)
@Disabled
public class ServoTeleop extends OpMode {
    private Servo bumper;

    @Override
    public void init() {
        bumper = hardwareMap.servo.get(Constants.Robot.BUMPER);

    }

    @Override
    public void loop() {
        bumper.setPosition(gamepad1.right_stick_y );
    }
}
