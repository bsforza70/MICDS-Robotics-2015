package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class RobotJackson extends OpMode{
    //Initializing the motors as DcMotors
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor grapplinghookPin;
    DcMotor grapplingWinch;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        grapplinghookPin = hardwareMap.dcMotor.get("grapplinghookPin");
        grapplingWinch = hardwareMap.dcMotor.get("grapplingWinch");

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
            grapplinghookPin.setPower(1.0);
        }

        if(gamepad1.right_trigger > 0.05) {
            grapplingWinch.setPower(1);
        }

        if(gamepad1.left_trigger > 0.05) {
            grapplingWinch.setPower(-1);
        }
    }
}
