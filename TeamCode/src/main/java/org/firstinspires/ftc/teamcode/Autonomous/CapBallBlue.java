package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.API.AutonomousBase;
import org.firstinspires.ftc.teamcode.Autonomous.API.BeaconFinderUtils;
import org.firstinspires.ftc.teamcode.Robots.RobotAuto;
import org.firstinspires.ftc.teamcode.Utils.Constants;
import org.firstinspires.ftc.teamcode.Utils.Group;

/**
 * Created by ethan.hampton on 1/23/2017.
 * <p>
 * adds team capabilities to autonomous
 */
@Autonomous(name = "Cap Ball Blue", group = Group.RELEASE)
public class CapBallBlue extends OpMode {
    private AutonomousBase auto;

    private final double[][] steps = new double[][]{
            {0, 3.5, 3.5, -1},//forward
            {0, 1.1, -1.1, -1},//turn right
            {0, 0.5, 0.5, -1},//forward
            {5},//launch
            {0, 3, 3, -1},//forward
            {-1}
    };


    @Override
    public void init() {
        auto = new AutonomousBase() {

            @Override
            public boolean checkMovement(RobotAuto robot, double movementMode) {
                return BeaconFinderUtils.checkMovement(robot, movementMode);
            }

            @Override
            public void startMovement(RobotAuto robot, double movementMode) {
                BeaconFinderUtils.startMovement(robot, movementMode, this.getSteps());
            }

            @Override
            public double[][] getSteps() {
                return steps;
            }
        };
        auto.init(hardwareMap);
        auto.getRobot().setEncoderTicksPerRotation(Constants.Robot.MOTOR_ENCODERS_USED);


        auto.getRobot().leftMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
        auto.getRobot().rightMotor.setMaxSpeed(Constants.MAX_MOTOR_TICKS_PER_SECOND);
    }

    @Override
    public void loop() {
        auto.loop();
    }
}
