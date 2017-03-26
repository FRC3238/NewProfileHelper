import MotionPacker.RobotDimensions;
import jaci.pathfinder.*;

public class Main {

    public static void main(String[] args)  throws java.io.FileNotFoundException{

        initWaypointCalculator();

        createProfileFile(WaypointCalculator.getWaypointsSideWallToCenterLift(), "sideLyft");
        createProfileFile(WaypointCalculator.getWaypointsHopperPerpendicular(), "hopperPerp");
        createProfileFile(WaypointCalculator.getWaypointsForTurn(90), "hopperTurn", Utilities.INVERTLEFT);
        createProfileFile(WaypointCalculator.getWaypointsHitHopper(), "hopperHit");
        createProfileFile(WaypointCalculator.getWaypointsDistance(36), "hopperHitBack", Utilities.INVERTBOTH);

        createProfileFile(WaypointCalculator.getWaypointsForTurn(138), "hopperShootTurn", Utilities.INVERTRIGHT);
        System.out.println("done");
    }

    public static void initWaypointCalculator() {
        Trajectory.Config activeConfig = Utilities.protoBot;
        RobotDimensions activeRobot = Utilities.protoBotDim;
        WaypointCalculator.init(activeRobot, activeConfig);
    }
    public static void createProfileFile(Waypoint[] points, String name) throws java.io.FileNotFoundException{
        createProfileFile(points, name, 0);
    }
    public static void createProfileFile(Waypoint[] points, String name, int invert) throws java.io.FileNotFoundException{
        Trajectory customTrajectory = WaypointCalculator.getTrajectory(points);
        Utilities.createHeader(points);
        Utilities.outputToJavaFile(name, customTrajectory, invert);

    }
}
