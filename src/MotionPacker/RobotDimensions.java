package MotionPacker;

import java.util.Dictionary;

/**
 * Created by Julee on 3/23/2017.
 */
public class RobotDimensions {
    double wheelbaseWidth, wheelbaseLength, robotWidthWithBumpers, robotLengthWithBumpers,
    frontOfGearToRobotCenter, WheelDiameter;
    public RobotDimensions(double wheelbaseWidth, double wheelbaseLength, double robotWidthWithBumpers, double robotLengthWithBumpers,
                           double frontOfGearToRobotCenter, double wheelDiameter) {
        this.wheelbaseLength = wheelbaseLength;
        this.wheelbaseWidth = wheelbaseWidth;
        this.frontOfGearToRobotCenter = frontOfGearToRobotCenter;
        this.WheelDiameter = wheelDiameter;
        this.robotLengthWithBumpers = robotLengthWithBumpers;
        this.robotWidthWithBumpers = robotWidthWithBumpers;
    }
}
