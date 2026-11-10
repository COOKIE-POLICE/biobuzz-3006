package org.firstinspires.ftc.teamcode.subsystem.hivedetectionsubsystem;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Array;
import org.firstinspires.ftc.teamcode.Preferences;
import org.firstinspires.ftc.teamcode.Side;
import org.firstinspires.ftc.teamcode.subsystem.FollowerSubsystem;


public class HiveDetectionSubsystem extends SubsystemBase {

    private final FollowerSubsystem followerSubsystem;
    public int currentPipeline;
    public Limelight3A limelight;
    protected int scoringPipeline;
    protected int audiencePipeline;
    protected int[] audienceTagIds;
    protected int[] scoringTagIds;
    private Side side;

    public HiveDetectionSubsystem(HardwareMap hardwareMap, FollowerSubsystem followerSubsystem, int audiencePipeline, int scoringPipeline, int[] audienceTagIds, int[] scoringTagIds) {
        this.followerSubsystem = followerSubsystem;
        this.scoringPipeline = scoringPipeline;
        this.audiencePipeline = audiencePipeline;
        limelight = hardwareMap.get(Limelight3A.class, Preferences.DeviceNames.LIMELIGHT);
        this.audienceTagIds = audienceTagIds;
        this.scoringTagIds = scoringTagIds;
    }

    public Side getSide() {
        return side;
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

    public void updatePipeline() {
        if (followerSubsystem.getSide() == Side.Audience) {
            limelight.pipelineSwitch(audiencePipeline);
        } else if (followerSubsystem.getSide() == Side.Scoring) {
            limelight.pipelineSwitch(scoringPipeline);
        }
    }
}
