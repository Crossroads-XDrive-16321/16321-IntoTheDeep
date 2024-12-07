package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Helpers.DriveController;
import org.firstinspires.ftc.teamcode.Helpers.Toggler;


@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backLeft, backRight, slideLeft, slideRight;
    DriveController driveController;
    CRServo armServo;
    Servo clawServo;
    boolean clawIsOpen = true;
    double speedFactor = 1;

    //.37 closed, .55 open

    Toggler toggle1 = new Toggler();
    Toggler toggle2 = new Toggler();
    Toggler toggle3 = new Toggler();

    int slideLeftInitPos; // goes up 3170
    int slideRightInitPos;
    
    void initialize() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        slideLeft = hardwareMap.get(DcMotorEx.class, "slideLeft");
        slideRight = hardwareMap.get(DcMotorEx.class, "slideRight");

        armServo = hardwareMap.get(CRServo.class, "armServo");

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

            if(slideRight.getCurrentPosition()-slideRightInitPos > 1000) {
                speedFactor = 0.6;
            } else {
                speedFactor = 1.0;
            }

            driveController.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, speedFactor*(1-(gamepad1.right_trigger*0.7)));



            if (gamepad2.right_trigger >= 0.3) {
//                if (slideRight.getCurrentPosition() - slideRightInitPos <= 3165) {
                    slideLeft.setPower(-gamepad2.right_trigger);
                    slideRight.setPower(-gamepad2.right_trigger);
//                }
            } else if (gamepad2.left_trigger >= 0.3) {
//                if (slideRight.getCurrentPosition() - slideRightInitPos >= 5) {
                    slideLeft.setPower(gamepad2.left_trigger);
                    slideRight.setPower(gamepad2.left_trigger);
//                }
            } else {
                slideLeft.setPower(-0.1);
                slideRight.setPower(-0.1);
            }


            armServo.setPower(-gamepad2.right_stick_y+0.05);


            if (toggle1.toggle(gamepad2.a)) {
                if (clawIsOpen) {
                    clawServo.setPosition(0.24);
                } else {
                    clawServo.setPosition(0.37);
                }
                clawIsOpen = !clawIsOpen;
            }


            telemetry.addData("SlideLeftInitPos:", slideLeftInitPos);
            telemetry.addData("SlideLeftCurrentPos:", slideLeft.getCurrentPosition());
            telemetry.addData("SlideLeftDiff:", slideLeft.getCurrentPosition()-slideLeftInitPos);

            telemetry.addLine("");

            telemetry.addData("SlideRightInitPos:", slideRightInitPos);
            telemetry.addData("SlideRightCurrentPos:", slideRight.getCurrentPosition());
            telemetry.addData("SlideRightDiff:", slideRight.getCurrentPosition()-slideRightInitPos);

            telemetry.addLine("");

//            telemetry.addData("Arm Servo:", armServo.getPosition());
            telemetry.addData("Claw Servo:", clawServo.getPosition());

            telemetry.addData("frontLeft", frontLeft.getCurrentPosition());
            telemetry.addData("frontRight", frontRight.getCurrentPosition());
            telemetry.addData("backLeft", backLeft.getCurrentPosition());
            telemetry.addData("backRight", backRight.getCurrentPosition());

            telemetry.update();

        }

    }
}
