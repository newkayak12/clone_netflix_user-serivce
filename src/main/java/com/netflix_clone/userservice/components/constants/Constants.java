package com.netflix_clone.userservice.components.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "constants")
public class Constants {

    public static String SALT;
    public static String TOKEN_NAME;
    public static String PROJECT_NAME;

    @Value("${constant.file_path}")
    public void setSalt(String _SALT) { SALT = _SALT; }
    @Value("${constant.token_name}")
    public void setTokenName(String _TOKEN_NAME) { TOKEN_NAME = _TOKEN_NAME; }
    @Value("${constant.project_name}")
    public void setProjectName(String _PROJECT_NAME) { PROJECT_NAME = _PROJECT_NAME; }

}
