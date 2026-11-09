package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Preferences;


public class HiveDetectionSubsystem extends SubsystemBase {
    public int currentPipeline;
    public Limelight3A limelight;
    public boolean isRedAudienceHiveActive = false;
    public boolean isRedOppositeHiveActive = false;

    public boolean isBlueAudienceHiveActive = false;

    public boolean isBlueOppositeHiveActive = false;

    public HiveDetectionSubsystem(HardwareMap hardwareMap, int currentPipeline) {
        this.currentPipeline = currentPipeline;
        limelight = hardwareMap.get(Limelight3A.class, Preferences.DeviceNames.LIMELIGHT);
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
                    double tagRoll = targetPose.getOrientation().getRoll();
                    int tagId = tag.getFiducialId();
                    double totalRoll = 0;
                    if (currentPipeline == Preferences.LimelightPipelines.blueOpposite) {
                        if (tagId == 42 || tagId == 43 || tagId == 44 || tagId == 45 || tagId == 38 || tagId == 39 || tagId == 40 || tagId == 41 || tagId == 34 || tagId == 35 || tagId == 36 || tagId == 37 || tagId == 30 || tagId == 31 || tagId == 32 || tagId == 33) {
                            totalRoll += tagRoll;
                            double averageRoll = totalRoll / 4;
                            if (Preferences.LimelightRollThresholds.activeStart <= averageRoll && averageRoll <= Preferences.LimelightRollThresholds.activeEnd) {
                                this.isBlueOppositeHiveActive = true;
                                this.isBlueAudienceHiveActive = false;
                            }
                        }
                    } else if (currentPipeline == Preferences.LimelightPipelines.blueAudience) {
                        if (tagId == 38 || tagId == 39 || tagId == 40 || tagId == 41) {
                            totalRoll += tagRoll;
                            double averageRoll = totalRoll / 4;
                            if (Preferences.LimelightRollThresholds.activeStart <= averageRoll && averageRoll <= Preferences.LimelightRollThresholds.activeEnd) {
                                this.isBlueOppositeHiveActive = false;
                                this.isBlueAudienceHiveActive = true;
                            }
                        }
                    } else if (currentPipeline == Preferences.LimelightPipelines.redAudience) {
                        if (tagId == 34 || tagId == 35 || tagId == 36 || tagId == 37) {
                            totalRoll += tagRoll;
                            double averageRoll = totalRoll / 4;
                            if (Preferences.LimelightRollThresholds.activeStart <= averageRoll && averageRoll <= Preferences.LimelightRollThresholds.activeEnd) {
                                this.isRedOppositeHiveActive = false;
                                this.isRedAudienceHiveActive = true;
                            }
                        }
                    } else if (currentPipeline == Preferences.LimelightPipelines.redOpposite) {
                        if (tagId == 30 || tagId == 31 || tagId == 32 || tagId == 33) {
                            totalRoll += tagRoll;
                            double averageRoll = totalRoll / 4;
                            if (Preferences.LimelightRollThresholds.activeStart <= averageRoll && averageRoll <= Preferences.LimelightRollThresholds.activeEnd) {
                                this.isRedOppositeHiveActive = true;
                                this.isRedAudienceHiveActive = false;
                            }
                        }
                    }
                }

            }
        }

    }

    public void start() {
        limelight.pipelineSwitch(currentPipeline);
        limelight.start();
    }
}
