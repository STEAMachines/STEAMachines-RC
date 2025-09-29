package org.firstinspires.ftc.teamcode.drive.advanced.Strategy1_MeepMeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.drive.advanced.PoseStorage;

@Autonomous(name="Autonomous2_DECODE_MeepMeep", group="STEAMachines_DECODE")
public class AutoDrive2_Decode_MeepMeep extends LinearOpMode {
    @Override
    public void runOpMode() {
        SampleTankDrive drive = new SampleTankDrive(hardwareMap);
        Pose2d startPose = new Pose2d(60, -15, Math.PI);
        drive.setPoseEstimate(startPose);
        waitForStart();
        if(!isStopRequested()) return;
        Trajectory traj = drive.trajectoryBuilder(startPose)
                .lineToLinearHeading(new Pose2d(0, Math.toRadians(60)))
                .strafeTo(new Vector2d(-34, -34))
                .strafeTo(new Vector2d(30, 30))
                .build();
        drive.followTrajectory(traj);
        PoseStorage.currentPose = drive.getPoseEstimate();
    }
}
