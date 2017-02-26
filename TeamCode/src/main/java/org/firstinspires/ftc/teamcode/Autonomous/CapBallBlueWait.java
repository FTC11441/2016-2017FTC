package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.EncoderMove;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.LaunchParticle;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Stop;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;
import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 1/23/2017.
 * <p>
 * adds team capabilities to autonomous
 */
@Autonomous(name = "Cap Ball Blue WAIT", group = Group.RELEASE)
public class CapBallBlueWait extends OpMode {
    private AutonomousBase auto;

    private final Module[] steps = new Module[]{
            new Wait(15),//wait before starting so the other team can go
            new EncoderMove(1.7, 1.7, Constants.DEFAULT_SPEED),//forward
            new EncoderMove(1.05, -1.05, Constants.TURNING_SPEED),//turn
            new EncoderMove(0.5, 0.5, Constants.DEFAULT_SPEED),//forward
            new LaunchParticle(),//launch
            new EncoderMove(1.3, 1.3, Constants.DEFAULT_SPEED),//forward
            new Stop()
/*
            {0, 3.5, 3.5, -1},//forward
            {0, 1.1, -1.1, -1},//turn right
            {0, 0.5, 0.5, -1},//forward
            {5},//launch
            {0, 3, 3, -1},//forward
            {-1}
 */
    };


    @Override
    public void init() {
        auto = new AutonomousBase();
        auto.init(hardwareMap, Team.BLUE, steps);

        auto.getRobot().setEncoderTicksPerRotation(Constants.Robot.MOTOR_ENCODERS_USED);

        auto.getRobot().leftMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
        auto.getRobot().rightMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
    }

    @Override
    public void loop() {
        auto.loop();
    }
}
