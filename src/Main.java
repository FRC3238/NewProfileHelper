import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

//import ProfileGenerator.AutoDriveMotionProfileHelper;
//import ProfileGenerator.TalonMotionProfilePoint;
//import ProfileGenerator.AutoDriveMotionProfileHelper;
//import ProfileGenerator.TalonMotionProfilePoint;
//import AutoDriveMotionProfileHelper.DriveSides;
import jaci.pathfinder.*;
import jaci.pathfinder.modifiers.*;

public class Main {
   static double maxVel = 30, maxAccel = 30, maxJerk = 600, dt = 0.01;
    public static void main(String[] args) {
//        Trajectory boilerCornerToLiftTrajectory = AutoDriveMotionProfileHelper.GetTrajectoryBoilerCornerToSideLift(0.01, 60, 60, 1200);
        Trajectory boilerCornerToLiftTrajectory = AutoDriveMotionProfileHelper.GetTrajectoryBoilerCornerToSideLift(dt, maxVel, maxAccel, maxJerk);
        Trajectory mainLiftTrajectory = AutoDriveMotionProfileHelper.getCenterLiftTrajectory(dt, maxVel, maxAccel, maxJerk);
        Trajectory straightProfile = AutoDriveMotionProfileHelper.getStraightTrajectory(dt, maxVel, maxAccel, maxJerk, ConstantsMP.kFirstForwardMove);

        Trajectory straightProfileTwo = AutoDriveMotionProfileHelper.getStraightTrajectory(dt, maxVel, maxAccel, maxJerk, ConstantsMP.kSecondForwardMove);
//        List<TalonMotionProfilePoint> straightProf = AutoDriveMotionProfileHelper
//                .getMotionProfileLeftRight(straightProfile,AutoDriveMotionProfileHelper.AllianceColors.RED,
//                        AutoDriveMotionProfileHelper.DriveSides.RIGHT );
//      
//        List<TalonMotionProfilePoint> sideMP = AutoDriveMotionProfileHelper.getMotionProfileLeftRight(boilerCornerToLiftTrajectory, 
//                AutoDriveMotionProfileHelper.AllianceColors.RED, 
//                AutoDriveMotionProfileHelper.DriveSides.LEFT);
//        List<TalonMotionProfilePoint> sideMR = AutoDriveMotionProfileHelper.getMotionProfileLeftRight(boilerCornerToLiftTrajectory, 
//                AutoDriveMotionProfileHelper.AllianceColors.RED, 
//                AutoDriveMotionProfileHelper.DriveSides.RIGHT);
//        List<TalonMotionProfilePoint> mainLift = AutoDriveMotionProfileHelper.getMotionProfileLeftRight(mainLiftTrajectory,
//                AutoDriveMotionProfileHelper.AllianceColors.RED, AutoDriveMotionProfileHelper.DriveSides.RIGHT);
        List<TalonMotionProfilePoint> distLift = AutoDriveMotionProfileHelper.getMotionProfileLeftRight(straightProfile,
                AutoDriveMotionProfileHelper.AllianceColors.RED, AutoDriveMotionProfileHelper.DriveSides.RIGHT);

        List<TalonMotionProfilePoint> distLiftTwo = AutoDriveMotionProfileHelper.getMotionProfileLeftRight(straightProfileTwo,
                AutoDriveMotionProfileHelper.AllianceColors.RED, AutoDriveMotionProfileHelper.DriveSides.RIGHT);
        AutoDriveMotionProfileHelper.WriteTalonMPToCSV("C:\\Users\\Team 3238\\Documents\\Motion Profiling\\altTwo.csv", distLiftTwo);

//        AutoDriveMotionProfileHelper.WriteTalonMPToCSV("C:\\Users\\Team 3238\\Documents\\Motion Profiling\\mainLift.csv", mainLift);
        AutoDriveMotionProfileHelper.WriteTalonMPToCSV("C:\\Users\\Team 3238\\Documents\\Motion Profiling\\alt.csv", distLift);
//        AutoDriveMotionProfileHelper.WriteTalonMPToCSV("C:\\Users\\Team 3238\\Documents\\Motion Profiling\\rightTalonMP_40MaxSpeed.csv", sideMR);
//
//        AutoDriveMotionProfileHelper.WriteTalonMPToCSV("C:\\Users\\Team 3238\\Documents\\Motion Profiling\\leftTalonMP_40MaxSpeed.csv", sideMP);
        
        JOptionPane.showMessageDialog(null, "done");        
    }
    public double[][] getStraightProfile(double distance) {
        Trajectory straightProfile = AutoDriveMotionProfileHelper.getStraightTrajectory(dt, maxVel, maxAccel, maxJerk, distance);
        List<TalonMotionProfilePoint> straightProf = AutoDriveMotionProfileHelper
                .getMotionProfileLeftRight(straightProfile,AutoDriveMotionProfileHelper.AllianceColors.RED,
                        AutoDriveMotionProfileHelper.DriveSides.RIGHT );
//        for(int i = 0; i < straightProfile.length(); i++) {
//            DriverStation.reportWarning(""+straightProfile.get(i).position, false);
//        }
        return TalonListToDouble(straightProf);
    }
    public double[][] TalonListToDouble(List<TalonMotionProfilePoint> profile) {
        double[][] returner = new double[profile.size()][3];
        for(int i = 0; i < profile.size(); i++){
            returner[i][0] = profile.get(i).GetPositionInRevolutions();
            returner[i][1] = profile.get(i).GetSpeedInRPMs();
            returner[i][2] = profile.get(i).GetTimestepInMilliseconds();
        }
        return returner;
    }

}
