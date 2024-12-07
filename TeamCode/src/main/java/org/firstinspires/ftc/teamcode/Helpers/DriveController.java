package org.firstinspires.ftc.teamcode.Helpers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DriveController {

    DcMotorEx frontLeft, backLeft, frontRight, backRight;


    double tilesToPos = 1025; //
    double degreesToPos = 8.3; //


    public DriveController(DcMotorEx frontLeft, DcMotorEx backLeft, DcMotorEx frontRight, DcMotorEx backRight) {
        this.frontLeft = frontLeft;
        this.backLeft = backLeft;
        this.frontRight = frontRight;
        this.backRight = backRight;
    }

    public void init() {

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRight.setTargetPositionTolerance(25);
        frontLeft.setTargetPositionTolerance(25);
        backRight.setTargetPositionTolerance(25);
        backLeft.setTargetPositionTolerance(25);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    /**
     *
     * @return returns false if any motor is busy, returns true otherwise.
     */

    public void waitForMotors() {
        while(!(!frontLeft.isBusy() && !backLeft.isBusy() && !frontRight.isBusy() && !backRight.isBusy())) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException("Uncaught", e);
            }
        }
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     *
     * @param leftX the x position of the left stick.
     * @param leftY the y position of the left stick.
     * @param rightX the x position of the right stick.
     * @param speedFactor scales the speed of the robot. Should be a double from 0 to 1.
     */

    public void drive(double leftX, double leftY, double rightX, double speedFactor) {

        speedFactor = Math.min(speedFactor, 1);
        speedFactor = Math.max(speedFactor, 0);

        double r = Math.hypot(leftX, leftY);
        double robotAngle = Math.atan2(-leftY, leftX) - Math.PI / 4;

        final double v1;
        final double v2;
        final double v3;
        final double v4;
//        if(speedFactor + Math.abs(rightX) > 1) {
//            v1 = (r * Math.cos(robotAngle) + rightX) / speedFactor + Math.abs(rightX);
//            v2 = (r * Math.sin(robotAngle) - rightX) / speedFactor + Math.abs(rightX);
//            v3 = (r * Math.sin(robotAngle) + rightX) / speedFactor + Math.abs(rightX);
//            v4 = (r * Math.cos(robotAngle) - rightX) / speedFactor + Math.abs(rightX);
//        } else {
            v1 = r * Math.cos(robotAngle) + rightX;
            v2 = r * Math.sin(robotAngle) - rightX;
            v3 = r * Math.sin(robotAngle) + rightX;
            v4 = r * Math.cos(robotAngle) - rightX;
//        }

        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setPower(v1 * speedFactor);
        frontRight.setPower(v2 * speedFactor);
        backLeft.setPower(v3 * speedFactor);
        backRight.setPower(v4 * speedFactor);
    }

    void setMode(DcMotor.RunMode mode) {
        frontLeft.setMode(mode);
        backLeft.setMode(mode);
        frontRight.setMode(mode);
        backRight.setMode(mode);
    }



    public void forwards(double tiles, double power) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) Math.round(tiles * tilesToPos));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) Math.round(tiles * tilesToPos));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) Math.round(tiles * tilesToPos));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) Math.round(tiles * tilesToPos));
//        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) Math.round(tiles * tilesToPosFL));
//        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) Math.round(tiles * tilesToPosFR));
//        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) Math.round(tiles * tilesToPosBL));
//        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) Math.round(tiles * tilesToPosBR));

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForMotors();
    }


    public void backwards(double tiles, double power) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - (int) Math.round(tiles * tilesToPos));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() - (int) Math.round(tiles * tilesToPos));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() - (int) Math.round(tiles * tilesToPos));
        backRight.setTargetPosition(backRight.getCurrentPosition() - (int) Math.round(tiles * tilesToPos));
