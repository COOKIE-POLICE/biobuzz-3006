package org.firstinspires.ftc.teamcode.commands.launchersubsystem;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.LauncherSubsystem;

public class LauncherStartCommand extends CommandBase {
    private final LauncherSubsystem launcherSubsystem;

    public LauncherStartCommand(LauncherSubsystem launcherSubsystem) {
        this.launcherSubsystem = launcherSubsystem;
        addRequirements(launcherSubsystem);
    }

    @Override
    public void execute() {
        launcherSubsystem.start();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}