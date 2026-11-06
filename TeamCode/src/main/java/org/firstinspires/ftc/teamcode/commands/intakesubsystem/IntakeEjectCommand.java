package org.firstinspires.ftc.teamcode.commands.intakesubsystem;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;

public class IntakeEjectCommand extends CommandBase {
    private final IntakeSubsystem intake;

    public IntakeEjectCommand(IntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.eject();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}