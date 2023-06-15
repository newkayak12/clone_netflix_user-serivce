package com.netflix_clone.userservice.repository.dto.request;

import com.netflix_clone.userservice.components.validations.ProfileValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModifyRequest {
    private Long profileNo;
    private String profileName;
    @NotEmpty(message = "변경할 값이 누락됐습니다.", groups = {ProfileValid.PushState.class})
    private Boolean isPush;


}
