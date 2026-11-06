package org.firstinspires.ftc.teamcode;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.commands.blockersubsystem.AdmitCommand;
import org.firstinspires.ftc.teamcode.commands.blockersubsystem.PreventCommand;
import org.firstinspires.ftc.teamcode.commands.followersubsystem.RobotCentricCommand;
import org.firstinspires.ftc.teamcode.commands.intakesubsystem.IntakeEjectCommand;
import org.firstinspires.ftc.teamcode.commands.intakesubsystem.IntakeStartCommand;
import org.firstinspires.ftc.teamcode.commands.intakesubsystem.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.launchersubsystem.LauncherStartCommand;
import org.firstinspires.ftc.teamcode.commands.launchersubsystem.LauncherStopCommand;
import org.firstinspires.ftc.teamcode.commands.pidfgraphingsubsystem.GraphPIDFCommand;
import org.firstinspires.ftc.teamcode.subsystem.BlockerSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.FollowerSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.IndicatorSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.LauncherSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.LazySusanSubsystem;


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

// Limelight positioned on the intake.
// Limelight has pipeline configured https://github.com/hivevisionftc/Hive-Vision/blob/main/docs/detector_tracks/limelight_3a_setup.md
// The limelight is going to track the pollen and nectar coming in.
// It prioritizes the one that looks most “south” to the camera.
// There is a designated line in software. If the center of the element being tracked passes that line (The line is to make sure we don’t log a single element two times), its element (If it’s yellow it is pollen, if it’s red or blue it’s nectar) gets logged into a  deque (However, this also means that when the driver presses eject you have to track the reverse as well).
// The launcher has two Interpolated Tree maps that map distance to the hives and the optimal launcher velocity, based on whether it’s a nectar or pollen  (Alternatively we get Mr. Lin to help us do physics to determine that).

@TeleOp(name = "Base TeleOp")
public class BaseTeleOp extends CommandOpMode {
    protected Pose hiveSouth;
    protected Pose hiveNorth;
    GamepadEx gamepad = new GamepadEx(gamepad1);
    private PIDFGraphingSubsystem pidfGraphingSubsystem;
    private FollowerSubsystem followerSubsystem;
    private LauncherSubsystem launcherSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private BlockerSubsystem blockerSubsystem;
    private IndicatorSubsystem indicatorSubsystem;
    private LazySusanSubsystem lazySusanSubsystem;

    public BaseTeleOp(Pose hiveNorth, Pose hiveSouth) {
        this.hiveNorth = hiveNorth;
        this.hiveSouth = hiveSouth;
    }

    @Override
    public void preRun() {
        gamepad.getGamepadButton(Preferences.launchButton)
                .toggleWhenPressed(
                        new LauncherStartCommand(launcherSubsystem),
                        new LauncherStopCommand(launcherSubsystem)
                );

        new Trigger(() -> gamepad.getTrigger(Preferences.intakeTrigger) > 0)
                .whileActiveOnce(
                        new IntakeStartCommand(intakeSubsystem)
                )
                .whenInactive(
                        new IntakeStopCommand(intakeSubsystem)
                );

        new Trigger(() -> gamepad.getTrigger(Preferences.ejectTrigger) > 0)
                .whileActiveOnce(
                        new IntakeEjectCommand(intakeSubsystem)
                )
                .whenInactive(
                        new IntakeStopCommand(intakeSubsystem)
                );

        gamepad.getGamepadButton(Preferences.blockButton)
                .whenPressed(new AdmitCommand(blockerSubsystem))
                .whenReleased(new PreventCommand(blockerSubsystem));

        Trigger atLauncherSetPoint = new Trigger(() -> launcherSubsystem.atSetPoint());

        atLauncherSetPoint
                .whileActiveContinuous(new InstantCommand(() -> indicatorSubsystem.turnGreen(), indicatorSubsystem));

        atLauncherSetPoint
                .negate()
                .whileActiveContinuous(new InstantCommand(() -> indicatorSubsystem.turnRed(), indicatorSubsystem));

        lazySusanSubsystem.setDefaultCommand(new RunCommand(() -> lazySusanSubsystem.update(), lazySusanSubsystem));
        followerSubsystem.setDefaultCommand(new RobotCentricCommand(followerSubsystem));
        pidfGraphingSubsystem.setDefaultCommand(new GraphPIDFCommand(pidfGraphingSubsystem));
    }

    @Override
    public void initialize() {
        followerSubsystem = new FollowerSubsystem(hardwareMap, gamepad, Preferences.Poses.redHiveSouth);
        launcherSubsystem = new LauncherSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        blockerSubsystem = new BlockerSubsystem(hardwareMap);
        pidfGraphingSubsystem = new PIDFGraphingSubsystem(telemetry);
        indicatorSubsystem = new IndicatorSubsystem(hardwareMap);
        lazySusanSubsystem = new LazySusanSubsystem(hardwareMap, followerSubsystem);
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