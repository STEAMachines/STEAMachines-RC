package org.firstinspires.ftc.teamcode.drive.advanced.Strategy2_MeeMeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.drive.advanced.PoseStorage;

@Autonomous(name="Autonomous4_DECODE_MeepMeep2", group="STEAMachines_DECODE")
public class AutoDrive4_Decode_MeepMeep2 extends LinearOpMode {
    @Override
    public void runOpMode() {
        SampleTankDrive STEAMachines_bot = new SampleTankDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-52, -52, Math.PI/4);
        STEAMachines_bot.setPoseEstimate(startPose);
        waitForStart();
        if(!isStopRequested()) return;
        Trajectory traj = STEAMachines_bot.trajectoryBuilder(startPose)
                .build();
        STEAMachines_bot.followTrajectory(traj);
        PoseStorage.currentPose = STEAMachines_bot.getPoseEstimate();
    }
}
