import MotionPacker.ProfilingConstants;
import MotionPacker.RobotDimensions;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Julee on 3/23/2017.
 */
public class Utilities {
    public static Trajectory.Config compBot = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, ProfilingConstants.Utilities.compDt, ProfilingConstants.Utilities.compMaxVel, ProfilingConstants.Utilities.compMaxAccel, ProfilingConstants.Utilities.compMaxJerk),
    fastCompBot = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,ProfilingConstants.Utilities.compFastDt, ProfilingConstants.Utilities.compFastmaxVel, ProfilingConstants.Utilities.compFastMaxAccel, ProfilingConstants.Utilities.compFastMaxJerk),
    protoBot = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,ProfilingConstants.Utilities.protoDt, ProfilingConstants.Utilities.protoMaxVel, ProfilingConstants.Utilities.protoMaxAccel, ProfilingConstants.Utilities.protoMaxJerk);
    public static RobotDimensions compBotDim = new RobotDimensions(ProfilingConstants.kCompWheelbaseWidth, ProfilingConstants.kCompWheelbaseLength,
                                     ProfilingConstants.kCompRobotWidthWithBumpers, ProfilingConstants.kCompRobotLengthWithBumpers,
                                     ProfilingConstants.kCompFrontOfGearToRobotCenter, ProfilingConstants.kCompWheelDiameter),
    protoBotDim = new RobotDimensions(ProfilingConstants.kProtoWheelbaseWidth, ProfilingConstants.kProtoWheelbaseLength,
                                      ProfilingConstants.kProtoRobotWidthWithBumpers, ProfilingConstants.kProtoRobotLengthWithBumpers, ProfilingConstants.kProtoFrontOfGearToRobotCenter,
                                      ProfilingConstants.kProtoWheelDiameter);
    static int INVERTLEFT = 1, INVERTRIGHT = 2, INVERTBOTH = 3;
    static String header = "/**/";
    public static void createHeader(Waypoint[] points) {
        String head = "/**";
        head+="\n@Waypoints";
        for(Waypoint p : points) {
            head+="\n\tX: " + p.x + " Y:" + p.y + " Angle: " + p.angle;
        }
        head+="\n@RobotDimensions";
        head+="\n\tGearCenter:" + WaypointCalculator.activeDimensions.getFrontOfGearToRobotCenter();
        head+="\n\tRobotWidth:" + WaypointCalculator.activeDimensions.getRobotWidthWithBumpers();
        head+="\n\tRobotLength:" + WaypointCalculator.activeDimensions.getRobotLengthWithBumpers();
        head+="\n\tWheelbaseWidth:" + WaypointCalculator.activeDimensions.getWheelbaseWidth();
        head+="\n\tWheelbaseLength:" + WaypointCalculator.activeDimensions.getWheelbaseLength();
        head+="\n\tWheelDiameter:" + WaypointCalculator.activeDimensions.getWheelDiameter();
        head+="\n@MotionParameters";
        head+="\n\tVel:" + WaypointCalculator.activeParameters.max_velocity;
        head+="\n\tAccel:" + WaypointCalculator.activeParameters.max_acceleration;
        head+="\n\tJerk:" + WaypointCalculator.activeParameters.max_jerk;
        head+="\n\tdt:" + WaypointCalculator.activeParameters.dt;
        head+="\n*/\n";
        header = head;
    }
    public static void outputToJavaFile(String profileName, Trajectory tr) throws FileNotFoundException {
        outputToJavaFile(profileName, tr, 0);
    }
    public static void outputToJavaFile(String profileName, Trajectory tr, int invert) throws FileNotFoundException {
        String leftNewName = ProfilingConstants.Utilities.DirectoryPath+profileName+"LEFT.java";
        String rightNewName = ProfilingConstants.Utilities.DirectoryPath+profileName+"RIGHT.java";
        double leftMult = 1, rightMult = 1;
        if(invert == INVERTLEFT) leftMult = -1;
        if(invert == INVERTRIGHT) rightMult = -1;
        if(invert == INVERTBOTH) {leftMult = -1; rightMult = -1;}
        File leftFile = new File(leftNewName);
        File rightFile = new File(rightNewName);
        PrintWriter leftWriter = new PrintWriter(leftFile), rightWriter = new PrintWriter(rightFile);
        TankModifier mod = new TankModifier(tr);
        mod.modify(WaypointCalculator.activeDimensions.getWheelbaseWidth());
        Trajectory leftTraj = mod.getLeftTrajectory(), rightTraj = mod.getRightTrajectory();

        leftWriter.append("package " + ProfilingConstants.Utilities.packageName + ";\npublic class " + profileName + "LEFT {\n"+header+"\n    public static double[][] Points = new double[][]{");
        rightWriter.append("package " + ProfilingConstants.Utilities.packageName + ";\npublic class " + profileName + "RIGHT {\n" + header + "\n    public static double[][] Points = new double[][]{");
        System.out.println(""+leftTraj.segments[0].dt);
        System.out.println(""+leftTraj.length());
        leftWriter.append("\n\n        //Time: " + leftTraj.segments[0].dt*leftTraj.length()+"\n");
        rightWriter.append("\n\n        //Time: " + leftTraj.segments[0].dt*leftTraj.length()+"\n");
        header = "/**/";
        double wheelbaseDiameter = WaypointCalculator.activeDimensions.getWheelDiameter();
        for(int i = 0; i < leftTraj.length(); i++) {

            leftWriter.append("        {"+((leftTraj.segments[i].position*leftMult) / (Math.PI * wheelbaseDiameter)) + ", " +

                    ((leftTraj.segments[i].velocity * 60 *leftMult) / (Math.PI * wheelbaseDiameter)) + ", " +

                    (leftTraj.segments[i].dt * 1000)+ "}");

            rightWriter.append("        {"+((rightTraj.segments[i].position*rightMult) / (Math.PI * wheelbaseDiameter)) + ", " +

                    ((rightTraj.segments[i].velocity * 60*rightMult) / (Math.PI * wheelbaseDiameter)) + ", " +

                    (rightTraj.segments[i].dt * 1000)+ "}");

            if(i!=leftTraj.length()-1)

            {

                leftWriter.append(",\n");

                rightWriter.append(",\n");

            }

            else {

                leftWriter.append("\n    };\n}");

                rightWriter.append("\n    };\n}");

            }

        }

        leftWriter.close();

        rightWriter.close();
    }


}
