package org.firstinspires.ftc.teamcode.Autonomous.API;

import org.firstinspires.ftc.teamcode.Robots.RobotAuto;

/**
 * Created by ethan.hampton on 2/20/2017.
 * <p>
 * Module for autonomous
 */

public abstract class Module {

    protected double[] vars;

    protected Module(double[] vars){
        this.vars = vars;
    }

    /**
     * Is called when not using default movement and needs to check to see if movement is done
     * Automatically iterates step and state if returns true
     *
     * @param robot robot object to get and set values from
     */
   public abstract void checkMovement(RobotAuto robot);

    /**
     * Is called at the start of every movement
     *
     * @param robot robot object to get and set values from
     */
   public abstract void startMovement(RobotAuto robot);

    public double[] getVars() {
        return vars;
    }

    public void setVars(double[] vars) {
        this.vars = vars;
    }
}
