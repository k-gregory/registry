package io.github.k_gregory.registry.controller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class IndividualDtoTest {
    private static Validator validator;
    private IndividualDto validDto;
    private IndividualDto invalidDto;

    @BeforeClass
    public static void setUpClass() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Before
    public void setUp() {
        validDto = new IndividualDto();
        validDto.setUid("0124356789");
        validDto.setFirstName("First");
        validDto.setMiddleName("Middle");
        validDto.setLastName("Last");
        validDto.setBirthDate(new Date(0));
        validDto.setPassport("ХЗ123456");

        invalidDto = new IndividualDto();
    }

    @Test
    public void checkValidDto() {
        Set<ConstraintViolation<IndividualDto>> constraints = validator.validate(validDto);
        assertThat(constraints).isEmpty();
    }

    @Test
    public void checkInvalidDto() {
        Set<ConstraintViolation<IndividualDto>> constraints = validator.validate(invalidDto);
        assertThat(constraints).isNotEmpty();
    }
}