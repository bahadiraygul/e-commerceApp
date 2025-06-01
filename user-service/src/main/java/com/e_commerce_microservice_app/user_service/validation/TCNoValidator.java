package com.e_commerce_microservice_app.user_service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TCNoValidator implements ConstraintValidator<TCNoValid, String> {


    @Override
    public void initialize(TCNoValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(String tcNo, ConstraintValidatorContext constraintValidatorContext) {
        if (tcNo == null || tcNo.length() != 11) {
            return false;
        }

        if (!tcNo.matches("[0-9]+")) {
            return false;
        }

        if (tcNo.charAt(0) == '0') {
            return false;
        }

        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Integer.parseInt(String.valueOf(tcNo.charAt(i)));
        }

        int sumOddPositions = digits[0] + digits[2] + digits[4] + digits[6] + digits[8];
        int sumEvenPositions = digits[1] + digits[3] + digits[5] + digits[7];
        int tenthDigit = (sumOddPositions * 7 - sumEvenPositions) % 10;

        if (tenthDigit != digits[9]) {
            return false;
        }

        int sumFirst10 = sumOddPositions + sumEvenPositions + tenthDigit;
        int eleventhDigit = sumFirst10 % 10;

        if (eleventhDigit != digits[10]) {
            return false;
        }

        return true;


    }
}
