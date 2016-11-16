package org.firstinspires.ftc.teamcode.Utils.Beacon;

import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 11/15/2016.
 *
 * Manages beacons on the field
 */

public class BeaconManager {
    private BeaconState beaconGears = BeaconState.UNKNOWN;
    private BeaconState beaconTools = BeaconState.UNKNOWN;
    private BeaconState beaconLegos = BeaconState.UNKNOWN;
    private BeaconState beaconWheels = BeaconState.UNKNOWN;
    private Team team;

    public BeaconManager(Team team){
        this.team = team;
    }

    /**
     *
     * @return Gear beacon's state
     */
    public BeaconState getBeaconGears() {
        return beaconGears;
    }

    public void setBeaconGears(BeaconState beaconGears) {
        this.beaconGears = beaconGears;
    }

    /**
     *
     * @return Tool beacon's state
     */
    public BeaconState getBeaconTools() {
        return beaconTools;
    }

    public void setBeaconTools(BeaconState beaconTools) {
        this.beaconTools = beaconTools;
    }

    /**
     *
     * @return Lego beacon's state
     */
    public BeaconState getBeaconLegos() {
        return beaconLegos;
    }

    public void setBeaconLegos(BeaconState beaconLegos) {
        this.beaconLegos = beaconLegos;
    }

    /**
     *
     * @return Wheel beacon's state
     */
    public BeaconState getBeaconWheels() {
        return beaconWheels;
    }

    public void setBeaconWheels(BeaconState beaconWheels) {
        this.beaconWheels = beaconWheels;
    }
}
