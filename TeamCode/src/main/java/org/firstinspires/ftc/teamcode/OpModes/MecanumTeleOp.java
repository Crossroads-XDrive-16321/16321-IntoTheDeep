package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Helpers.DriveController;
import org.firstinspires.ftc.teamcode.Helpers.SlideController;
import org.firstinspires.ftc.teamcode.Helpers.Toggler;
import org.firstinspires.ftc.teamcode.Helpers.ClawController;

//TODO: ports 0 and 3 on REV hubs are more accurate at high speeds and should be used for the parallel dead wheels.


@TeleOp
public class MecanumTeleOp extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backLeft, backRight, slideLeft, slideRight;
    DriveController driveController;
    SlideController slideController;
    ClawController clawController;
//    CRServo armServo;
    Servo clawServo, armServo; // Armservo positions: 0.45 scoring, 0.18 ground, __ from wall

    Toggler toggler1 = new Toggler();
    Toggler toggler2 = new Toggler();

    double speedFactor = 1;
    
    void initialize() {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        slideLeft = hardwareMap.get(DcMotorEx.class, "slideLeft");
        slideRight = hardwareMap.get(DcMotorEx.class, "slideRight");

//        armServo = hardwareMap.get(CRServo.class, "armServo");
        armServo = hardwareMap.get(Servo.class, "armServo");

        clawServo = hardwareMap.get(Servo.class, "clawServo");

        slideLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        slideController = new SlideController(slideLeft, slideRight);
        slideController.initialize();

        driveController = new DriveController(frontLeft, backLeft, frontRight, backRight);
        clawController = new ClawController(clawServo);
        driveController.init();

    }


    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        while (!isStopRequested()) {

            speedFactor = 1.0f;

            slideController.setPowers(gamepad2.left_trigger, gamepad2.right_trigger);

            driveController.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, speedFactor*(1-(gamepad1.right_trigger*0.7)));

            if(toggler1.toggle(gamepad2.b)) {
                armServo.setPosition(0.45);
            }
            if (toggler2.toggle(gamepad2.x)) {
                armServo.setPosition(0.19);
            }

            clawController.checkAndToggleClaw(gamepad2.a);


//            telemetry.addData("SlideLeftInitPos:", slideLeftInitPos);
            telemetry.addData("SlideLeftCurrentPos:", slideLeft.getCurrentPosition());
//            telemetry.addData("SlideLeftDiff:", slideLeft.getCurrentPosition()-slideLeftInitPos);

            telemetry.addLine("");

//            telemetry.addData("SlideRightInitPos:", slideRightInitPos);
            telemetry.addData("SlideRightCurrentPos:", slideRight.getCurrentPosition());
//            telemetry.addData("SlideRightDiff:", slideRight.getCurrentPosition()-slideRightInitPos);

            telemetry.addLine("");

            telemetry.addData("Arm Servo:", armServo.getPosition());
            telemetry.addData("Claw Servo:", clawServo.getPosition());

            telemetry.addData("frontLeft", frontLeft.getCurrentPosition());
            telemetry.addData("frontRight", frontRight.getCurrentPosition());
            telemetry.addData("backLeft", backLeft.getCurrentPosition());
            telemetry.addData("backRight", backRight.getCurrentPosition());

            telemetry.update();

        }

    }
}
