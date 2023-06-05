package com.netflix_clone.userservice.repository.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(value = {
        "userPwd",
        "regDate",
        "isAdult",
        "adultCheckDate",
        "isSubscribed",
        "lastSignDate",
        "profiles",
        "ticketStatus"
}, allowSetters = false)
public class FindAccountRequest extends AccountDto {
}
