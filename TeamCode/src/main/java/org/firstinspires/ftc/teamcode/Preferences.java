package org.firstinspires.ftc.teamcode;


import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;


public class Preferences {

    // Goes to right in front of the hive, where the optimal shooting position is.
    public static GamepadKeys.Button launchButton = GamepadKeys.Button.RIGHT_BUMPER;
    public static GamepadKeys.Button blockButton = GamepadKeys.Button.A;
    public static GamepadKeys.Trigger intakeTrigger = GamepadKeys.Trigger.RIGHT_TRIGGER;
    public static GamepadKeys.Trigger ejectTrigger = GamepadKeys.Trigger.LEFT_TRIGGER;


    public static class LauncherPIDF {
        public static double p = 10;
        public static double i = 10;
        public static double d = 10;
        public static double f = 0.0;
    }

    public static class DeviceNames {
        public static final String RIGHT_FRONT_MOTOR = "Right Front Motor";
        public static final String RIGHT_BACK_MOTOR = "Right Back Motor";
        public static final String LEFT_REAR_MOTOR = "Left Back Motor";
        public static final String LEFT_FRONT_MOTOR = "Left Front Motor";
        public static final String LEFT_LAZY_SUSAN = "Left Lazy Susan";
        public static final String OUTTAKE = "Outtake";
        public static final String PINPOINT = "Pinpoint";
        public static final String LIMELIGHT = "Limelight";
        public static final String LEFT_INTAKE = "Left Intake";
        public static final String RIGHT_INTAKE = "Right Intake";
        public static final String BLOCKER = "Blocker";
        public static final String LEFT_INDICATOR = "Left Indicator";
        public static final String RIGHT_LAZY_SUSAN = "Right Lazy Susan";

        public static final String LAZY_SUSAN_ENCODER = "Lazy Susan Encoder";
        public static final String RIGHT_INDICATOR = "Right Indicator";
    }

    // Why does lazy susan need feedforward?
    // Well, IDk ngl
    public static class LazySusanPIDF {
        public static double p = 0.0;
        public static double i = 0.0;
        public static double d = 0.0;
        public static double f = 0.0;
    }

    public static class Poses {
        public static final Pose redHiveSouth = new Pose(57.5, 56.5);
        public static final Pose redHiveNorth = new Pose(57.5, 84.5);
        public static final Pose blueHiveSouth = new Pose(84, 55);
        public static final Pose blueHiveNorth = new Pose(84.5, 82);
    }
}
