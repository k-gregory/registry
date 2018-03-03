package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.infrastructure.ResourceNotFoundException;
import io.github.k_gregory.registry.model.IndividualDebtor;
import io.github.k_gregory.registry.repository.IndividualDebtorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.List;

class IndividualDebtorDto {
    @NotNull
    @Size(min = 10, max = 10)
    private String uid;

    @NotNull
    private String firstName;

    @NotNull
    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private Date birthDate;

    @NotNull
    @Pattern(regexp = "^(?:(?![ЫЪЭ])[А-ЯЄІЇ]){2}\\d{6}$", flags = Pattern.Flag.UNICODE_CASE)
    private String passport;

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() { return middleName; }

    public String getFirstName() { return firstName; }

    public String getUid() { return uid; }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassport() {
        return passport;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}

@RestController
@RequestMapping("/api/individual/debtor")
public class IndividualDebtorController {
    private ModelMapper mapper;
    private IndividualDebtorRepository debtors;

    @Autowired
    public IndividualDebtorController(ModelMapper mapper, IndividualDebtorRepository debtors)
    {
        this.mapper = mapper;
        this.debtors = debtors;
    }

    @GetMapping("/{uid}")
    public IndividualDebtorDto findByUid(@PathVariable(value="uid") String uid)
    {
        IndividualDebtor debtor = this.debtors.findByUid(uid);

        if(debtor == null)
            throw new ResourceNotFoundException();

        return mapper.map(debtor, IndividualDebtorDto.class);
    }

    @GetMapping("/search/{uid}")
    public List<IndividualDebtorDto> findByUidPart(@PathVariable(value="uid") String uid)
    {
        List<IndividualDebtor> debtors = this.debtors.findByUidStartsWith(uid);

        Type listType = new TypeToken<List<IndividualDebtorDto>>() {}.getType();
        return mapper.map(debtors, listType);
    }
}
