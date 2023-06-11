package com.netflix_clone.userservice.components.delegate;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component(value = "password_delegate")
public class PasswordDelegate {
    private final List<Integer> englishUpperCase = IntStream.rangeClosed(65, 90).boxed().collect(Collectors.toList());
    private final List<Integer> englishLowerCase = IntStream.rangeClosed(97, 122).boxed().collect(Collectors.toList());
    private final List<Integer> number = IntStream.rangeClosed(48, 57).boxed().collect(Collectors.toList());
    private final List<Integer> specialCase = IntStream.rangeClosed(33, 38).boxed().filter(no -> no != 34).collect(Collectors.toList());
    public String getNewResetPassword(){
        return IntStream.rangeClosed(33, 122)
                 .filter(no -> englishUpperCase.contains(no) ||
                               englishLowerCase.contains(no) ||
                               number.contains(no)           ||
                               specialCase.contains(no)
                         )
                 .limit(20L)
                 .collect(StringBuffer::new, StringBuffer::appendCodePoint, StringBuffer::append).toString();
    }

}
