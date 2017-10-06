package MotionPacker;

import jaci.pathfinder.Waypoint;

public class ProfilingConstants {
    //Robot geometry constants
    public static final double kWheelbaseWidth =( 0.61595*(1/0.0254)); //meters
    public static final double kWheelbaseLength = 17.375; //inches
    public static final double kRobotWidthWithBumpers = 36.75; //inches
    public static final double kRobotLengthWithBumpers = 33.75; //inches
    public static final double kFrontOfGearToRobotCenter = 10.875; //inches
    public static final double kWheelDiameter = 7.64; //inches
    public static final double kCompWheelbaseWidth = 23.5; //inches
    public static final double kCompWheelbaseLength = 18; //inches
    public static final double kCompRobotWidthWithBumpers = 35.5; //inches
    public static final double kCompRobotLengthWithBumpers = 33.5; //inches
    public static final double kCompFrontOfGearToRobotCenter = 10.5; //inches
    public static final double kCompWheelDiameter = 7.64; //inches
    public static final double kCompBumperThickness = 3.25; //inches, includes backing plywood
    public static final double kCompBallExitToCenterLineOfRobot = 11.5; //inches, measured across width of robot
    public static final double kProtoWheelbaseWidth =26.5; //meters
    public static final double kProtoWheelbaseLength = 17.375; //inches
    public static final double kProtoRobotWidthWithBumpers = 36.75; //inches
    public static final double kProtoRobotLengthWithBumpers = 33.75; //inches
    public static final double kProtoFrontOfGearToRobotCenter = 10.875; //inches
    public static final double kProtoWheelDiameter = 7.64; //inches
    //field geometry constants
    public static final double kDriverWallToCenterLift = 112;
    public static final double kDriverWallToSideLift = 130; //inches, from field CAD

    //public static final double kSideWallToBoilerSideLift = 91.2; //inches, from field CAD
    public static final double kSideWallToBoilerSideLift = 100; //inches, from measuring our field setup
    
    public static final double kSideWallToFeederSideLift = 89.7; //inches, from field CAD
    

    public static final double kStartPointToBot = kSideWallToBoilerSideLift-66+39;//at -66 thought it was at 0
    
    public static final double kFirstForwardMoveFar = kDriverWallToSideLift-(kSideWallToBoilerSideLift-kRobotWidthWithBumpers/2)*Math.tan(Math.toRadians(30))-kRobotLengthWithBumpers/2;
    public static final double kSecondForwardMoveFar = (kSideWallToBoilerSideLift-kRobotWidthWithBumpers/2)/Math.cos(Math.toRadians(30))-kRobotLengthWithBumpers/2;

    public static final double kFirstForwardMove = kDriverWallToSideLift-(kStartPointToBot-kRobotWidthWithBumpers/2)*Math.tan(Math.toRadians(30))-kRobotLengthWithBumpers/2;
    public static final double kSecondForwardMove = (kStartPointToBot-kRobotWidthWithBumpers/2)/Math.cos(Math.toRadians(30))-kRobotLengthWithBumpers/2+kFrontOfGearToRobotCenter;

    public static final Waypoint startPoint = new Waypoint(0,0,Math.toRadians(90));
    public static final double kHopperForwardFirst = 112,
                                kHopperForwardHit = 116; //
    public class Utilities {

        public static final double maxVel = 30,
                maxAccel = 30,
                maxJerk = 600,
                dt = 0.01;
        public static final double compMaxVel = 60,
                compMaxAccel = 60,
                compMaxJerk = 1200,
                compDt = 0.01;
        public static final double protoMaxVel = 60,
               protoMaxAccel = 60,
               protoMaxJerk = 1200,
               protoDt = 0.01;
        public static final double compFastmaxVel = 80,
                compFastMaxAccel = 80,
                compFastMaxJerk = 1400,
                compFastDt = 0.01;
        public static final String DirectoryPath = "C:\\Users\\Team 3238\\IdeaProjects\\2017Robot\\src\\org\\usfirst\\frc\\team3238\\robot\\Autonomous\\Profiles\\";
        public static final String packageName = "org.usfirst.frc.team3238.robot.Autonomous.Profiles";
    }
}
