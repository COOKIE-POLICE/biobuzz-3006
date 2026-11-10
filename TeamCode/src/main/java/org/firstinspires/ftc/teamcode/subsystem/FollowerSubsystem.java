package org.firstinspires.ftc.teamcode.subsystem;


// This follower subsystem was created cuz if so I can take advantage of command requirements.
// This way in TeleOp I can interrupt auto pathing with joystick and vice versa.

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Preferences;
import org.firstinspires.ftc.teamcode.Side;
import org.firstinspires.ftc.teamcode.pedro.Constants;


public class FollowerSubsystem extends SubsystemBase {

    public final Follower follower;
    private final GamepadEx gamepad;
    private final Pose hivePose;
    private Side side;

    public FollowerSubsystem(HardwareMap hardwareMap, GamepadEx gamepad, Pose hivePose) {
        this.follower = Constants.create(hardwareMap);
        this.gamepad = gamepad;
        this.hivePose = hivePose;
    }

    public Side getSide() {
        return side;
    }

    public void update() {
        if (Preferences.Areas.audienceAreas.checkPose(getPose())) {
            side = Side.Audience;
        } else if (Preferences.Areas.scoringAreas.checkPose(getPose())) {
            side = Side.Scoring;
        }
    }

    public Pose getPose() {
        return follower.pose();
    }

    public void robotCentric() {
        ManualDrive.driveOrHold(
                follower,
                -gamepad.getLeftY(),
                gamepad.getLeftX(),
                gamepad.getRightX()
        );
        follower.update();
    }

    public void fieldCentric() {
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad.getLeftY(),
                gamepad.getLeftX(),
                gamepad.getRightX(),
                follower.pose().heading()
        );
        ManualDrive.driveOrHold(follower, powers);
        follower.update();
    }


    public double angleTo(double x, double y) {
        return Math.atan2(
                y - follower.pose().y(),
                x - follower.pose().x()
        );
    }

    public double angleToHive() {
        return angleTo(hivePose.x(), hivePose.y());
    }
}
