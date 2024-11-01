package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Helpers.DriveController;
import org.firstinspires.ftc.teamcode.Helpers.Toggler;


@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backLeft, backRight, slideLeft, slideRight;
    DriveController driveController;
    Servo armLeft, armRight, clawServo;

    Toggler toggle1 = new Toggler();
    Toggler toggle2 = new Toggler();
    Toggler toggle3 = new Toggler();
    Toggler toggle4 = new Toggler();
    Toggler toggle5 = new Toggler();
    Toggler toggle6 = new Toggler();

    int slideLeftInitPos;
    int slideRightInitPos;
    
    void initialize() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        slideLeft = hardwareMap.get(DcMotorEx.class, "slideLeft");
        slideRight = hardwareMap.get(DcMotorEx.class, "slideRight");

        armLeft = hardwareMap.get(Servo.class, "armLeft");
        armRight = hardwareMap.get(Servo.class, "armRight");
        clawServo = hardwareMap.get(Servo.class, "clawServo");

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
                slideLeft.setPower(gamepad2.right_trigger);
                slideRight.setPower(0.6*gamepad2.right_trigger);
            } else if (gamepad2.left_trigger >= 0.3) {
                slideLeft.setPower(-gamepad2.left_trigger);
                slideRight.setPower(-0.6*gamepad2.left_trigger);
            } else {
                slideLeft.setPower(0);
                slideRight.setPower(0);
            }

            if (toggle1.toggle(gamepad2.a)) {
                armRight.setPosition(armRight.getPosition() + 0.1);
                armLeft.setPosition(armLeft.getPosition() - 0.1);
            }

            if (toggle2.toggle(gamepad2.b)) {
                armRight.setPosition(armRight.getPosition() - 0.1);
                armLeft.setPosition(armLeft.getPosition() + 0.1);
            }

            if (toggle5.toggle(gamepad2.left_bumper)) {
                clawServo.setPosition(clawServo.getPosition() + 0.1);
            }

            if (toggle6.toggle(gamepad2.right_bumper)) {
                clawServo.setPosition(clawServo.getPosition() - 0.1);
            }



            telemetry.addData("SlideLeftInitPos:", slideLeftInitPos);
            telemetry.addData("SlideLeftCurrentPos:", slideLeft.getCurrentPosition());
            telemetry.addData("SlideLeftDiff:", slideLeft.getCurrentPosition()-slideLeftInitPos);

            telemetry.addLine("");

            telemetry.addData("SlideRightInitPos:", slideRightInitPos);
            telemetry.addData("SlideRightCurrentPos:", slideRight.getCurrentPosition());
            telemetry.addData("SlideRightDiff:", slideRight.getCurrentPosition()-slideRightInitPos);

            telemetry.addLine("");

            telemetry.addData("Arm Left:", armLeft.getPosition());
            telemetry.addData("Arm Right:", armRight.getPosition());
            telemetry.addData("Claw Servo:", clawServo.getPosition());

            telemetry.update();

        }

    }
}
