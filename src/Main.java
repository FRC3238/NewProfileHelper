//import ProfileGenerator.WaypointCalculator;
//import ProfileGenerator.TalonMotionProfilePoint;
//import ProfileGenerator.WaypointCalculator;
//import ProfileGenerator.TalonMotionProfilePoint;
//import WaypointCalculator.DriveSides;
import MotionPacker.ProfilingConstants;
import MotionPacker.RobotDimensions;
import jaci.pathfinder.*;

public class Main {

    public static void main(String[] args)  throws java.io.FileNotFoundException{

        initWaypointCalculator();

        createProfileFile(WaypointCalculator.getWaypointsSideWallToCenterLift(), "sideLyft");

        System.out.println("done");
    }

    public static void initWaypointCalculator() {
        Trajectory.Config activeConfig = MotionPacker.Utilities.protoBot;
        RobotDimensions activeRobot = MotionPacker.Utilities.protoBotDim;
        WaypointCalculator.init(activeRobot, activeConfig);
    }
    public static void createProfileFile(Waypoint[] points, String name) throws java.io.FileNotFoundException{
        Trajectory customTrajectory = WaypointCalculator.getTrajectory(points);
        MotionPacker.Utilities.outputToJavaFile(name, customTrajectory);

    }
}
