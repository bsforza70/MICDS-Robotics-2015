package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by BAbel on 9/21/2015.
 */
public class PopsicleDrive extends OpMode{
    //Initializing the motors as DcMotors
    DcMotor leftMotor;
    DcMotor rightMotor;



    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        //Servo stuff:
        /*
        servo1 = hardwareMap.servo.get("servo_name");
        servo2 = hardwareMap.servo.get("other_servo_name");
         */


        //reverses the right motor
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;

        leftMotor.setPower(leftY);
        rightMotor.setPower(rightY);

        //TELEMETRY testerino

        telemetry.addData("Popsicle Telemetry");
        telemetry.addData("Left Motor", leftMotor.getPower());
        telemetry.addData("Right Motor", rightMotor.getPower());
        //telemetry.addData("servo_name", servo_name.getPosition());
        //telemetry.addData("other_servo_name", other_servo_name.getPortNumber());

    }

}
