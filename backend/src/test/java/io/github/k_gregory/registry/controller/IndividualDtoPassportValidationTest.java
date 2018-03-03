package io.github.k_gregory.registry.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Date;
import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(value = Parameterized.class)
public class IndividualDtoPassportValidationTest {

    private final IndividualDto individualDto;
    private final boolean isValid;
    private final Validator validator;

    @Parameterized.Parameters(name= "{index}: isValid({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                        {"ТТ556556", true},
                        {"ІВ998765", true},
                        {"АА999999", true},
                        {"ЯЯ000000", true},
                        {"ЄА765456", true},
                        {"ЇІ987678", true},

                        {"JK123321", false},
                        {"ЫВ123453", false},
                        {"ЭВ123453", false},
                        {"ЛЪ222453", false},
                        {"ЇІІ987678", false},
                        {"ЇІ9287678", false},
                        {"11987678", false},
                        {"ЇІИМАПМВ", false},
                        {"ABCDEFGB", false}
                }
        );
    }

    public IndividualDtoPassportValidationTest(String passport, boolean isValid)
    {
        this.isValid = isValid;
        this.individualDto = new IndividualDto();

        individualDto.setUid("0124356789");
        individualDto.setFirstName("First");
        individualDto.setMiddleName("Middle");
        individualDto.setLastName("Last");
        individualDto.setBirthDate(new Date(0));
        individualDto.setPassport(passport);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void validatePassword() {
        Set<ConstraintViolation<IndividualDto>> constraints = validator.validate(individualDto);
        assertValid(constraints, isValid);
    }

    private void assertValid(Set<ConstraintViolation<IndividualDto>> constraints, boolean valid)
    {
        if(valid)
            assertThat(constraints).isEmpty();
        else
            assertThat(constraints).isNotEmpty();
    }
}
