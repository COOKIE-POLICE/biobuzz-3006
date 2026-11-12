package org.firstinspires.ftc.teamcode.subsystem;

import com.pedropathing.math.Pose;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Array;
import org.firstinspires.ftc.teamcode.Preferences;
import org.firstinspires.ftc.teamcode.Quadrant;


// Stop making the hive detection subsystem depend on the follower subsystem, make a command for that.
public class HiveDetectionSubsystem extends SubsystemBase {
    private final Quadrant startingQuadrant;
    public int currentPipeline;
    public Limelight3A limelight;
    private Pose optimalLaunchPose;

    public HiveDetectionSubsystem(HardwareMap hardwareMap, Quadrant quadrant) {
        this.startingQuadrant = quadrant;
        limelight = hardwareMap.get(Limelight3A.class, Preferences.DeviceNames.LIMELIGHT);
    }

    public Pose getOptimalLaunchPose() {
        return optimalLaunchPose;
    }

    public int getScoringPipeline() {
        switch (startingQuadrant) {
            case BlueScoring:
                return Preferences.LimelightPipelines.blueScoring;
            case BlueAudience:
                return Preferences.LimelightPipelines.blueScoring;
            case RedAudience:
                return Preferences.LimelightPipelines.redScoring;
            case RedScoring:
                return Preferences.LimelightPipelines.redScoring;
        }
        return 5;
    }

    public int getAudiencePipeline() {
        switch (startingQuadrant) {
            case RedScoring:
                return Preferences.LimelightPipelines.redAudience;
            case RedAudience:
                return Preferences.LimelightPipelines.redAudience;
            case BlueAudience:
                return Preferences.LimelightPipelines.blueAudience;
            case BlueScoring:
                return Preferences.LimelightPipelines.blueAudience;
        }
        return 5;
    }

    public int[] getAudienceTagIdentifiers() {
        switch (startingQuadrant) {
            case RedAudience:
                return Preferences.AprilTagIdentifiers.redAudience;
            case RedScoring:
                return Preferences.AprilTagIdentifiers.redAudience;
            case BlueScoring:
                return Preferences.AprilTagIdentifiers.blueAudience;
            case BlueAudience:
                return Preferences.AprilTagIdentifiers.blueAudience;
        }
    }

    public int[] getScoringTagIdentifiers() {
        switch (startingQuadrant) {
            case BlueAudience:
                return Preferences.AprilTagIdentifiers.blueScoring;
            case BlueScoring:
                return Preferences.AprilTagIdentifiers.blueScoring;
            case RedAudience:
                return Preferences.AprilTagIdentifiers.redScoring;
            case RedScoring:
                return Preferences.AprilTagIdentifiers.redScoring;
        }
        return new int[]{0, 0, 0, 0};
    }


    public void stop() {
        limelight.stop();
    }

    public void update() {
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            for (LLResultTypes.FiducialResult tag : result.getFiducialResults()) {
                Pose3D targetPose = tag.getCameraPoseTargetSpace();
                if (targetPose != null) {
                    int tagId = tag.getFiducialId();
                    if (currentPipeline == getScoringPipeline()) {
                        if (Array.contains(getScoringTagIdentifiers(), tagId)) {
                            switch (startingQuadrant) {
                                case BlueScoring:
                                    optimalLaunchPose = Preferences.LaunchPoses.blueScoring;
                                case BlueAudience:
                                    optimalLaunchPose = Preferences.LaunchPoses.blueScoring;
                                case RedScoring:
                                    optimalLaunchPose = Preferences.LaunchPoses.redScoring;
                                case RedAudience:
                                    optimalLaunchPose = Preferences.LaunchPoses.redScoring;
                            }
                        }
                    } else if (currentPipeline == getAudiencePipeline()) {
                        if (Array.contains(getAudienceTagIdentifiers(), tagId)) {
                            switch (startingQuadrant) {
                                case RedScoring:
                                    optimalLaunchPose = Preferences.LaunchPoses.redAudience;
                                case RedAudience:
                                    optimalLaunchPose = Preferences.LaunchPoses.redAudience;
                                case BlueAudience:
                                    optimalLaunchPose = Preferences.LaunchPoses.blueAudience;
                                case BlueScoring:
                                    optimalLaunchPose = Preferences.LaunchPoses.blueAudience;
                            }
                        }
                    }
                }

            }
        }
    }

    public void switchToScoringPipeline() {
        limelight.pipelineSwitch(getScoringPipeline());
    }

    public void switchToAudiencePipeline() {
        limelight.pipelineSwitch(getAudiencePipeline());
    }
}
