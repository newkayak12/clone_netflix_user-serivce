package com.netflix_clone.userservice.service;

import com.netflix_clone.userservice.components.constants.Constants;
import com.netflix_clone.userservice.components.delegate.EmailDelegate;
import com.netflix_clone.userservice.components.delegate.PasswordDelegate;
import com.netflix_clone.userservice.components.exceptions.BecauseOf;
import com.netflix_clone.userservice.components.exceptions.CommonException;
import com.netflix_clone.userservice.repository.domains.Account;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.reference.TicketRaiseLogDto;
import com.netflix_clone.userservice.repository.dto.request.ChangePasswordRequest;
import com.netflix_clone.userservice.repository.dto.request.FindAccountRequest;
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
import tokenManager.TokenControl;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
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
    private final TokenControl tokenControl;
    private final PasswordDelegate passwordDelegate;
    private final EmailDelegate emailDelegate;



    private Boolean isPasswordMatched(String rawPassword, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
    }

    private void encryptPassword(AccountDto accountDto){
        accountDto.setUserPwd(bCryptPasswordEncoder.encode(accountDto.getUserPwd()));
    }

    public AccountDto signIn(SignInRequest signInRequest, HttpServletResponse response) throws CommonException {
        AccountDto dto = Optional.ofNullable(repository.signIn(signInRequest)).orElseThrow(() -> new CommonException(BecauseOf.ACCOUNT_NOT_EXIST));
        if(!this.isPasswordMatched(signInRequest.getUserPwd(), dto.getUserPwd())) throw new CommonException(BecauseOf.PASSWORD_NOT_MATCHED);

        TicketRaiseLogDto ticketStatus = Optional.ofNullable(ticketRaiseRepository.ticketInfoByUserNo(dto.getUserNo(), LocalDate.now()))
                                                 .orElseGet(() -> null);

        dto.setTicketStatus(ticketStatus);
        response.addHeader(Constants.TOKEN_NAME, tokenControl.encrypt(dto));
        return dto;
    }

    @Transactional(readOnly = true)
    public Boolean checkDuplicateId(String userId) {
        return repository.countAccountByUserId(userId).map( result -> result > 0 ? false : true).orElseGet(() -> true);
    }

    public AccountDto signUp(SignUpRequest signUpRequest, HttpServletResponse response) throws CommonException {
        if(Objects.nonNull(signUpRequest.getUserNo())) throw new CommonException(BecauseOf.ALREADY_EXIST_ACCOUNT);


        AccountDto dto =  signUpRequest;
        encryptPassword(dto);

        Account account = mapper.map(dto, Account.class);
        account = repository.save(account);
        dto = mapper.map(account, AccountDto.class);

        response.addHeader(Constants.TOKEN_NAME, tokenControl.encrypt(dto));
        return dto;
    }

    public Boolean changePassword(ChangePasswordRequest changePasswordRequest) throws CommonException {
        AccountDto dto = repository.findAccountByUserId(changePasswordRequest.getUserId())
                                   .map(result -> mapper.map(result, AccountDto.class))
                                   .orElseThrow(() -> new CommonException(BecauseOf.NO_DATA));

        changePasswordRequest.setUserNo(dto.getUserNo());

        if(!isPasswordMatched(changePasswordRequest.getUserPwd(), dto.getUserPwd()) ) throw new CommonException(BecauseOf.PASSWORD_NOT_MATCHED);
        if(!repository.changePassword(changePasswordRequest)) throw  new CommonException(BecauseOf.UPDATE_FAILURE);


        return true;
    }

    public String findId(FindAccountRequest request) throws CommonException {
        return repository.findAccountByEmailAndMobileNo( request.getEmail(), request.getMobileNo() )
                                   .map(Account::getUserId)
                                   .orElseThrow(() -> new CommonException(BecauseOf.NO_DATA));
    }

    public Boolean findPassword(FindAccountRequest request) throws CommonException {
        AccountDto accountDto = repository.findAccountByUserIdAndEmailAndMobileNo(
                request.getUserId(), request.getEmail(), request.getMobileNo()
        )
        .map(account -> mapper.map(account, AccountDto.class))
        .orElseThrow(() -> new CommonException(BecauseOf.NO_DATA) );

        String resetPassword = passwordDelegate.getNewResetPassword();
        String encodedPassword = bCryptPasswordEncoder.encode(resetPassword);

        accountDto.setUserPwd(encodedPassword);
        Optional<Account> result = Optional.ofNullable(repository.save(mapper.map(accountDto, Account.class)));

        if(result.isPresent() && emailDelegate.sendPasswordReset(accountDto.getEmail(), resetPassword)) return true;
        else throw new CommonException(BecauseOf.UPDATE_FAILURE);
    }
}
