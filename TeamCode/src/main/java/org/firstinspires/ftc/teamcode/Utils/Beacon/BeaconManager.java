package org.firstinspires.ftc.teamcode.Utils.Beacon;

import org.firstinspires.ftc.teamcode.Utils.Team;

/**
 * Created by ethan.hampton on 11/15/2016.
 * <p>
 * Manages beacons on the field
 */

public class BeaconManager {
    private BeaconState beaconGears = BeaconState.UNKNOWN;
    private BeaconState beaconTools = BeaconState.UNKNOWN;
    private BeaconState beaconLegos = BeaconState.UNKNOWN;
    private BeaconState beaconWheels = BeaconState.UNKNOWN;
    private Team team;

    public BeaconManager(Team team) {
        this.team = team;
    }

    /**
     * @param beacon what beacon should we return the information for
     * @return beacon button to press, 1 for left, 2 for right
     */
    public int getButton(Beacon beacon) {
        switch (beacon) {
            case TOOLS:
                return stateSwitch(beaconTools);
            case GEARS:
                return stateSwitch(beaconGears);
            case LEGOS:
                return stateSwitch(beaconLegos);
            case WHEELS:
                return stateSwitch(beaconWheels);
        }
        return 0;
    }

    private int stateSwitch(BeaconState state) {
        switch (state) {
            case RED_LEFT:
                return redOnLeft();
            case BLUE_LEFT:
                return blueOnLeft();
            case ALL_BLUE:
                //nothing to do if on blue
                if (team.equals(Team.BLUE)) {
                    return 0;
                } else {
                    return 2;
                }
            case ALL_RED:
                //nothing to do if on red
                if (team.equals(Team.RED)) {
                    return 0;
                } else {
                    return 2;
                }
            case ALL_BLUE_RED_LEFT:
                return redOnLeft();
            case ALL_RED_BLUE_LEFT:
                return blueOnLeft();
            case UNKNOWN:
                return 0;

        }
        return 0;
    }

    private int redOnLeft() {
        if (team.equals(Team.RED)) {
            return 1;
        } else if (team.equals(Team.BLUE)) {
            return 2;
        } else {
            return 0;
        }
    }

    private int blueOnLeft() {
        if (team.equals(Team.BLUE)) {
            return 1;
        } else if (team.equals(Team.RED)) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * @return Gear beacon's state
     */
    public BeaconState getBeaconGears() {
        return beaconGears;
    }

    public void setBeaconGears(BeaconState beaconGears) {
        this.beaconGears = beaconGears;
    }

    /**
     * @return Tool beacon's state
     */
    public BeaconState getBeaconTools() {
        return beaconTools;
    }

    public void setBeaconTools(BeaconState beaconTools) {
        this.beaconTools = beaconTools;
    }

    /**
     * @return Lego beacon's state
     */
    public BeaconState getBeaconLegos() {
        return beaconLegos;
    }

    public void setBeaconLegos(BeaconState beaconLegos) {
        this.beaconLegos = beaconLegos;
    }

    /**
     * @return Wheel beacon's state
     */
    public BeaconState getBeaconWheels() {
        return beaconWheels;
    }

    public void setBeaconWheels(BeaconState beaconWheels) {
        this.beaconWheels = beaconWheels;
    }
}
