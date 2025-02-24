package org.firstinspires.ftc.teamcode.Helpers;

import com.qualcomm.robotcore.hardware.Servo;

// TODO: started adapting to into the deep, still mostly from center stage





//TODO: EXTENDO ARM SERVO IS IN CONTROL HUB PORT 4

public class ClawController {

    final double clawOpenPos = 0.25f;
    final double clawClosedPos = 0.05f;


//    public enum ClawPosition {
//        LEVEL,
//        SCORING
//    }

//    public ClawPosition currentClawPos = ClawPosition.SCORING;
//    private final float clawLevelPos = 0.65f;
//    private final float clawScoringPos = 0f;

    Servo clawServo;
    public boolean clawIsOpen = false;
//    Toggler clawArmToggler = new Toggler();
    Toggler clawPosToggler = new Toggler();
//    Toggler leftClawPosToggler = new Toggler();

    public ClawController(Servo clawServo) {
        this.clawServo = clawServo;
    }


//    public void setClawScoringPos() {
//        clawServo.setPosition(clawScoringPos);
//    }
//
//    public void setClawLevelPos() {
//        clawServo.setPosition(clawLevelPos);
//    }

//    public void toggleClawPosition(boolean button_pressed) {
//        if(clawArmToggler.toggle(button_pressed)) {
//            switch (currentClawPos) {
//                case LEVEL:
//                    clawServo.setPosition(clawScoringPos);
//                    currentClawPos = ClawPosition.SCORING;
//                    break;
//                case SCORING:
//                    clawServo.setPosition(clawLevelPos);
//                    currentClawPos = ClawPosition.LEVEL;
//                    break;
//            }
//        }
//    }

    private boolean toggleClaw() {
        if(clawIsOpen) {
            clawServo.setPosition(clawClosedPos);
        } else {
            clawServo.setPosition(clawOpenPos);
        }

        clawIsOpen = !clawIsOpen;

        return(clawIsOpen);
    }

    public void checkAndToggleClaw(boolean rightButton) {

        if(clawPosToggler.toggle(rightButton)) {
            toggleClaw();
        }

    }

    public void openClaw() {
        clawServo.setPosition(clawOpenPos);

        clawIsOpen = true;
    }

    public void closeClaw() {
        clawServo.setPosition(clawClosedPos);

        clawIsOpen = false;
    }

}
