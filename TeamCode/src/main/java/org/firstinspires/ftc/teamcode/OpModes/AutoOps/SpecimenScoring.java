package org.firstinspires.ftc.teamcode.OpModes.AutoOps;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Helpers.ClawController;
import org.firstinspires.ftc.teamcode.Helpers.DriveController;
import org.firstinspires.ftc.teamcode.Helpers.SlideController;
import org.firstinspires.ftc.teamcode.Helpers.Toggler;

@Autonomous
public class SpecimenScoring extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backLeft, backRight, slideLeft, slideRight;
    DriveController driveController;
//    CRServo armServo;
    Servo clawServo, armServo;
    SlideController slideController;
    ClawController clawController;
    boolean clawIsOpen = false;


    double driveSpeed = .6;
    double slideSpeed = 0.7;
    double rotateSpeed = .5;

    int slideLeftInitPos; // goes up 3170
    int slideRightInitPos;


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

        clawController.closeClaw();
        armServo.setPosition(0.45);
        driveController.backwards(0.5, driveSpeed);
        driveController.turnRight(180, rotateSpeed);
        driveController.left(0.75, driveSpeed);
        slideController.setPowers(0f, slideSpeed);
        sleep(750);
        slideController.setPowers(0f, 0f);
        driveController.forwards(0.55, driveSpeed/2);
        slideController.setPowers(slideSpeed, 0f);
        sleep(750);
        slideController.setPowers(0f, 0f);
        clawController.openClaw();
        driveController.backwards(1, driveSpeed);
        armServo.setPosition(0.19);
        driveController.right(2, driveSpeed);

    }
}
