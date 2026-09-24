package org.firstinspires.ftc.teamcode;


// This follower subsystem was created cuz if so I can take advantage of command requirements.
// This way in TeleOp I can interrupt auto pathing with joystick and vice versa.

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;

public class FollowerSubsystem {
    private final Follower follower;
    public FollowerSubsystem(Follower follower) {
        this.follower = follower;
    }
    public void robotCentric() {
        ManualDrive.driveOrHold(
                follower,
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );
        follower.update();
    }
    public void fieldCentric() {
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x,
                follower.pose().heading()
        );
        ManualDrive.driveOrHold(follower, powers);
        follower.update();

    }
}
