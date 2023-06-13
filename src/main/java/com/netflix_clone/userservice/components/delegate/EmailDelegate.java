package com.netflix_clone.userservice.components.delegate;

import com.github.newkayak12.config.SimpleEmailSender;
import com.github.newkayak12.message.HtmlMailForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Component(value = "email_delegate")
public class EmailDelegate {
    private SimpleEmailSender simpleEmailSender;
    private ThreadPoolTaskExecutor executor;

    @Autowired
    public EmailDelegate(SimpleEmailSender simpleEmailSender, ThreadPoolTaskExecutor executor) {
        this.simpleEmailSender = simpleEmailSender;
        this.executor = executor;

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setThreadNamePrefix("NETFLIX_CLONE_USER::::::THREAD");
        executor.initialize();
    }

    public Boolean sendPasswordReset(String email, String password){
        Map<String, Object> attributes =  Map.of(
                "title", "비밀번호 재설정",
                "text", password
        );
        HtmlMailForm form = HtmlMailForm.write("비밀번호 재설정", email);
        form.setText(simpleEmailSender.resolvingTemplate( "simpleSample", attributes));

        executor.execute(() -> {
            simpleEmailSender.send(form);
        });
        return true;
    }
}
