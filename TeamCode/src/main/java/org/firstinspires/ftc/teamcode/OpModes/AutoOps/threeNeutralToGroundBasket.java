package org.firstinspires.ftc.teamcode.OpModes.AutoOps;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Helpers.DriveController;
import org.firstinspires.ftc.teamcode.Helpers.Toggler;

@Autonomous
public class threeNeutralToGroundBasket extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backLeft, backRight, slideLeft, slideRight;
    DriveController driveController;
    CRServo armServo;
    Servo clawServo;
    boolean clawIsOpen = false;


    double driveSpeed = .25;
    double rotateSpeed = .5;

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

        //coordinates: bottom left at basket = (0,0), top right at opposing basket = (6,6)
        //START POSITION: (1+1/2, 3/8)


        driveController.forwards(2+1/8f, driveSpeed);
        driveController.left(3/8f, driveSpeed);
        driveController.backwards(2f, driveSpeed);

        driveController.forwards(2f, driveSpeed);
        driveController.left(1/2f, driveSpeed);
        driveController.backwards(2f, driveSpeed);

        driveController.forwards(2f, driveSpeed);
        driveController.left(1/2f, driveSpeed);
        driveController.backwards(2f, driveSpeed);




    }
}
