package com.netflix_clone.userservice.repository.domains;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProfileId implements Serializable {
    @OneToOne
    @JoinColumn(name = "profileNo")
    private Profile profile;
}
