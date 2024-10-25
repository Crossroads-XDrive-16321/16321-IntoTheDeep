package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Helpers.DriveController;


@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backLeft, backRight, slideLeft, slideRight;
    DriveController driveController;

    int slideLeftInitPos;
    int slideRightInitPos;
    
    void initialize() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        slideLeft = hardwareMap.get(DcMotorEx.class, "slideLeft");
        slideRight = hardwareMap.get(DcMotorEx.class, "slideRight");

        slideLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        slideLeftInitPos = slideLeft.getCurrentPosition();
        slideRightInitPos = slideRight.getCurrentPosition();

        driveController = new DriveController(frontLeft, backLeft, frontRight, backRight);
        driveController.init();

    }


    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while (!isStopRequested()) {

            driveController.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, 1);

            if (gamepad2.right_trigger >= 0.3) {
                slideLeft.setPower(0.8*gamepad2.right_trigger);
                slideRight.setPower(0.8*gamepad2.right_trigger);
            } else if (gamepad2.left_trigger >= 0.3) {
                slideLeft.setPower(-0.8*gamepad2.left_trigger);
                slideRight.setPower(-0.8*gamepad2.left_trigger);
            } else {
                slideLeft.setPower(0);
                slideRight.setPower(0);
            }

            telemetry.addData("SlideLeftInitPos:", slideLeftInitPos);
            telemetry.addData("SlideLeftCurrentPos:", slideLeft.getCurrentPosition());
            telemetry.addData("SlideLeftDiff:", slideLeft.getCurrentPosition()-slideLeftInitPos);

            telemetry.addLine("");

            telemetry.addData("SlideRightInitPos:", slideRightInitPos);
            telemetry.addData("SlideRightCurrentPos:", slideRight.getCurrentPosition());
            telemetry.addData("SlideRightDiff:", slideRight.getCurrentPosition()-slideRightInitPos);

            telemetry.update();

        }

    }
}
