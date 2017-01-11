package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utils.Group;

/**
 * Created by ethan.hampton on 10/4/2016.
 * <p>
 * Very simple Teleop
 */

@TeleOp(name = "Servo Teleop", group = Group.FEATURE)
public class ServoTeleop extends OpMode {
    private Servo rightServo;
    private Servo leftServo;

    @Override
    public void init() {
        leftServo = hardwareMap.servo.get("leftServo");
        rightServo = hardwareMap.servo.get("rightServo");
        rightServo.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void loop() {
        // Run wheels in tank mode
        // In this mode the Left stick moves the left wheel forward and backwards and the right moves the right wheel
        double left = Math.abs(gamepad1.left_stick_y);
        double right = Math.abs(gamepad1.right_stick_y);

        left = left * 0.7;
        right = right * 0.7;

        //sets the robots motor power for both motors
        leftServo.setPosition(left);
        rightServo.setPosition(right);
    }
}
