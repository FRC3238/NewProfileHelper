//import ProfileGenerator.WaypointCalculator;
//import ProfileGenerator.TalonMotionProfilePoint;
//import ProfileGenerator.WaypointCalculator;
//import ProfileGenerator.TalonMotionProfilePoint;
//import WaypointCalculator.DriveSides;
import MotionPacker.RobotDimensions;
import jaci.pathfinder.*;

public class Main {

    public static void main(String[] args)  throws java.io.FileNotFoundException{

        initWaypointCalculator();

        createProfileFile(WaypointCalculator.getWaypointsSideWallToCenterLift(), "sideLyft");

        System.out.println("done");
    }

    public static void initWaypointCalculator() {
        Trajectory.Config activeConfig = Utilities.protoBot;
        RobotDimensions activeRobot = Utilities.protoBotDim;
        WaypointCalculator.init(activeRobot, activeConfig);
    }
    public static void createProfileFile(Waypoint[] points, String name) throws java.io.FileNotFoundException{
        Trajectory customTrajectory = WaypointCalculator.getTrajectory(points);
        Utilities.outputToJavaFile(name, customTrajectory);

    }
}
