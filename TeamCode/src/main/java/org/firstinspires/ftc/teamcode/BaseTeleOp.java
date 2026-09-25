package org.firstinspires.ftc.teamcode;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

@TeleOp(name = "Base TeleOp")
public class BaseTeleOp extends CommandOpMode {
    private FollowerSubsystem followerSubsystem;
    GamepadEx gamepad = new GamepadEx(gamepad1);
    @Override
    public void initialize() {
        followerSubsystem = new FollowerSubsystem();
        gamepad.getGamepadButton(GamepadKeys.Button.A).whenPressed(new GoToPoseCommand(followerSubsystem, new Pose(50, 50)));
    }
    @Override
    public void run() {
        scheduleRobotCentricIfAnyJoystickIsMoved();
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