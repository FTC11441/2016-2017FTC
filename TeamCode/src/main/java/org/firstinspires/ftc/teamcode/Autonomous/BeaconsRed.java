package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Autonomous.API.Module;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.CheckBeaconColor;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.EncoderMove;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.FollowLine;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.PressCorrectBeacon;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.PushBeacon;
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
@Autonomous(name = "Beacons Red", group = Group.DEV)
public class BeaconsRed extends OpMode {
    private AutonomousBase auto;

    private final Module[] steps = new Module[]{
            new EncoderMove( -1, 1, Constants.TURNING_SPEED),//turn to go parallel to the wall
            new EncoderMove( 3, 3, Constants.DEFAULT_SPEED),//move forward
            new EncoderMove( 1, -1, Constants.TURNING_SPEED),//turn to go parallel to the wall
            new FollowLine(),//align with the first beacon
            new EncoderMove( -0.2, -0.2, Constants.TURNING_SPEED),//align with left beacon side
            new PushBeacon(0.6),//move pusher forward
            new CheckBeaconColor(),//read left beacon color and store it
            new PressCorrectBeacon(),//push or move then push
            new Wait(1),//wait a little
            new PushBeacon( Constants.Teleop.BUMPER_IN_POSITION),//reset pusher

            new EncoderMove( 0, 0.1, Constants.TURNING_SPEED),//turn to go parallel to the wall
            new EncoderMove( 2, 2, Constants.DEFAULT_SPEED),//move forward again to next beacon and repeat

            new FollowLine(),//align with the first beacon
            new EncoderMove( -0.2, -0.2, Constants.TURNING_SPEED),//align with left beacon side
            new PushBeacon(0.6),//move pusher forward
            new CheckBeaconColor(),//read left beacon color and store it
            new PressCorrectBeacon(),//push or move then push
            new Wait(1),//wait a little
            new PushBeacon( Constants.Teleop.BUMPER_IN_POSITION),//reset pusher
            new Stop()
    };

    /*
    {0, 0, 2.75, Constants.TURNING_SPEED},//turn to go parallel to the wall
    {0, 5, 5, Constants.DEFAULT_SPEED},//move forward
    {0, 2.35, 0, Constants.TURNING_SPEED},//turn to go parallel to the wall
    {3},//align with the first beacon
    {0, -0.2, -0.2, Constants.TURNING_SPEED},//align with left beacon side
    {4, 0.6},//move pusher forward
    {6},//read left beacon color and store it
    {7},//push or move then push
    {2, 1},//wait a little
    {4, Constants.Teleop.BUMPER_IN_POSITION},//reset pusher
    {0, 0, 0.1, Constants.TURNING_SPEED},//turn to go parallel to the wall
    {0, 4, 4, Constants.DEFAULT_SPEED},//move forward again to next beacon and repeat
    {3},//align with the second beacon
    {0, -0.2, -0.2, Constants.TURNING_SPEED},//align with left beacon side
    {4, 0.5},//move pusher forward
    {6},//read left beacon color and store it
    {7},//push or move then push
    {2, 1},//wait a little
    {4, Constants.Teleop.BUMPER_IN_POSITION},//reset pusher
    {-1}
    */


    @Override
    public void init() {
        auto = new AutonomousBase();
        auto.init(hardwareMap, Team.RED, steps);

        auto.getRobot().setEncoderTicksPerRotation(Constants.Robot.MOTOR_ENCODERS_USED);

        auto.getRobot().leftMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
        auto.getRobot().rightMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
    }

    private boolean floor = false;

    @Override
    public void loop() {
        if (!floor) {
            auto.getRobot().colorSensors.startPolling();
            //get average of floor color so that we can accurately detect the floor
            auto.getRobot().floorReflection = (auto.getRobot().colorSensors.getCRGB(Constants.Robot.LEFT_COLOR)[0] + auto.getRobot().colorSensors.getCRGB(Constants.Robot.RIGHT_COLOR)[0]) / 2;
            floor = true;

            auto.getRobot().bumper.setPosition(Constants.Teleop.BUMPER_IN_POSITION);
            auto.getRobot().flipper.setPosition(Constants.Teleop.FLIPPER_IN);
            auto.getRobot().forklift.setPosition(Constants.Teleop.FORKLIFT_HOLD_POSITION);
        }
        auto.loop();
        telemetry.addLine(auto.getRobot().debug());
        telemetry.addLine("Left Beacon Color: " + auto.getRobot().leftBeacon.toString());
    }
}
