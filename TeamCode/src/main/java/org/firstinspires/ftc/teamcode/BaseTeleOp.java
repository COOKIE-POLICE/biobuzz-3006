package org.firstinspires.ftc.teamcode;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.Robot;

import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

@TeleOp(name = "Base TeleOp")
public class BaseTeleOp extends CommandOpMode {
    private FollowerSubsystem followerSubsystem;
    private OuttakeSubsystem outtakeSubsystem;
    private IntakeSubsystem intakeSubsystem;
    GamepadEx gamepad = new GamepadEx(gamepad1);
    @Override
    public void initialize() {
        followerSubsystem = new FollowerSubsystem();
        outtakeSubsystem = new OuttakeSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        gamepad.getGamepadButton(Preferences.goToPoseButton).whenPressed(new GoToPoseCommand(followerSubsystem, Preferences.redHiveSouthSide));
        gamepad.getGamepadButton(Preferences.outtakeButton)
                .toggleWhenPressed(
                        new InstantCommand(()->outtakeSubsystem.start(), outtakeSubsystem),
                        new InstantCommand(()->outtakeSubsystem.start(), outtakeSubsystem)
                        );
        new Trigger(() -> gamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0)
                .whenActive(new InstantCommand(()->intakeSubsystem.start(), intakeSubsystem))
                .whenInactive(new InstantCommand(()->intakeSubsystem.stop(), intakeSubsystem));
        new Trigger(() -> gamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0)
                .whenActive(new InstantCommand(()->intakeSubsystem.eject(), intakeSubsystem))
                .whenInactive(new InstantCommand(()->intakeSubsystem.stop(), intakeSubsystem));
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