package org.firstinspires.ftc.teamcode.Strategy1_AprilTag;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous(name="AutoDrive1_AprilTag", group="STEAMachines_DECODE")
public class AutoDrive1_Apriltag extends LinearOpMode {
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor shootMotors;

    public void runOpMode() {
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        shootMotors = hardwareMap.get(DcMotor.class, "shootWheel");
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        boolean isShooting = false;
        initializeAprilTag();
        waitForStart();
        autoDrive();
    }
    public void initializeAprilTag() {

    }
    public void autoDrive() {
        leftDrive.setPower(0.5);
        rightDrive.setPower(0.4);
        sleep(2500);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(3000);
        leftDrive.setPower(-0.5);
        rightDrive.setPower(0.5);
        sleep(700);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(250);
        leftDrive.setPower(0.5);
        rightDrive.setPower(0.5);
        sleep(750);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        sleep(3000);
        leftDrive.setPower(-0.5);
        rightDrive.setPower(-0.5);
        sleep(2250);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
}