//        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - (int) Math.round(tiles * tilesToPosFL));
//        frontRight.setTargetPosition(frontRight.getCurrentPosition() - (int) Math.round(tiles * tilesToPosFR));
//        backLeft.setTargetPosition(backLeft.getCurrentPosition() - (int) Math.round(tiles * tilesToPosBL));
//        backRight.setTargetPosition(backRight.getCurrentPosition() - (int) Math.round(tiles * tilesToPosBR));

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForMotors();
    }


    public void left(double tiles, double power) {
        frontLeft.setTargetPosition((int) Math.round(frontLeft.getCurrentPosition() - (int) Math.round(tiles * tilesToPos) * 1.15));
        frontRight.setTargetPosition((int) Math.round(frontRight.getCurrentPosition() + (int) Math.round(tiles * tilesToPos) * 1.15));
        backLeft.setTargetPosition((int) Math.round(backLeft.getCurrentPosition() + (int) Math.round(tiles * tilesToPos) * 1.15));
        backRight.setTargetPosition((int) Math.round(backRight.getCurrentPosition() - (int) Math.round(tiles * tilesToPos) * 1.15));
//        frontLeft.setTargetPosition((int) Math.round(frontLeft.getCurrentPosition() - (int) Math.round(tiles * tilesToPosFL) * 1.15));
//        frontRight.setTargetPosition((int) Math.round(frontRight.getCurrentPosition() + (int) Math.round(tiles * tilesToPosFR) * 1.15));
//        backLeft.setTargetPosition((int) Math.round(backLeft.getCurrentPosition() + (int) Math.round(tiles * tilesToPosBL) * 1.15));
//        backRight.setTargetPosition((int) Math.round(backRight.getCurrentPosition() - (int) Math.round(tiles * tilesToPosBR) * 1.15));
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForMotors();
    }


    public void right(double tiles, double power) {
        frontLeft.setTargetPosition((int) Math.round(frontLeft.getCurrentPosition() + (int) Math.round(tiles * tilesToPos) * 1.15));
        frontRight.setTargetPosition((int) Math.round(frontRight.getCurrentPosition() - (int) Math.round(tiles * tilesToPos) * 1.15));
        backLeft.setTargetPosition((int) Math.round(backLeft.getCurrentPosition() - (int) Math.round(tiles * tilesToPos) * 1.15));
        backRight.setTargetPosition((int) Math.round(backRight.getCurrentPosition() + (int) Math.round(tiles * tilesToPos) * 1.15));
//        frontLeft.setTargetPosition((int) Math.round(frontLeft.getCurrentPosition() + (int) Math.round(tiles * tilesToPosFL) * 1.15));
//        frontRight.setTargetPosition((int) Math.round(frontRight.getCurrentPosition() - (int) Math.round(tiles * tilesToPosFR) * 1.15));
//        backLeft.setTargetPosition((int) Math.round(backLeft.getCurrentPosition() - (int) Math.round(tiles * tilesToPosBL) * 1.15));
//        backRight.setTargetPosition((int) Math.round(backRight.getCurrentPosition() + (int) Math.round(tiles * tilesToPosBR) * 1.15));
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForMotors();

    }



    public void turn(double leftPower, double rightPower) {
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        backRight.setPower(rightPower);
    }


    public void turnRight(double degrees, double power) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) Math.round(degrees * degreesToPos));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() - (int) Math.round(degrees * degreesToPos));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) Math.round(degrees * degreesToPos));
        backRight.setTargetPosition(backRight.getCurrentPosition() - (int) Math.round(degrees * degreesToPos));
//        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + (int) Math.round(degrees * degreesToPosFL));
//        frontRight.setTargetPosition(frontRight.getCurrentPosition() - (int) Math.round(degrees * degreesToPosFR));
//        backLeft.setTargetPosition(backLeft.getCurrentPosition() + (int) Math.round(degrees * degreesToPosBL));
//        backRight.setTargetPosition(backRight.getCurrentPosition() - (int) Math.round(degrees * degreesToPosBR));
        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);
        waitForMotors();
    }


    public void turnLeft(double degrees, double power) {
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - (int) Math.round(degrees * degreesToPos));
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) Math.round(degrees * degreesToPos));
        backLeft.setTargetPosition(backLeft.getCurrentPosition() - (int) Math.round(degrees * degreesToPos));
        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) Math.round(degrees * degreesToPos));
//        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - (int) Math.round(degrees * degreesToPosFL));
//        frontRight.setTargetPosition(frontRight.getCurrentPosition() + (int) Math.round(degrees * degreesToPosFR));
//        backLeft.setTargetPosition(backLeft.getCurrentPosition() - (int) Math.round(degrees * degreesToPosBL));
//        backRight.setTargetPosition(backRight.getCurrentPosition() + (int) Math.round(degrees * degreesToPosBR));
        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);
        waitForMotors();
    }




}