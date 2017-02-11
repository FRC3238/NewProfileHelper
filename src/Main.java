import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

//import AutoDriveMotionProfileHelper.DriveSides;
import jaci.pathfinder.*;
import jaci.pathfinder.modifiers.*;

public class Main {
   static double maxVel = 60, maxAccel = 60, maxJerk = 1200, dt = 0.01;
    public static void main(String[] args) {
//        Trajectory boilerCornerToLiftTrajectory = AutoDriveMotionProfileHelper.GetTrajectoryBoilerCornerToSideLift(0.01, 60, 60, 1200);
        Trajectory boilerCornerToLiftTrajectory = AutoDriveMotionProfileHelper.GetTrajectoryBoilerCornerToSideLift(dt, maxVel, maxAccel, maxJerk);
        Trajectory mainLiftTrajectory = AutoDriveMotionProfileHelper.getCenterLiftTrajectory(dt, maxVel, maxAccel, maxJerk);
        List<TalonMotionProfilePoint> sideMP = AutoDriveMotionProfileHelper.getMotionProfileLeftRight(boilerCornerToLiftTrajectory, 
                AutoDriveMotionProfileHelper.AllianceColors.RED, 
                AutoDriveMotionProfileHelper.DriveSides.LEFT);
        List<TalonMotionProfilePoint> sideMR = AutoDriveMotionProfileHelper.getMotionProfileLeftRight(boilerCornerToLiftTrajectory, 
                AutoDriveMotionProfileHelper.AllianceColors.RED, 
                AutoDriveMotionProfileHelper.DriveSides.RIGHT);
        List<TalonMotionProfilePoint> mainLift = AutoDriveMotionProfileHelper.getMotionProfileLeftRight(mainLiftTrajectory,
                AutoDriveMotionProfileHelper.AllianceColors.RED, AutoDriveMotionProfileHelper.DriveSides.RIGHT);

        AutoDriveMotionProfileHelper.WriteTalonMPToCSV("C:\\Users\\Team 3238\\Documents\\Motion Profiling\\mainLift.csv", mainLift);
        AutoDriveMotionProfileHelper.WriteTalonMPToCSV("C:\\Users\\Team 3238\\Documents\\Motion Profiling\\rightTalonMP.csv", sideMR);

        AutoDriveMotionProfileHelper.WriteTalonMPToCSV("C:\\Users\\Team 3238\\Documents\\Motion Profiling\\leftTalonMP.csv", sideMP);
        
        JOptionPane.showMessageDialog(null, "done");        
    }

}
