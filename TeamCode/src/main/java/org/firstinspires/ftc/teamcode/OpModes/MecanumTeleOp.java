package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Helpers.DriveController;


@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backLeft, backRight;
    DriveController driveController;
    
    void initialize() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        driveController = new DriveController(frontLeft, backLeft, frontRight, backRight);
        driveController.init();

    }


    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while (!isStopRequested()) {

            driveController.drive(gamepad1.left_stick_x, gamepad1.right_stick_y, gamepad1.right_stick_x, 1);

        }

    }
}
