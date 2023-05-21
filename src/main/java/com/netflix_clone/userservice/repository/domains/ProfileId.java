package com.netflix_clone.userservice.repository.domains;

import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class ProfileId implements Serializable {
    private static final long serialVersionUID = -1035697788972782814L;
    @OneToOne
    @JoinColumn(name = "profileNo")
    private Profile profile;
}
