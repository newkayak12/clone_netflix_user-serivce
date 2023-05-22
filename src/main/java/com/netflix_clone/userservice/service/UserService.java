package com.netflix_clone.userservice.service;

import com.netflix_clone.userservice.exceptions.BecauseOf;
import com.netflix_clone.userservice.exceptions.CommonException;
import com.netflix_clone.userservice.repository.deviceRepository.DeviceRepository;
import com.netflix_clone.userservice.repository.domains.Account;
import com.netflix_clone.userservice.repository.domains.TicketRaiseLog;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.reference.MobileDeviceInfoDto;
import com.netflix_clone.userservice.repository.dto.reference.TicketRaiseLogDto;
import com.netflix_clone.userservice.repository.dto.request.ChangePasswordRequest;
import com.netflix_clone.userservice.repository.dto.request.SignInRequest;
import com.netflix_clone.userservice.repository.dto.request.SignUpRequest;
import com.netflix_clone.userservice.repository.ticketRaiseRepository.TicketRaiseRepository;
import com.netflix_clone.userservice.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserService {
    private final UserRepository repository;
    private final TicketRaiseRepository ticketRaiseRepository;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private Boolean isPasswordMatched(String rawPassword, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
    }

    private void encryptPassword(AccountDto accountDto){
        accountDto.setUserPwd(bCryptPasswordEncoder.encode(accountDto.getUserPwd()));
    }

    public AccountDto signIn(SignInRequest signInRequest) throws CommonException {
        AccountDto dto = Optional.ofNullable(repository.signIn(signInRequest)).orElseThrow(() -> new CommonException(BecauseOf.ACCOUNT_NOT_EXIST));
        if(!this.isPasswordMatched(signInRequest.getUserPwd(), dto.getUserPwd())) throw new CommonException(BecauseOf.PASSWORD_NOT_MATCHED);

        TicketRaiseLogDto ticketStatus = Optional.ofNullable(ticketRaiseRepository.ticketInfoByUserNo(dto.getUserNo(), LocalDate.now()))
                                               .orElseGet(() -> null);
        dto.setTicketStatus(ticketStatus);
        return dto;
    }

    @Transactional(readOnly = true)
    public Boolean checkDuplicateId(String userId) {
        return repository.countAccountByUserId(userId).map( result -> result > 0 ? false : true).orElseGet(() -> true);
    }

    public AccountDto signUp(SignUpRequest signUpRequest) throws CommonException {
        if(Objects.nonNull(signUpRequest.getUserNo())) throw new CommonException(BecauseOf.ALREADY_EXIST_ACCOUNT);

        AccountDto dto =  signUpRequest;
        encryptPassword(dto);
        Account account = mapper.map(signUpRequest, Account.class);

        return mapper.map(repository.save(account), AccountDto.class);
    }

    public Boolean changePassword(ChangePasswordRequest changePasswordRequest) throws CommonException {
        AccountDto dto = repository.findAccountByUserId(changePasswordRequest.getUserId())
                                   .map(result -> mapper.map(result, AccountDto.class))
                                   .orElseThrow(() -> new CommonException(BecauseOf.NO_DATA));

        if(
                isPasswordMatched(changePasswordRequest.getUserPwd(), dto.getUserPwd()) &&
                !repository.changePassword(changePasswordRequest)
        ) throw  new CommonException(BecauseOf.UPDATE_FAILURE);


        return true;
    }
}
