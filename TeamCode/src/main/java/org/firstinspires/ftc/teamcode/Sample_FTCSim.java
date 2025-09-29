package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class Sample_FTCSim extends LinearOpMode {
    private DcMotor driveLeft;
    private DcMotor driveRight;
    private DcMotor shootwheel;
    private Servo artifactstopper;
    ColorSensor color1;
    DistanceSensor distance1;
    BNO055IMU imu;
    boolean isShooting;
    double shootPower, horizontalInput, verticalInput;
    int maxDrivePower, mode, nArtifacts;

    public void runOpMode() {
        driveLeft = hardwareMap.get(DcMotor.class, "driveLeft");
        driveRight = hardwareMap.get(DcMotor.class, "driveRight");
        shootwheel = hardwareMap.get(DcMotor.class, "shootwheel");
        artifactstopper = hardwareMap.get(Servo.class, "artifactstopper");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        distance1 = hardwareMap.get(DistanceSensor.class, "distance1");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        initialSetup();
        shootPower = 0.8;
        maxDrivePower = 1;
        mode = 1;
        waitForStart();
        pickMode();
    }
    public void initialSetup() {
        driveLeft.setDirection(DcMotor.Direction.REVERSE);
        isShooting = false;
        artifactstopper.setPosition(0.2);
    }
    public void pickMode() {
        if (mode == 0) {
            gamepadDrive();
        }
        else if(mode == 1) {
            autoDrive();
        }
    }
    public void gamepadDrive() {
        while(opModeIsActive()) {
            horizontalInput = gamepad1.right_stick_x;
            verticalInput = gamepad1.left_stick_y;
            processDriveInputs();
            if(gamepad1.a && !isShooting) {
                shoot();
            }
        }
    }
    public void processDriveInputs() {
        driveLeft.setPower(verticalInput * maxDrivePower + horizontalInput * verticalInput);
        driveRight.setPower(verticalInput * maxDrivePower - horizontalInput * verticalInput);
    }
    public void shoot() {
        driveLeft.setPower(0);
        driveRight.setPower(0);
        isShooting = true;
        artifactstopper.setPosition(0);
        shootwheel.setPower(shootPower);
        sleep(250);
        artifactstopper.setPosition(0.2);
        sleep(200);
        shootwheel.setPower(0);
        sleep(1500);
        isShooting = false;
    }
    public void autoDrive() {
        driveToGoal();
        shootThreeArtifacts();
        driveToLoadingSpotAndBack();
        shootThreeArtifacts();
    }
    public void driveToGoal() {
        driveLeft.setPower(1);
        driveRight.setPower(1);
        sleep(1200);
        driveLeft.setPower(-1);
        driveRight.setPower(-1);
        sleep(230);
        driveLeft.setPower(0);
        driveRight.setPower(0);
        sleep(500);
    }
    public void shootThreeArtifacts() {
        nArtifacts = 3;
        while(opModeIsActive() && nArtifacts > 0) {
            if(!isShooting) {
                shoot();
                nArtifacts -= 1;
            }
        }
    }
    public void driveToLoadingSpotAndBack() {
        driveLeft.setPower(-1);
        driveRight.setPower(-1);
        sleep(1500);
        driveLeft.setPower(0);
        driveRight.setPower(0);
        sleep(10000);
        driveLeft.setPower(1);
        driveRight.setPower(1);
        sleep(1500);
        driveLeft.setPower(0);
        driveRight.setPower(0);
        sleep(500);
    }
}
