package org.firstinspires.ftc.teamcode;


import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;


@Configurable
public class Preferences {

    // Goes to right in front of the hive, where the optimal shooting position is.
    public static GamepadKeys.Button goToPoseButton = GamepadKeys.Button.DPAD_UP;
    public static GamepadKeys.Button outtakeButton = GamepadKeys.Button.RIGHT_BUMPER;
    public static GamepadKeys.Button blockButton = GamepadKeys.Button.A;
    // Forces turret to keep looking at the hive
    public static GamepadKeys.Button lockOnButton = GamepadKeys.Button.Y;
    public static GamepadKeys.Trigger intakeTrigger = GamepadKeys.Trigger.RIGHT_TRIGGER;
    public static GamepadKeys.Trigger ejectTrigger = GamepadKeys.Trigger.LEFT_TRIGGER;
    public static double outtakeP = 10;
    public static double outtakeI = 10;
    public static double outtakeD = 10;
    public static Pose redHiveSouthSide = new Pose(58.21093750000001, 23.655598958333343, 90);

    public static class DeviceNames {
        public static final String RIGHT_FRONT_MOTOR = "rightFrontMotor";
        public static final String RIGHT_BACK_MOTOR = "rightBackMotor";
        public static final String LEFT_REAR_MOTOR = "leftBackMotor";
        public static final String LEFT_FRONT_MOTOR = "leftFrontMotor";
        public static final String OUTTAKE = "launcherMotor";
        public static final String PINPOINT_ODOMETRY = "pinpoint";
        public static final String LIMELIGHT = "limelight";
        public static final String LEFT_INTAKE = "leftIntake";
        public static final String RIGHT_INTAKE = "rightIntake";
        public static final String BLOCKER = "blocker";

        public static final String INDICATOR = "indicator";
    }
}
