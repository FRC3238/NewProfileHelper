package MotionPacker;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.modifiers.TankModifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Julee on 3/23/2017.
 */
public class Utilities {
    public static Trajectory.Config compBot = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,ProfilingConstants.Utilities.compDt, ProfilingConstants.Utilities.compMaxVel, ProfilingConstants.Utilities.compMaxAccel, ProfilingConstants.Utilities.compMaxJerk),
    fastCompBot = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,ProfilingConstants.Utilities.compFastDt, ProfilingConstants.Utilities.compFastmaxVel, ProfilingConstants.Utilities.compFastMaxAccel, ProfilingConstants.Utilities.compFastMaxJerk),
    protoBot = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,ProfilingConstants.Utilities.protoDt, ProfilingConstants.Utilities.protoMaxVel, ProfilingConstants.Utilities.protoMaxAccel, ProfilingConstants.Utilities.protoMaxJerk);
    public static RobotDimensions compBotDim = new RobotDimensions(ProfilingConstants.kCompWheelbaseWidth, ProfilingConstants.kCompWheelbaseLength,
                                     ProfilingConstants.kCompRobotWidthWithBumpers, ProfilingConstants.kCompRobotLengthWithBumpers,
                                     ProfilingConstants.kCompFrontOfGearToRobotCenter, ProfilingConstants.kCompWheelDiameter),
    protoBotDim = new RobotDimensions(ProfilingConstants.kProtoWheelbaseWidth, ProfilingConstants.kProtoWheelbaseLength,
                                      ProfilingConstants.kProtoRobotWidthWithBumpers, ProfilingConstants.kProtoRobotLengthWithBumpers, ProfilingConstants.kProtoFrontOfGearToRobotCenter,
                                      ProfilingConstants.kProtoWheelDiameter);
    public static void outputToJavaFile(String profileName, Trajectory tr) throws FileNotFoundException {
        String leftNewName = ProfilingConstants.Utilities.DirectoryPath+profileName+"LEFT.java";
        String rightNewName = ProfilingConstants.Utilities.DirectoryPath+profileName+"RIGHT.java";

        File leftFile = new File(leftNewName);
        File rightFile = new File(rightNewName);
        PrintWriter leftWriter = new PrintWriter(leftFile), rightWriter = new PrintWriter(rightFile);
        TankModifier mod = new TankModifier(tr);
        mod.modify(ProfilingConstants.kWheelbaseWidth);
        Trajectory leftTraj = mod.getLeftTrajectory(), rightTraj = mod.getRightTrajectory();

        leftWriter.append("package " + ProfilingConstants.Utilities.packageName + ";\npublic static class " + profileName + "LEFT {\npublic static double[][] Points = new double[][]{");
        rightWriter.append("package " + ProfilingConstants.Utilities.packageName + ";\npublic static class " + profileName + "RIGHT {\npublic static double[][] Points = new double[][]{");
        for(int i = 0; i < leftTraj.length(); i++) {

            leftWriter.append("{"+(leftTraj.segments[i].position / (Math.PI * ProfilingConstants.kWheelDiameter)) + ", " +

                    (leftTraj.segments[i].velocity * 60 / (Math.PI * ProfilingConstants.kWheelDiameter)) + ", " +

                    (leftTraj.segments[i].dt * 1000)+ "} ");

            rightWriter.append("{"+(rightTraj.segments[i].position / (Math.PI * ProfilingConstants.kWheelDiameter)) + ", " +

                    (rightTraj.segments[i].velocity * 60 / (Math.PI * ProfilingConstants.kWheelDiameter)) + ", " +

                    (rightTraj.segments[i].dt * 1000)+ "} ");

            if(i!=leftTraj.length()-1)

            {

                leftWriter.append(",");

                rightWriter.append(",");

            }

            else {

                leftWriter.append("};\n}");

                rightWriter.append("};\n}");

            }

        }

        leftWriter.close();

        rightWriter.close();
    }


}
