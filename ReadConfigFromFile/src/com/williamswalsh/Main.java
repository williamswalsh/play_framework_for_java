package com.williamswalsh;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = Main.readProperties();
        String emailRecipients = props.getProperty("x");
        String[] emailsArr = emailRecipients.split(",");
        for (String email: emailsArr) {
            System.out.println(email.trim());
        }
    }


    public static Properties readProperties() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";

        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream((appConfigPath)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return appProps;
    }
}
