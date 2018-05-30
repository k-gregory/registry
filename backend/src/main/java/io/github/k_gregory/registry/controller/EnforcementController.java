package io.github.k_gregory.registry.controller;

import io.github.k_gregory.registry.dto.TopEnforcementDTO;
import io.github.k_gregory.registry.model.Enforcement;
import io.github.k_gregory.registry.model.EnforcementState;
import io.github.k_gregory.registry.model.Facility;
import io.github.k_gregory.registry.model.Subject;
import io.github.k_gregory.registry.repository.EnforcementRepository;
import io.github.k_gregory.registry.repository.FacilityRepository;
import io.github.k_gregory.registry.repository.SubjectRepository;
import io.github.k_gregory.registry.service.EnforcementService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

class EnforcementResponse {
    private Long id;
    private String senderId;
    private String senderName;
    private String receiverId;
    private String facilityName;
    private String receiverName;
    private Date startedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }
}

class EnS {
    private Long senderId;
    private Long receiverId;
    private Long facilityId;

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }
}

@RestController
@RequestMapping("/api/enforcement")
public class EnforcementController {
    private final EnforcementService enforcement;
    private final EnforcementRepository er;

    private final SubjectRepository sr;
    private final FacilityRepository fr;

    private final ModelMapper mapper;

    @Autowired
    public EnforcementController(EnforcementService enforcement, EnforcementRepository er, SubjectRepository sr, FacilityRepository fr, ModelMapper mapper) {
        this.enforcement = enforcement;
        this.er = er;
        this.sr = sr;
        this.fr = fr;
        this.mapper = mapper;
    }

    @RequestMapping(path = "/top", method = RequestMethod.GET)
    public List<TopEnforcementDTO> topEnforcements() {
        return enforcement.listTopEnforcements();
    }

    @GetMapping
    public List<EnforcementResponse> allEnforcements() {
        Type type = new TypeToken<List<EnforcementResponse>>() {
        }.getType();
        return mapper.map(er.findAll(), type);
    }

    @PostMapping
    @Transactional
    public EnforcementResponse addEnforcement(@RequestBody EnS enS) {
        Facility f = fr.findById(enS.getFacilityId()).get();
        Subject r = sr.findById(enS.getReceiverId()).get();
        Subject s = sr.findById(enS.getSenderId()).get();

        Enforcement enforcement = new Enforcement();
        enforcement.setStartedAt(new Date());
        enforcement.setState(EnforcementState.OPENED);
        enforcement.setFacility(f);
        enforcement.setReceiver(r);
        enforcement.setSender(s);

        return mapper.map(er.save(enforcement), EnforcementResponse.class);
    }
}
