package org.firstinspires.ftc.teamcode.commands.intakesubsystem;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;

public class IntakeStartCommand extends CommandBase {
    private final IntakeSubsystem intake;

    public IntakeStartCommand(IntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.intake();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}