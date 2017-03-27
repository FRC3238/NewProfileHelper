import java.util.ArrayList;

//import ProfileGenerator.MotionPacker.ProfilingConstants;
import MotionPacker.ProfilingConstants;
import MotionPacker.RobotDimensions;
import MotionPacker.TrajectoryParameters;
import jaci.pathfinder.*;

public class WaypointCalculator {

   static RobotDimensions activeDimensions;
   static Trajectory.Config activeParameters;
    public static void init(RobotDimensions activeDim, Trajectory.Config activeParams) {
        activeDimensions = activeDim;
        activeParameters = activeParams;
    }
    public static Waypoint[] getWaypointsHopperPerpendicular() { //startpoint is inner edge boiler tape
        Waypoint[] fwdMove = new Waypoint[2];
        fwdMove[0] = ProfilingConstants.startPoint;
        fwdMove[1] = new Waypoint(0,ProfilingConstants.kHopperForwardFirst-activeDimensions.getRobotLengthWithBumpers(), Math.toRadians(90));
        return fwdMove;
    }
    public static Waypoint[] getWaypointsHitHopper() {
        Waypoint[] fwdMove = new Waypoint[2];
        fwdMove[0] = ProfilingConstants.startPoint;
        fwdMove[1] = new Waypoint(0,ProfilingConstants.kHopperForwardHit-(activeDimensions.getRobotWidthWithBumpers()-activeDimensions.getRobotLengthWithBumpers())/2-activeDimensions.getRobotLengthWithBumpers()+3-79.3+activeDimensions.getRobotWidthWithBumpers(), Math.toRadians(90));
        return fwdMove;
    }
    public static Waypoint[] getWaypointsCurvedHit() {
        Waypoint[] turn = new Waypoint[2];
        turn[0] = ProfilingConstants.startPoint;
        turn[1] = new Waypoint(ProfilingConstants.kHopperForwardHit-(activeDimensions.getRobotWidthWithBumpers()-activeDimensions.getRobotLengthWithBumpers())/2-activeDimensions.getRobotLengthWithBumpers()+3+activeDimensions.getRobotWidthWithBumpers(), ProfilingConstants.kHopperForwardFirst-activeDimensions.getRobotLengthWithBumpers(), Math.toRadians(180));
        return turn;
    }
    public static Waypoint[] getWaypointsForTurn(double angle) {
        double angleToStraight = (Math.PI*activeDimensions.getWheelbaseWidth())/(360/angle);
        Waypoint[] turn = new Waypoint[2];
        turn[0] = ProfilingConstants.startPoint;
        turn[1] = new Waypoint(0, angleToStraight, Math.toRadians(90));
        return turn;
    }
    public static Waypoint[] getWaypointsSideWallToCenterLift() {
        Waypoint[] result = new Waypoint[2];
        result[0] = new Waypoint(0,0,Math.PI/2);
        result[1] = new Waypoint(0, ProfilingConstants.kDriverWallToCenterLift - (activeDimensions.getRobotLengthWithBumpers()/2) - activeDimensions.getFrontOfGearToRobotCenter() , Math.PI/2);
        return result;
    }
    public static Waypoint[] GetWaypointsBoilerCornerToSideLift()
    {
        Waypoint[] result = new Waypoint[3];

        //starting point:
        //Robot starts with back corner of robot at intersection of driver station wall and boiler face, robot facing other alliance station wall
        //Calculations based on geometric center of the robot.
        result[0] = new Waypoint(0, 0, 0); 

        //ending point with gear on lift
        double x_final = ProfilingConstants.kDriverWallToSideLift
                - activeDimensions.getFrontOfGearToRobotCenter() * Math.cos(Math.toRadians(60))
                - activeDimensions.getRobotLengthWithBumpers()/2;

        double y_final = ProfilingConstants.kSideWallToBoilerSideLift
                - activeDimensions.getFrontOfGearToRobotCenter() * Math.sin(Math.toRadians(60))
                - activeDimensions.getRobotWidthWithBumpers()/2;
        
        result[2] = new Waypoint(x_final, y_final, Math.toRadians(60)); 

        //intermediate point three feet straight out from ending point at lift so we come straight at the lift for a decent distance
        double x = x_final - 36 * Math.cos(Math.toRadians(60));
        double y = y_final - 36 * Math.sin(Math.toRadians(60));
        result[1] = new Waypoint(x, y, Math.toRadians(60)); 
        
        return result;      
    }
    public static Trajectory getTrajectory(Waypoint[] points) {
        return Pathfinder.generate(points, activeParameters);

    }
    public static Waypoint[] customProfile() {
       Waypoint[] returner = new Waypoint[2];
       returner[0] = new Waypoint(0, 0, 0);
       returner[1] = new Waypoint(10, 0, 0);
       return returner;

    }
    public static Waypoint[] getWaypointsDistance(double distance) {
        Waypoint[] result = new Waypoint[2];
        result[0] = new Waypoint(0,0,Math.PI/2);
        result[1] = new Waypoint(0, distance, Math.PI/2);
        return result;
    }


}
