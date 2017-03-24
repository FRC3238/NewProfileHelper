package MotionPacker;

/**
 * Created by Julee on 3/23/2017.
 */
public class TrajectoryParameters {
    double maxJerk, maxAccel, maxVel;
    double dt;
    public TrajectoryParameters(double dt, double vel, double acc, double jerk) {
        this.dt = dt;
        this.maxAccel = acc;
        this.maxVel = vel;
        this.maxJerk = jerk;
    }
    public void setDt(double dt) {
        this.dt = dt;
    }

    public void setMaxAccel(int maxAccel) {
        this.maxAccel = maxAccel;
    }

    public void setMaxJerk(int maxJerk) {
        this.maxJerk = maxJerk;
    }

    public void setMaxVel(int maxVel) {
        this.maxVel = maxVel;
    }

    public double getDt() {
        return dt;
    }

    public double getMaxAccel() {
        return maxAccel;
    }

    public double getMaxJerk() {
        return maxJerk;
    }

    public double getMaxVel() {
        return maxVel;
    }
}
