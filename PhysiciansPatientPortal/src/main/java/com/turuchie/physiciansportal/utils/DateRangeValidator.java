//package com.turuchie.physiciansportal.utils;
//import java.time.LocalDate;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import software.amazon.awssdk.services.securityhub.model.DateRange;
//
//public class DateRangeValidator implements ConstraintValidator<DateRange, LocalDate> {
//    private int min;
//    private int max;
//
//    @Override
//    public void initialize(DateRange constraintAnnotation) {
//        this.min = constraintAnnotation.min();
//        this.max = constraintAnnotation.max();
//    }
//
//    @Override
//    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
//        if (dateOfBirth == null) {
//            // Let @NotNull handle null checks
//            return true;
//        }
//
//        LocalDate currentDate = LocalDate.now();
//        int age = currentDate.getYear() - dateOfBirth.getYear();
//
//        return age >= min && age <= max;
//    }
//}