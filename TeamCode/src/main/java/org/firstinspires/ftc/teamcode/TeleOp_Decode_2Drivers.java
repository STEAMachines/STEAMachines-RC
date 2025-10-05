package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp(name="TeleOp-2Drivers_Decode", group="STEAMachines_DECODE")
public class TeleOp_Decode_2Drivers extends LinearOpMode {
    final double DESIRED_DISTANCE = 12.0;
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor intakeMotors;
    private DcMotor launcherMotors;
    private AprilTagProcessor aprilTag;
    private AprilTagDetection desiredTag;
    private VisionPortal visionPortal;
    private static final boolean USE_WEBCAM = true;
    private static final int DESIRED_TAG_ID = 20;

    public void runOpMode() {
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        intakeMotors = hardwareMap.get(DcMotor.class, "intakeMotors");
        launcherMotors = hardwareMap.get(DcMotor.class, "launcherMotors");
        waitForStart();
        initializeAprilTag();
        displayWebcamVision();
        while(opModeIsActive()) {
            double leftPower, rightPower;
            double drive = gamepad1.left_stick_y;
            double turn = -gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -1.0, 1.0);
            rightPower = Range.clip(drive - turn, -1.0, 1.0);
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);

            //left_bumper && right_bumper to move....
            if(gamepad1.left_bumper) {
                intakeMotors.setPower(1);
            }
            else if(gamepad1.right_bumper) {
                intakeMotors.setPower(-1);
            }
            else {
                intakeMotors.setPower(0);
            }
            //left_trigger && right_trigger to move....
            if(gamepad2.left_trigger == 1.0) {
                launcherMotors.setPower(1);
            }
            else if(gamepad2.right_trigger == 1.0) {
                launcherMotors.setPower(-1);
            }
            else {
                launcherMotors.setPower(0);
            }
        }
    }
    public void initializeAprilTag() {
        aprilTag = new AprilTagProcessor.Builder().build();
        VisionPortal.Builder builder = new VisionPortal.Builder();
        if(USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        }
        else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }
        builder.addProcessor(aprilTag);
        visionPortal = builder.build();
    }
    public void displayWebcamVision() {
        boolean targetFound = true;
        double drive = 0;
        double turn = 0;
        while(opModeIsActive()) {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            for(AprilTagDetection detection:currentDetections) {
                if(detection.metadata != null) {
                    if(DESIRED_TAG_ID < 0 || (detection.id == DESIRED_TAG_ID)) {
                        targetFound = true;
                        desiredTag = detection;
                        break;
                    }
                    else {

                    }
                }
                else {

                }
                telemetry.addData("ID", detection.id);
                telemetry.addData("Range", detection.ftcPose.range);
                telemetry.addData("Bearing", detection.ftcPose.bearing);
            }
            if(targetFound) {
                double rangeError = (desiredTag.ftcPose.range - DESIRED_DISTANCE);
                double headingError = (desiredTag.ftcPose.bearing);
            }
            else {
                targetFound = false;
            }
        }
    }
}
