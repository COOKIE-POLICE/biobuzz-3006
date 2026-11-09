package org.firstinspires.ftc.teamcode;

import com.pedropathing.math.Pose;

public class Area {
    public double x;
    public double y;
    public double width;
    public double height;

    public Area(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean checkPose(Pose pose) {
        return (this.x <= pose.x() && pose.x() <= this.x + this.width) && (this.y <= pose.y() && pose.y() <= this.y + this.height);
    }
}
