package com.verifone.infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//import com.verifone.entities.*;
public class EnvConfig {

    private String env;
    private String portal;
    private ApiUrls apiUrls;
    private String webUrl;
    private Properties prop = new Properties();
//    private User user;




    public EnvConfig(String env, String portal) throws IOException {
        this.env = env;
        this.portal = portal;
        FileInputStream ip = new FileInputStream(new File(System.getProperty("user.dir")).getParent()
                + "\\infra\\src\\main\\java\\com\\verifone\\infra\\config.properties");
        prop.load(ip);

        setEnv();
    }


    private void setEnv() throws IOException {
        this.apiUrls = new ApiUrls(env, prop);
        this.webUrl = prop.getProperty(portal + "." + env);
    }


    public ApiUrls getApiUrls() {
        return apiUrls;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getEnv(){
        return env;
    }
}
