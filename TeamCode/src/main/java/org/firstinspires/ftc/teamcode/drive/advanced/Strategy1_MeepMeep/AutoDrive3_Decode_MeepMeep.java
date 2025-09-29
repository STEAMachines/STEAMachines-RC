package org.firstinspires.ftc.teamcode.drive.advanced.Strategy1_MeepMeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.drive.advanced.PoseStorage;

@Autonomous(name="Autonomous3_DECODE_MeepMeep", group="STEAMachines_DECODE")
public class AutoDrive3_Decode_MeepMeep extends LinearOpMode {
    @Override
    public void runOpMode() {
        SampleTankDrive drive = new SampleTankDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-52, 52, -Math.PI/4);
        drive.setPoseEstimate(startPose);
        waitForStart();
        if(!isStopRequested()) return;
        Trajectory traj = drive.trajectoryBuilder(startPose)
                .lineTo(new Vector2d(-2))
                .build();
        drive.followTrajectory(traj);
        PoseStorage.currentPose = drive.getPoseEstimate();
    }
}
