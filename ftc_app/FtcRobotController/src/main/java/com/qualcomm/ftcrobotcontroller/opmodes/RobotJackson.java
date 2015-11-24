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
    //DcMotorController MC1;
    DcMotor grapplingWinch;
    DcMotor leftMotor;

    Servo servoMotorGateRight;
    Servo servoMotorGateLeft;
    //DcMotorController MC2;

    LightSensor sensorLight;

    @Override
    public void init() {
        grapplingPin = hardwareMap.dcMotor.get("grapplingPin");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        //MC1 = hardwareMap.dcMotorController.get("controller1");
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        grapplingWinch = hardwareMap.dcMotor.get("grapplingWinch");

        servoMotorGateRight = hardwareMap.servo.get("servogr");
        servoMotorGateLeft = hardwareMap.servo.get("servogl");
        //MC2 = hardwareMap.dcMotorController.get("controller2");

        sensorLight = hardwareMap.lightSensor.get("sensorl");

        //reverses the right motor
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        leftMotor.setPower(leftY);
        rightMotor.setPower(rightY);

        double light = sensorLight.getLightDetected();

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

        if (gamepad1.right_bumper) {
            servoMotorTilt.setPosition(servoMotorTilt.getPosition() + 0.05);
        } else if (gamepad1.left_bumper) {
            servoMotorTilt.setPosition(servoMotorTilt.getPosition() - 0.05 );
        }
        if (gamepad1.a) {
            servoMotorGateLeft.setPosition(servoMotorGateLeft.getPosition() + 0.05);
        } else if (gamepad1.b) {
            servoMotorGateLeft.setPosition(servoMotorGateLeft.getPosition() - 0.05 );
        }
        if (gamepad1.x) {
            servoMotorGateRight.setPosition(servoMotorGateRight.getPosition() + 0.05);
        } else if (gamepad1.y) {
            servoMotorGateRight.setPosition(servoMotorGateRight.getPosition() - 0.05 );
        }
        //Legacy motors are write-only

        telemetry.addData("Text", "Running!");

        //telemetry.addData("Left Motor", leftMotor.getPower());
        //telemetry.addData("Right Motor", rightMotor.getPower());
        //telemetry.addData("Grappling Hook Pin", grapplinghookPin.getPower());
        // telemetry.addData("Grappling Hook Winch", grapplingWinch.getPower());


        telemetry.addData("Light Sensed", sensorLight.getLightDetected());
        //Change to whatever value corresponds to blue
        if(light <= 0.5){
            telemetry.addData("Text", "Red");
        }
        //Change to whatever value corresponds to red
        if(light > 0.5){
            telemetry.addData("Text", "Blue");
        }

    }
}
