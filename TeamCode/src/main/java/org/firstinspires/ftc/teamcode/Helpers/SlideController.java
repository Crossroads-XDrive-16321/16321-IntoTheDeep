package org.firstinspires.ftc.teamcode.Helpers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class SlideController {

    DcMotorEx slideLeft, slideRight;
    double slideLeftInitPos, slideRightInitPos;
    double speedFactor = 1;

    public SlideController(DcMotorEx slideLeft, DcMotorEx slideRight) {
        this.slideLeft = slideLeft;
        this.slideRight = slideRight;
    }

    public void initialize() {
        slideLeftInitPos = slideLeft.getCurrentPosition();
        slideRightInitPos = slideRight.getCurrentPosition();
    }

    public double setPowers(double leftTrigger, double rightTrigger) {

        if(slideRight.getCurrentPosition()-slideRightInitPos > 1000) {
            speedFactor = (1-(4000f/(slideRight.getCurrentPosition()-slideRightInitPos)));
        } else {
            speedFactor = 1.0;
        }

        if (rightTrigger >= 0.3) {
            slideLeft.setPower(-rightTrigger);
            slideRight.setPower(-rightTrigger);
        } else if (leftTrigger >= 0.3) {
            slideLeft.setPower(leftTrigger);
            slideRight.setPower(leftTrigger);
        } else {
            slideLeft.setPower(-0.1);
            slideRight.setPower(-0.1);
        }

        return speedFactor;

    }

    public void setSlidePos(double height, double power) {
        slideRight.setTargetPosition((int) (slideRightInitPos + height));
        slideLeft.setTargetPosition((int) (slideLeftInitPos + height));
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setPower(power);
        slideLeft.setPower(power);
    }

}
