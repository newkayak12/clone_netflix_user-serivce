package com.netflix_clone.userservice.components.delegate;

import com.github.newkayak12.config.SimpleEmailSender;
import com.github.newkayak12.message.HtmlMailForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component(value = "email_delegate")
@RequiredArgsConstructor
public class EmailDelegate {
    private final SimpleEmailSender simpleEmailSender;
    public Boolean send(String email, String password){
        Map<String, Object> attributes =  new HashMap<>();
        attributes.put("title", "비밀번호 재설정");
        attributes.put("text", password);
        HtmlMailForm form = HtmlMailForm.write(
                "비밀번호 재설정",
                "simpleSample",
                attributes,
                email
        );
        simpleEmailSender.send(form);
        return true;
    }
}
