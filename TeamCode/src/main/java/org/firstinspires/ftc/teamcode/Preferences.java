package org.firstinspires.ftc.teamcode;


import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;


public class Preferences {

    // Goes to right in front of the hive, where the optimal shooting position is.
    public static GamepadKeys.Button launchButton = GamepadKeys.Button.RIGHT_BUMPER;
    public static GamepadKeys.Button blockButton = GamepadKeys.Button.A;
    public static GamepadKeys.Trigger intakeTrigger = GamepadKeys.Trigger.RIGHT_TRIGGER;
    public static GamepadKeys.Trigger ejectTrigger = GamepadKeys.Trigger.LEFT_TRIGGER;

    public static GamepadKeys.Button goToOptimalScoringPoseButton = GamepadKeys.Button.B;

    public static class HivePoses {
        public static Pose redAudience = new Pose(57.75, 57);
        public static Pose redScoring = new Pose(57.75, 84.5);

        public static Pose blueAudience = new Pose(84, 55);
        public static Pose blueScoring = new Pose(84, 83);
    }

    public static class LaunchPoses {
        public static Pose redAudience = new Pose(56.0, 8.0, Math.toRadians(270));
        public static Pose redScoring = new Pose(56.0, 8.0, Math.toRadians(270));
        public static Pose blueAudience = new Pose(56.0, 8.0, Math.toRadians(270));

        public static Pose blueScoring = new Pose(56.0, 8.0, Math.toRadians(270));
    }

    public static class StartingPoses {
        public static Pose redAudience = new Pose(56.0, 8.0, Math.toRadians(270));


        // Replace these with correct poses
        public static Pose redScoring = new Pose(56.0, 8.0, Math.toRadians(270));

        public static Pose blueAudience = new Pose(56.0, 8.0, Math.toRadians(270));

        public static Pose blueScoring = new Pose(56.0, 8.0, Math.toRadians(270));
    }

    public static class Areas {
        public static Area audienceAreas = new Area(0, 0, 144, 72);
        public static Area scoringAreas = new Area(0, 72, 144, 72);
    }

    public static class AprilTagIdentifiers {
        public static int[] redAudience = new int[]{34, 35, 36, 37};
        public static int[] redScoring = new int[]{30, 31, 32, 33};

        public static int[] blueAudience = new int[]{38, 39, 40, 41};
        public static int[] blueScoring = new int[]{42, 43, 44, 45};
    }

    public static class LimelightPipelines {
        public static int redAudience = 0;
        public static int redScoring = 1;
        public static int blueAudience = 2;
        public static int blueScoring = 3;
    }

    public static class LimelightRollThresholds {
        public static double activeStart = 0;
        public static double activeEnd = 0;

        public static double inactiveStart = 0;
        public static double inactiveEnd = 0;
    }

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
}
