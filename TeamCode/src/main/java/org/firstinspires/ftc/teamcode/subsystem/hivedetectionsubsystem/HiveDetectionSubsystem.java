package org.firstinspires.ftc.teamcode.subsystem.hivedetectionsubsystem;

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
import org.firstinspires.ftc.teamcode.Side;


// Stop making the hive detection subsystem depend on the follower subsystem, make a command for that.
public class HiveDetectionSubsystem extends SubsystemBase {
    private final Quadrant startingQuadrant;
    public int currentPipeline;
    public Limelight3A limelight;
    private Pose optimalShootingPose;

    public HiveDetectionSubsystem(HardwareMap hardwareMap, Quadrant quadrant) {
        this.startingQuadrant = quadrant;
        limelight = hardwareMap.get(Limelight3A.class, Preferences.DeviceNames.LIMELIGHT);
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
    }

    public void stop() {
        limelight.stop();
    }

    public void update() {
        updatePipeline();
        // You don't need to check the roll of the tags, just check if they are seen and what quadrant the robot is in.
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            for (LLResultTypes.FiducialResult tag : result.getFiducialResults()) {
                Pose3D targetPose = tag.getCameraPoseTargetSpace();
                if (targetPose != null) {
                    int tagId = tag.getFiducialId();
                    if (currentPipeline == scoringPipeline) {
                        if (Array.contains(scoringTagIds, tagId)) {
                            side = Side.Scoring;
                        }
                    } else if (currentPipeline == audiencePipeline) {
                        if (Array.contains(scoringTagIds, tagId)) {
                            side = Side.Audience;
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
