package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;

import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;


// Limelight detects color and sends it into an array. Index 0 means its closest to being launched.
// Two continuous axon mini mk1 servo + relative encoder for lazy susan.
// One positional axon max mk1 servo for angling hood.
// One 6000 RPM motor for flywheel.
// Two DC motor for intake.

// Blocker driven by positional gobilda servo. - DONE

// Idea for auto pathing:
// The field is split up into four quadrants.
// Web camera is going to detect which hive is tipped already.
// If robot is in south quadrant and hive is tipped there then we know the target hive is north.


// This is going to be turned into an abstract class later on so we fill in what north or south hive is.


@TeleOp(name = "Base TeleOp")
public class BaseTeleOp extends CommandOpMode {
    GamepadEx gamepad = new GamepadEx(gamepad1);
    private PIDFGrapher pidfGrapher;
    private FollowerSubsystem followerSubsystem;
    private LauncherSubsystem outtakeSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private BlockerSubsystem blockerSubsystem;

    @Override
    public void initialize() {
        followerSubsystem = new FollowerSubsystem(hardwareMap, gamepad, Preferences.Poses.redHiveSouth);
        outtakeSubsystem = new LauncherSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        blockerSubsystem = new BlockerSubsystem(hardwareMap);
        pidfGrapher = new PIDFGrapher(telemetry);

        gamepad.getGamepadButton(Preferences.goToPoseButton).whenPressed(new GoToPoseCommand(followerSubsystem, Preferences.redHiveSouthSide));
        gamepad.getGamepadButton(Preferences.outtakeButton)
                .toggleWhenPressed(
                        new InstantCommand(() -> outtakeSubsystem.start(), outtakeSubsystem),
                        new InstantCommand(() -> outtakeSubsystem.start(), outtakeSubsystem)
                );
        new Trigger(() -> gamepad.getTrigger(Preferences.intakeTrigger) > 0)
                .whenActive(new InstantCommand(() -> intakeSubsystem.start(), intakeSubsystem))
                .whenInactive(new InstantCommand(() -> intakeSubsystem.stop(), intakeSubsystem));
        new Trigger(() -> gamepad.getTrigger(Preferences.ejectTrigger) > 0)
                .whenActive(new InstantCommand(() -> intakeSubsystem.eject(), intakeSubsystem))
                .whenInactive(new InstantCommand(() -> intakeSubsystem.stop(), intakeSubsystem));
        gamepad.getGamepadButton(Preferences.blockButton)
                .whenPressed(new InstantCommand(() -> blockerSubsystem.admit()))
                .whenReleased(new InstantCommand(() -> blockerSubsystem.prevent()));
    }

    @Override
    public void run() {
        scheduleRobotCentricIfAnyJoystickIsMoved();
        pidfGrapher.update();
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