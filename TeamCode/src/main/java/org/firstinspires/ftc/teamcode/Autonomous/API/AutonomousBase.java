package org.firstinspires.ftc.teamcode.Autonomous.API;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.State;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 12/1/2016.
 * <p>
 * Interface for autonomous classes to extend
 */

public class AutonomousBase {
    private RobotAuto robot = new RobotAuto();
    private Module module;

    public void init(HardwareMap map) {
        robot.Init(map);
        robot.steps = getSteps();
        robot.setTeam(Team.UNKNOWN);
    }

    public void init(HardwareMap map,Team team) {
        init(map);
        robot.setTeam(team);
    }
    public void init(HardwareMap map,Team team, Module[] steps) {
        init(map,team);
        robot.steps = steps;
    }

    public void loop() {
        switch (robot.currentState) {
            case INIT:
                robot.time.reset();
                robot.currentState = State.MOVE;
                break;

            case MOVE:
                robot.startTime = robot.time.milliseconds();//sets step start time for start of every movement
                robot.currentState = State.UPDATEMOVE;//changes state to watch for motors to be done

                module = robot.steps[robot.currentStep];
                module.startMovement(robot);
                break;

            case WAIT:
                //currently not using this ENUM at the moment
                if (robot.time.milliseconds() >= robot.waitTime) {
                    robot.currentState = State.MOVE;
                }
                break;

            case UPDATEMOVE:
                module.checkMovement(robot);
                break;

            case STOP:
                //stop and reset robot
                robot.rightMotor.setPower(0);
                robot.leftMotor.setPower(0);
                robot.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                break;
        }
    }


    public RobotAuto getRobot() {
        return robot;
    }

    public Module[] getSteps(){
        return null;
    }

}
