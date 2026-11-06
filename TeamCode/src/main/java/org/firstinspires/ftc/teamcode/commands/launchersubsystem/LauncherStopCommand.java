package org.firstinspires.ftc.teamcode.commands.launchersubsystem;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.LauncherSubsystem;

public class LauncherStopCommand extends CommandBase {
    private final LauncherSubsystem launcherSubsystem;

    public LauncherStopCommand(LauncherSubsystem launcherSubsystem) {
        this.launcherSubsystem = launcherSubsystem;
        addRequirements(launcherSubsystem);
    }

    @Override
    public void execute() {
        launcherSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}