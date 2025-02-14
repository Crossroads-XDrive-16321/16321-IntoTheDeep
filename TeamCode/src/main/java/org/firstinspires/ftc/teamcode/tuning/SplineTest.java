package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TankDrive;

public final class SplineTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(12, -60, Math.toRadians(-90));
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

            waitForStart();
            Actions.runBlocking(
                drive.actionBuilder(beginPose)
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(0, -32), Math.toRadians(90)) //WHILE ROTATING ARM INTO SCORING POS
                        //-----
                        //SCORE SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(-45))
                        .splineToConstantHeading(new Vector2d(28, -38), Math.toRadians(0f))

                        .splineToConstantHeading(new Vector2d(36, -12), Math.toRadians(90f))
                        .splineToConstantHeading(new Vector2d(46, -12), Math.toRadians(-90f))
                        .splineToConstantHeading(new Vector2d(46, -48), Math.toRadians(-90f))
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(46, -18), Math.toRadians(90f))
                        .splineToConstantHeading(new Vector2d(54, -12), Math.toRadians(-90f))
                        .splineToConstantHeading(new Vector2d(54, -48), Math.toRadians(-90f))
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(54, -18), Math.toRadians(90f))
                        .splineToConstantHeading(new Vector2d(61, -12), Math.toRadians(-90f))
                        .splineToConstantHeading(new Vector2d(61, -48), Math.toRadians(-90f))
                        //-----
                        .setTangent(Math.toRadians(135))
                        .splineToConstantHeading(new Vector2d(36, -60), Math.toRadians(-90))
                        //-----
                        //PICKUP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(4, -32), Math.toRadians(90))
                        //-----
                        //DROP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(36, -60), Math.toRadians(-90))
                        //-----
                        //PICKUP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(4, -32), Math.toRadians(90))
                        //-----
                        //DROP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(36, -60), Math.toRadians(-90))
                        //-----
                        //PICKUP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(4, -32), Math.toRadians(90))
                        //-----
                        //DROP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(50, -60), Math.toRadians(0))

                        .waitSeconds(8.06f)//PARK


                        .build());
        } else if (TuningOpModes.DRIVE_CLASS.equals(TankDrive.class)) {
            TankDrive drive = new TankDrive(hardwareMap, beginPose);

            waitForStart();

            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .splineTo(new Vector2d(30, 30), Math.PI / 2)
                            .splineTo(new Vector2d(0, 60), Math.PI)
                            .build());
        } else {
            throw new RuntimeException();
        }
    }
}
