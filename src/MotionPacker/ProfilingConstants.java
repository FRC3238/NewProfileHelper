package MotionPacker;

public class ProfilingConstants {
    //Robot geometry constants
    public static final double kWheelbaseWidth =( 0.61595*(1/0.0254)); //meters
    public static final double kWheelbaseLength = 17.375; //inches
    public static final double kRobotWidthWithBumpers = 36.75; //inches
    public static final double kRobotLengthWithBumpers = 33.75; //inches
    public static final double kFrontOfGearToRobotCenter = 10.875; //inches
    public static final double kWheelDiameter = 7.64; //inches
    public static final double kCompWheelbaseWidth =( 0.61595*(1/0.0254)); //meters
    public static final double kCompWheelbaseLength = 17.375; //inches
    public static final double kCompRobotWidthWithBumpers = 36.75; //inches
    public static final double kCompRobotLengthWithBumpers = 33.75; //inches
    public static final double kCompFrontOfGearToRobotCenter = 10.875; //inches
    public static final double kCompWheelDiameter = 7.64; //inches
    public static final double kProtoWheelbaseWidth =( 0.61595*(1/0.0254)); //meters
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

    public class Utilities {

        public static final double maxVel = 30,
                maxAccel = 30,
                maxJerk = 600,
                dt = 0.01;
        public static final double compMaxVel = 30,
                compMaxAccel = 30,
                compMaxJerk = 600,
                compDt = 0.01;
        public static final double protoMaxVel = 30,
               protoMaxAccel = 30,
               protoMaxJerk = 600,
               protoDt = 0.01;
        public static final double compFastmaxVel = 30,
                compFastMaxAccel = 30,
                compFastMaxJerk = 600,
                compFastDt = 0.01;
        public static final String DirectoryPath = "C:\\Users\\Julee\\IdeaProjects\\";
        public static final String packageName = "org.usfirst.frc.team3238.robot.Autonomous.Profile";
    }
}
