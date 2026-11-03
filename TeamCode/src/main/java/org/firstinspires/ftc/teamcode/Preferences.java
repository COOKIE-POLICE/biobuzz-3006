package org.firstinspires.ftc.teamcode;


import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;


public class Preferences {

    // Goes to right in front of the hive, where the optimal shooting position is.
    public static GamepadKeys.Button goToPoseButton = GamepadKeys.Button.DPAD_UP;
    public static GamepadKeys.Button outtakeButton = GamepadKeys.Button.RIGHT_BUMPER;
    public static GamepadKeys.Button blockButton = GamepadKeys.Button.A;
    // Forces turret to keep looking at the hive
    public static GamepadKeys.Button lockOnButton = GamepadKeys.Button.Y;
    public static GamepadKeys.Trigger intakeTrigger = GamepadKeys.Trigger.RIGHT_TRIGGER;
    public static GamepadKeys.Trigger ejectTrigger = GamepadKeys.Trigger.LEFT_TRIGGER;
    public static Pose redHiveSouthSide = new Pose(58.21093750000001, 23.655598958333343, 90);


    public static class LauncherPIDF {
        public static double p = 10;
        public static double i = 10;
        public static double d = 10;
        public static double f = 0.0;
    }

    public static class DeviceNames {
        public static final String RIGHT_FRONT_MOTOR = "rfm";
        public static final String RIGHT_BACK_MOTOR = "rbm";
        public static final String LEFT_REAR_MOTOR = "lbm";
        public static final String LEFT_FRONT_MOTOR = "lfm";

        public static final String LEFT_LAZY_SUSAN = "lls";
        public static final String OUTTAKE = "lm";
        public static final String PINPOINT_ODOMETRY = "po";
        public static final String LIMELIGHT = "l";
        public static final String LEFT_INTAKE = "li";
        public static final String RIGHT_INTAKE = "ri";
        public static final String BLOCKER = "b";
        public static final String INDICATOR = "i";
        public static final String RIGHT_LAZY_SUSAN = "rls";

        public static final String LAZY_SUSAN_ENCODER = "lce";
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
