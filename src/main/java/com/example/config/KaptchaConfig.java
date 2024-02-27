package com.example.config;

import com.example.properties.CaptchaProperties;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;



@Configuration

public class KaptchaConfig {
//    @Autowired
//    private CaptchaProperties captchaProperties;



    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 配置属性，例如边框、颜色等
//        properties.setProperty("kaptcha.border", "yes");
//        properties.setProperty("kaptcha.border.color", "105,179,90");
        // ... 其他属性
        // 设置验证码字符的长度为4
        properties.setProperty("kaptcha.textproducer.char.length", "6");

        // 设置验证码图片的高度和宽度（以像素为单位）
        properties.setProperty("kaptcha.image.width", "200");
        properties.setProperty("kaptcha.image.height", "50");

        // 设置验证码类型，可以是 "text" 或 "math"，或者其他类型
//        properties.setProperty("kaptcha.textproducer.impl", "com.google.code.kaptcha.text.impl.DefaultTextCreator");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
