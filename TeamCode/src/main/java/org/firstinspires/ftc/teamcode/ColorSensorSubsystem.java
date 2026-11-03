package org.firstinspires.ftc.teamcode;


import com.seattlesolvers.solverslib.command.SubsystemBase;


// Limelight positioned on the intake.
// Limelight has pipeline configured https://github.com/hivevisionftc/Hive-Vision/blob/main/docs/detector_tracks/limelight_3a_setup.md
// The limelight is going to track the pollen and nectar coming in.
// It prioritizes the one that looks most “south” to the camera.
// There is a designated line in software. If the center of the element being tracked passes that line (The line is to make sure we don’t log a single element two times), its element (If it’s yellow it is pollen, if it’s red or blue it’s nectar) gets logged into a  deque (However, this also means that when the driver presses eject you have to track the reverse as well).
// The launcher has two Interpolated Tree maps that map distance to the hives and the optimal launcher velocity, based on whether it’s a nectar or pollen  (Alternatively we get Mr. Lin to help us do physics to determine that).
public class ColorSensorSubsystem extends SubsystemBase {
}
