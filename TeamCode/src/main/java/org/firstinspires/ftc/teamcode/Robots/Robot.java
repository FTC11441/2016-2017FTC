package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

    public Servo bumper;
    public Servo forklift;
    public Servo flipper;

    public DcMotor linearSlide;
    public DcMotor launcher;
    public DcMotor collector;
    public DcMotor tube;

    /**
     * the team we are on
     */
    private Team team = Team.UNKNOWN;

    /* Initialize standard Hardware interfaces */
    public void Init(HardwareMap ahwMap) {
        super.Init(ahwMap);

        bumper = ahwMap.servo.get(Constants.Robot.BUMPER);
        bumper.setDirection(Servo.Direction.REVERSE);

        forklift = ahwMap.servo.get(Constants.Robot.FORKLIFT);
        linearSlide = ahwMap.dcMotor.get(Constants.Robot.LINEAR_SLIDE);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        flipper = ahwMap.servo.get(Constants.Robot.FLIPPER);
        launcher = ahwMap.dcMotor.get(Constants.Robot.LAUNCHER);
        collector = ahwMap.dcMotor.get(Constants.Robot.BALL_COLLECTOR);
        collector.setDirection(DcMotorSimple.Direction.REVERSE);
        tube = ahwMap.dcMotor.get(Constants.Robot.BALL_TUBE);

    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
