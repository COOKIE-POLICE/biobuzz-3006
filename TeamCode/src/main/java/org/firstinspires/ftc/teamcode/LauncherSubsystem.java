package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.util.InterpLUT;

public class LauncherSubsystem extends SubsystemBase {
    private InterpLUT interpolatedTable;
    private enum TargetVelocityCalculationStrategy {
        InterpolatedTable,
        PhysicsEquation,
    }
    private final TunedMotor tunedMotor;
    private final TargetVelocityCalculationStrategy targetVelocityCalculationStrategy = TargetVelocityCalculationStrategy.InterpolatedTable;
    public LauncherSubsystem(HardwareMap hardwareMap) {
        this.tunedMotor = new TunedMotor(hardwareMap, Preferences.DeviceNames.OUTTAKE, 0, 0, 0, 0);
        initializeInterpolatedTable();
    }
    public void initializeInterpolatedTable() {
        interpolatedTable = new InterpLUT();
        interpolatedTable.add(0, 0);
        interpolatedTable.add(49.66, 1625);
        interpolatedTable.add(65.43, 1650);
        interpolatedTable.add(82.82, 1700);
        interpolatedTable.add(91.74, 1750);
        interpolatedTable.add(93.37, 1750);
        interpolatedTable.add(100.26, 1750);
        interpolatedTable.add(110.72, 1850);
        interpolatedTable.add(119.71, 1900);
        interpolatedTable.add(138.90, 1950);
        interpolatedTable.add(155.60, 2025);
        interpolatedTable.add(170.1182, 2230);
        interpolatedTable.add(200.0, 2400);
        interpolatedTable.add(240.0, 2600);
        interpolatedTable.add(300.0, 3000);
        interpolatedTable.createLUT();
    }
    public void start() {
        tunedMotor.update(1000);
    }
    public void stop() {
        tunedMotor.update(0);
    }

}