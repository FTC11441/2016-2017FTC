package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 10/25/2016.
 * <p>
 * Robot that is current to actual robot
 */

public class Robot extends SimpleRobot {

    public Servo leftBumper = null;
    public Servo rightBumper = null;
    public DcMotor leftLauncher = null;
    public DcMotor rightLauncher = null;

    /**
     * the team we are on
     */
    private Team team = Team.UNKNOWN;

    /* Initialize standard Hardware interfaces */
    public void Init(HardwareMap ahwMap) {
        super.Init(ahwMap);
        //leftBumper = ahwMap.servo.get("Left bumper");
        //rightBumper = ahwMap.servo.get("Right bumper");
        //leftBumper.setDirection(Servo.Direction.REVERSE);


        leftLauncher = ahwMap.dcMotor.get("left launcher");
        rightLauncher = ahwMap.dcMotor.get("right launcher");
        rightLauncher.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
