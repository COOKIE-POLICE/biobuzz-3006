package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.Robot;

@TeleOp(name = "Base TeleOp")
public class BaseTeleOp extends CommandOpMode {

    private FollowerSubsystem followerSubsystem;
    @Override
    public void initialize() {

    }
    public void scheduleRobotCentricIfAnyJoystickIsMoved() {
        double deadZone = 0.05;
        boolean moved = Math.abs(gamepad1.left_stick_x) > deadZone || Math.abs(gamepad1.left_stick_y) > deadZone || Math.abs(gamepad1.right_stick_x) > deadZone || Math.abs(gamepad1.right_stick_y) > deadZone;
        if (moved) {
            RobotCentricCommand robotCentricCommand = new RobotCentricCommand(followerSubsystem);
            robotCentricCommand.schedule(true);
        }
    }
}