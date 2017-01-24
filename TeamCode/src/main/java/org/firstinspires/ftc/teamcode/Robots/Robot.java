package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 10/25/2016.
 * <p>
 * Robot that is current to actual robot
 */

public class Robot extends SimpleRobot {

    public Servo leftBumper = null;
    public Servo rightBumper = null;
    public Servo forklift = null;
    public Servo flipper = null;

    public DcMotor linearSlide = null;
    public DcMotor launcher = null;
    public DcMotor collector = null;
    public DcMotor tube = null;

    /**
     * the team we are on
     */
    private Team team = Team.UNKNOWN;

    /* Initialize standard Hardware interfaces */
    public void Init(HardwareMap ahwMap) {
        super.Init(ahwMap);

        leftBumper = ahwMap.servo.get(Constants.Robot.leftBumper);
        rightBumper = ahwMap.servo.get(Constants.Robot.rightBumper);
        leftBumper.setDirection(Servo.Direction.REVERSE);

        forklift = ahwMap.servo.get(Constants.Robot.forklift);
        linearSlide = ahwMap.dcMotor.get(Constants.Robot.linearSlide);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        flipper = ahwMap.servo.get(Constants.Robot.flipper);
        launcher = ahwMap.dcMotor.get(Constants.Robot.launcher);
        collector = ahwMap.dcMotor.get(Constants.Robot.ballCollector);
        tube = ahwMap.dcMotor.get(Constants.Robot.ballTube);

    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
