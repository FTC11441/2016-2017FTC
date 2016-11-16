package org.firstinspires.ftc.teamcode.Robots;

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

    // if 1 then red is on the left and blue is on the right,
    // if 2 then red is on the right and blue is on the left,
    // if 0 then we can assume we do not know the state of the variable
    private byte beaconGears = 0;
    private byte beaconTools = 0;
    private byte beaconLegos = 0;
    private byte beaconWheels = 0;

    /**
     * the team we are on
     */
    private Team team = Team.UNKNOWN;

    /* Initialize standard Hardware interfaces */
    public void Init(HardwareMap ahwMap) {
        super.Init(ahwMap);
        leftBumper = ahwMap.servo.get("Left bumper");
        rightBumper = ahwMap.servo.get("Right bumper");
        leftBumper.setDirection(Servo.Direction.REVERSE);
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public byte getBeaconGears() {
        return beaconGears;
    }

    public void setBeaconGears(byte beaconGears) {
        this.beaconGears = beaconGears;
    }

    public byte getBeaconTools() {
        return beaconTools;
    }

    public void setBeaconTools(byte beaconTools) {
        this.beaconTools = beaconTools;
    }

    public byte getBeaconLegos() {
        return beaconLegos;
    }

    public void setBeaconLegos(byte beaconLegos) {
        this.beaconLegos = beaconLegos;
    }

    public byte getBeaconWheels() {
        return beaconWheels;
    }

    public void setBeaconWheels(byte beaconWheels) {
        this.beaconWheels = beaconWheels;
    }

}
