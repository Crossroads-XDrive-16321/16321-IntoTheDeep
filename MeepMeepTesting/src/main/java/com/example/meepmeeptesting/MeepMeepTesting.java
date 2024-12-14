package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;

import org.jetbrains.annotations.NotNull;
import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.core.colorscheme.scheme.*;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity redSpecimen = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(12, -60, Math.toRadians(90f)))


                        .lineTo(new Vector2d(0, -32)) //WHILE ROTATING ARM INTO SCORING POS
                        //-----
                        //SCORE SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(-45))
                        .splineToConstantHeading(new Vector2d(28, -36), Math.toRadians(0f))

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
                        .splineToConstantHeading(new Vector2d(36, -60), Math.toRadians(-90))

                        //PARK


                        .build());
        RoadRunnerBotEntity blueSpecimen = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-12, 60, Math.toRadians(-90f)))


                        .lineTo(new Vector2d(0, 32)) //WHILE ROTATING ARM INTO SCORING POS
                        //-----
                        //SCORE SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(135f))
                        .splineToConstantHeading(new Vector2d(-28, 36), Math.toRadians(180f))

                        .splineToConstantHeading(new Vector2d(-36, 12), Math.toRadians(-90f))
                        .splineToConstantHeading(new Vector2d(-46, 12), Math.toRadians(90f))
                        .splineToConstantHeading(new Vector2d(-46, 48), Math.toRadians(90f))
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-46, 18), Math.toRadians(-90f))
                        .splineToConstantHeading(new Vector2d(-54, 12), Math.toRadians(90f))
                        .splineToConstantHeading(new Vector2d(-54, 48), Math.toRadians(90f))
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-54, 18), Math.toRadians(-90f))
                        .splineToConstantHeading(new Vector2d(-61, 12), Math.toRadians(90f))
                        .splineToConstantHeading(new Vector2d(-61, 48), Math.toRadians(90f))
                        //-----
                        .setTangent(Math.toRadians(-45f))
                        .splineToConstantHeading(new Vector2d(-36, 60), Math.toRadians(90))
                        //-----
                        //PICKUP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-4, 32), Math.toRadians(-90))
                        //-----
                        //DROP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(-36, 60), Math.toRadians(90))
                        //-----
                        //PICKUP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-4, 32), Math.toRadians(-90))
                        //-----
                        //DROP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(-36, 60), Math.toRadians(90))
                        //-----
                        //PICKUP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-4, 32), Math.toRadians(-90))
                        //-----
                        //DROP SPECIMEN
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(-36, 60), Math.toRadians(90))

                        //PARK


                        .build());

        RoadRunnerBotEntity redBasket = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setColorScheme(new ColorSchemeRedLight())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-12, -60, Math.toRadians(90f)))

                        .lineTo(new Vector2d(-8, -32)) //WHILE ROTATING ARM INTO SCORING POS
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-48,-36), Math.toRadians(90))
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(-57,-57,Math.toRadians(45)), Math.toRadians(-90))
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-58,-36, Math.toRadians(90)), Math.toRadians(90))
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(-57,-57,Math.toRadians(45)), Math.toRadians(-90))
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-61, -12, Math.toRadians(90)), Math.toRadians(180f))
                        .setTangent(Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(-61, -48), Math.toRadians(-90f))
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(-30, 0, Math.toRadians(0)), Math.toRadians(0))
                        //-----
                        //SLOW DOWN WHILE APPROACHING TO NOT BREAK ARM HAHAHA
                        //-----
                        .forward(6f)

                        .build());

        RoadRunnerBotEntity blueBasket = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setColorScheme(new ColorSchemeBlueLight())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(-90f)))

                        .lineTo(new Vector2d(8, 32)) //WHILE ROTATING ARM INTO SCORING POS
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(48,36), Math.toRadians(-90))
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(57,57,Math.toRadians(-135)), Math.toRadians(90))
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(58,36, Math.toRadians(-90)), Math.toRadians(-90))
                        //-----
                        .setTangent(Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(57,57,Math.toRadians(-135)), Math.toRadians(90))
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(61, 12, Math.toRadians(-90)), Math.toRadians(0f))
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(61, 48), Math.toRadians(90f))
                        //-----
                        .setTangent(Math.toRadians(-90))
                        .splineToLinearHeading(new Pose2d(30, 0, Math.toRadians(180)), Math.toRadians(180))
                        //-----
                        //SLOW DOWN WHILE APPROACHING TO NOT BREAK ARM HAHAHA
                        //-----
                        .forward(6f)

                        .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(redSpecimen)
                .addEntity(blueSpecimen)
//                .addEntity(redBasket)
//                .addEntity(blueBasket)
                .start();
    }
}