package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class RobotJackson extends OpMode{
    //Initializing the motors as DcMotors
    DcMotor grapplingPin;
    DcMotor rightMotor;
    //DcMotorController MC1;
    DcMotor grapplingWinch;
    DcMotor leftMotor;
    //DcMotorController MC2;

    @Override
    public void init() {
        grapplingPin = hardwareMap.dcMotor.get("grapplingPin");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        //MC1 = hardwareMap.dcMotorController.get("controller1");
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        grapplingWinch = hardwareMap.dcMotor.get("grapplingWinch");
        //MC2 = hardwareMap.dcMotorController.get("controller2");

        //reverses the right motor
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
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

        //Legacy motors are write-only

        telemetry.addData("Text", "Running!");

        //telemetry.addData("Left Motor", leftMotor.getPower());
        //telemetry.addData("Right Motor", rightMotor.getPower());
        //telemetry.addData("Grappling Hook Pin", grapplinghookPin.getPower());
        // telemetry.addData("Grappling Hook Winch", grapplingWinch.getPower());

    }
}
