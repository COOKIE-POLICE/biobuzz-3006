package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PIDFGrapher {
    private final MultipleTelemetry multipleTelemetry;

    public PIDFGrapher(Telemetry telemetry) {
        multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    public void update() {
        multipleTelemetry.addData("Lazy Susan Proportional", Preferences.LazySusanPIDF.p);
        multipleTelemetry.addData("Lazy Susan Integral", Preferences.LazySusanPIDF.i);
        multipleTelemetry.addData("Lazy Susan Derivative", Preferences.LazySusanPIDF.d);
        multipleTelemetry.addData("Lazy Susan Feedforward", Preferences.LazySusanPIDF.f);

        multipleTelemetry.addData("Launcher Proportional", Preferences.LauncherPIDF.p);
        multipleTelemetry.addData("Launcher Integral", Preferences.LauncherPIDF.i);
        multipleTelemetry.addData("Launcher Derivative", Preferences.LauncherPIDF.d);
        multipleTelemetry.addData("Launcher Feedforward", Preferences.LauncherPIDF.f);
        multipleTelemetry.update();
    }
}
