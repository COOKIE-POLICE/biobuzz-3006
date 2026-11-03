package org.firstinspires.ftc.teamcode;


// This follower subsystem was created cuz if so I can take advantage of command requirements.
// This way in TeleOp I can interrupt auto pathing with joystick and vice versa.

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.pedro.Constants;

public class FollowerSubsystem extends SubsystemBase {
    public final Follower follower;
    private final GamepadEx gamepad;
    public FollowerSubsystem(HardwareMap hardwareMap, GamepadEx gamepad) {
        this.follower = Constants.create(hardwareMap);
        this.gamepad = gamepad;
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
}
