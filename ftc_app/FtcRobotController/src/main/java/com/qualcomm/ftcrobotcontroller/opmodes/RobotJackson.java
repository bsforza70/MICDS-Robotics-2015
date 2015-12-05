package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotJackson extends OpMode{
    //Initializing the motors as DcMotors
    DcMotor grapplingPin;
    DcMotor rightMotor;
    DcMotor grapplingWinch;
    DcMotor leftMotor;
    DcMotor bucketArm;

    Servo servoMotorGateRight;
    Servo servoMotorGateLeft;
    Servo rescuerRight;
    Servo rescuerLeft;

    LightSensor sensorLight;
    LightSensor allianceDetector;

    @Override
    public void init() {
        grapplingPin = hardwareMap.dcMotor.get("grapplingPin");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        grapplingWinch = hardwareMap.dcMotor.get("grapplingWinch");
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        bucketArm = hardwareMap.dcMotor.get("arm");

        servoMotorGateRight = hardwareMap.servo.get("servogr");
        servoMotorGateLeft = hardwareMap.servo.get("servogl");
        rescuerRight = hardwareMap.servo.get("rightres");
        rescuerLeft = hardwareMap.servo.get("leftres");

        sensorLight = hardwareMap.lightSensor.get("sensor1");
        allianceDetector = hardwareMap.lightSensor.get("sensor2");

        //reverses the right motor
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        leftMotor.setPower(leftY);
        rightMotor.setPower(rightY);

        if(gamepad1.a) {
            grapplingPin.setPower(1.0);
        }

        if(gamepad1.b){
            leftMotor.setPower(1.0);
            rightMotor.setPower(1.0);
            grapplingPin.setPower(1.0);
            grapplingWinch.setPower(1.0);
        }

        if(gamepad1.right_trigger > 0.05) {
            grapplingWinch.setPower(1);
        }

        if(gamepad1.left_trigger > 0.05) {
            grapplingWinch.setPower(-1);
        }

        if (gamepad2.a) {
            if(servoMotorGateLeft.getPosition() == 0){
                servoMotorGateLeft.setPosition(servoMotorGateLeft.getPosition() + 0.05);
            } else {
                telemetry.addData("Left Gate", "STOP - Nicole Truman-Shaw");
            }
        }

        if (gamepad2.b) {
            if(servoMotorGateLeft.getPosition() == 0){
                telemetry.addData("Left Gate", "STOP - Nicole Truman-Shaw");
            } else {
                servoMotorGateLeft.setPosition(servoMotorGateLeft.getPosition() - 0.05);
            }
        }

        if (gamepad2.x) {
            if(servoMotorGateRight.getPosition() == 0){
                servoMotorGateRight.setPosition(servoMotorGateRight.getPosition() + 0.05);
            } else {
                telemetry.addData("Right Gate", "STOP - Nicole Truman-Shaw");
            }
        }

        if (gamepad2.y) {
            if(servoMotorGateRight.getPosition() == 0){
                telemetry.addData("Right Gate", "STOP - Nicole Truman-Shaw");
            } else {
                servoMotorGateRight.setPosition(servoMotorGateRight.getPosition() - 0.05);
            }
        }

        if (gamepad2.left_trigger > 0.05){
            if(rescuerLeft.getPosition() == 0){
                rescuerLeft.setPosition(rescuerLeft.getPosition() + 0.05);
            } else {
                telemetry.addData("Left Rescuer", "STOP - Nicole Truman-Shaw");
            }
        }

        if (gamepad2.left_bumper){
            if(rescuerLeft.getPosition() == 0){
                telemetry.addData("Left Rescuer", "STOP - Nicole Truman-Shaw");
            } else {
                rescuerLeft.setPosition(rescuerLeft.getPosition() - 0.05);
            }
        }

        if (gamepad2.right_trigger > 0.05){
            if(rescuerRight.getPosition() == 0){
                rescuerRight.setPosition(rescuerRight.getPosition() + 0.05);
            } else {
                telemetry.addData("Right Rescuer", "STOP - Nicole Truman-Shaw");
            }
        }

        if (gamepad2.right_bumper){
            if(rescuerRight.getPosition() == 0){
                telemetry.addData("Right Rescuer", "STOP - Nicole Truman-Shaw");
            } else {
                rescuerRight.setPosition(rescuerRight.getPosition() - 0.05);
            }
        }

        if (gamepad2.left_stick_y > 0.05){
            bucketArm.setPower(1);
        } else if (gamepad2.left_stick_y < -0.05){
            bucketArm.setPower(-1);
        }

        telemetry.addData("Text", "Running!");

        //telemetry.addData("Left Motor", leftMotor.getPower());
        //telemetry.addData("Right Motor", rightMotor.getPower());
        //telemetry.addData("Grappling Hook Pin", grapplinghookPin.getPower());
        // telemetry.addData("Grappling Hook Winch", grapplingWinch.getPower());


    }
}
