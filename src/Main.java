import MotionPacker.RobotDimensions;
import MotionPacker.TrajectoryParameters;
import jaci.pathfinder.*;

public class Main {

    public static void main(String[] args) throws java.io.FileNotFoundException {

        initWaypointCalculator();

        /*  Commented profiles are no longer used in the robot code.
            createProfileFile(WaypointCalculator.getWaypointsSideWallToCenterLift(), "sideLyft");
            createProfileFile(WaypointCalculator.getWaypointsHopperPerpendicular(), "hopperPerp");
            createProfileFile(WaypointCalculator.getWaypointsForTurn(90), "hopperTurn", Utilities.INVERTLEFT);
            createProfileFile(WaypointCalculator.getWaypointsHitHopper(), "hopperHit");
            createProfileFile(WaypointCalculator.getWaypointsForTurn(90), "rightAngleTurnTest", Utilities.INVERTRIGHT);
        */

//        createProfileFile(WaypointCalculator.getWaypointsCurvedHopperHit(), "curveHit");
//        createProfileFile(WaypointCalculator.getWaypointsDistance(18), "hopperHitBack", Utilities.INVERTBOTH);
//        createProfileFile(WaypointCalculator.getWaypointsForTurn(95 + 15), "hopperShootTurnRed", Utilities.INVERTLEFT);
//        createProfileFile(WaypointCalculator.getWaypointsForTurn(95), "hopperShootTurnBlue", Utilities.INVERTLEFT);
//
//        createProfileFile(WaypointCalculator.getWaypointsRetryGear(), "retryGear");
//
//        createProfileFile(WaypointCalculator.getWaypointsCenterShootForward(), "centerShootForward");
//        createProfileFile(WaypointCalculator.getWaypointsCenterShootCurveRed(), "centerShootCurveRed", Utilities.INVERTBOTH);
//        createProfileFile(WaypointCalculator.getWaypointsCenterShootCurveBlue(), "centerShootCurveBlue", Utilities.INVERTBOTH);
//
//        createProfileFile(WaypointCalculator.getWaypointsBoilerRun(), "boilerSideRun");
//
        WaypointCalculator.init(Utilities.compBotDim, Utilities.compBot);
//        createProfileFile(WaypointCalculator.getWaypointsDriveToShotLocationBlue(), "continueToLiftBlue");

        createProfileFile(WaypointCalculator.getWaypointsPegToShootRed(), "pegToShootRed", Utilities.INVERTBOTH);
        createProfileFile(WaypointCalculator.getWaypointsPegToShootBlue(), "pegToShootBlue", Utilities.INVERTBOTH);


        System.out.println("done");
    }

    public static void initWaypointCalculator() {
        Trajectory.Config activeConfig = Utilities.fastCompBot;
        RobotDimensions activeRobot = Utilities.compBotDim;
        WaypointCalculator.init(activeRobot, activeConfig);
    }

    public static void createProfileFile(Waypoint[] points, String name) throws java.io.FileNotFoundException {
        createProfileFile(points, name, 0);
    }

    public static void createProfileFile(Waypoint[] points, String name, int invert) throws java.io.FileNotFoundException {
        Trajectory customTrajectory = WaypointCalculator.getTrajectory(points);
        Utilities.createHeader(points);
        Utilities.outputToJavaFile(name, customTrajectory, invert);

    }
}
