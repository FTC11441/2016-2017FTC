package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 2/21/2017.
 * follows line
 */

public class FollowLine extends Module {
    private Team team = Team.UNKNOWN;
    private double forward = 0.1;
    private double turn = 0.3;

    protected FollowLine() {
        super(null);

    }

    @Override
    public void checkMovement(RobotAuto robot) {
        int light = robot.floorReflection;
        final int add = Constants.LIGHT_CHANGE;
        int value = light + add;
            int[] left = robot.colorSensors.getCRGB(Constants.Robot.LEFT_COLOR);
            int[] right = robot.colorSensors.getCRGB(Constants.Robot.RIGHT_COLOR);
            //both are off line
            if (left[0] < value && right[0] < value) {
                robot.leftMotor.setPower(forward);
                robot.rightMotor.setPower(forward);

                //left is on line
            } else if (left[0] > value && right[0] < value) {
                robot.leftMotor.setPower(0);
                robot.rightMotor.setPower(turn);

                //right is on line
            } else if (left[0] > value && right[0] < value) {
                robot.leftMotor.setPower(turn);
                robot.rightMotor.setPower(0);

                //both on line
            } else if (left[0] > value && right[0] > value) {
                robot.leftMotor.setPower(0);
                robot.rightMotor.setPower(0);

                robot.nextStep();
            }
    }

    @Override
    public void startMovement(RobotAuto robot) {
        this.team = robot.getTeam();
        if (this.team == Team.BLUE){
            forward = -forward;
            turn = -turn;
        }
        //start robot moving slowly to align with line
        robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftMotor.setPower(forward);
        robot.rightMotor.setPower(forward);
    }
}
