package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by BAbel on 9/21/2015.
 */
public class GamepadTest extends OpMode{
    //Initializing the motors as DcMotors
    DcMotor leftMotor;
    DcMotor rightMotor;



    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        //reverses the right motor
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        leftMotor.setPower(leftY);
        rightMotor.setPower(rightY);

        if(gamepad1.dpad_down) {
            leftMotor.setPower(0.33);
            rightMotor.setPower(0.33);
        }

        if(gamepad1.dpad_left) {
            leftMotor.setPower(0.33);
            rightMotor.setPower(0.67);
        }

        if(gamepad1.dpad_right) {
            leftMotor.setPower(0.67);
            rightMotor.setPower(0.33);
        }

        if(gamepad1.dpad_up) {
            leftMotor.setPower(1.0);
            rightMotor.setPower(1.0);
        }

        if(gamepad1.left_bumper) {
            leftMotor.setPower(1.0);
            rightMotor.setPower(-1.0);
        }

        if(gamepad1.right_bumper) {
            leftMotor.setPower(-1.0);
            rightMotor.setPower(1.0);
        }

        if(gamepad1.a) {
            leftMotor.setPower(0.1);
            rightMotor.setPower(0.1);
        }

        if(gamepad1.b) {
            leftMotor.setPower(0.2);
            rightMotor.setPower(0.2);
        }

        if(gamepad1.x) {
            leftMotor.setPower(0.3);
            rightMotor.setPower(0.3);
        }

        if(gamepad1.y) {
            leftMotor.setPower(0.4);
            rightMotor.setPower(0.4);
        }

        if(gamepad1.left_trigger >= 0.05) {
            leftMotor.setPower(gamepad1.left_trigger);
            rightMotor.setPower(-1 * gamepad1.left_trigger);
        }

        if(gamepad1.right_trigger >= 0.05) {
            leftMotor.setPower(-1 * gamepad1.right_trigger);
            rightMotor.setPower(gamepad1.right_trigger);
        }
    }

}
