package org.firstinspires.ftc.teamcode;


import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;


@Configurable
public class Preferences {
    public static GamepadKeys.Button goToPoseButton = GamepadKeys.Button.A;
    public static GamepadKeys.Button outtakeButton = GamepadKeys.Button.RIGHT_BUMPER;
    public static double outtakeP = 10;
    public static double outtakeI = 10;
    public static double outtakeD = 10;

    public static Pose redHiveSouthSide = new Pose(58.21093750000001, 23.655598958333343, 90);
}
