package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.utils.JwtUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.example.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class Captcha {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private RedisCache redisCache;
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response) throws Exception {



        // 生成验证码文本
        String text = defaultKaptcha.createText();
        String key = JwtUtil.getUUID();
        redisCache.setCacheObject(key, text);
        redisCache.expire(key, 3, TimeUnit.MINUTES);
        // 使用验证码文本生成图片
        BufferedImage image = defaultKaptcha.createImage(text);
        // 设置响应格式为图片
        response.setContentType("image/jpeg");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        // 设置响应格式为JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 将UUID添加到响应体中
        Map<String, Object> result = new HashMap<>();
        result.put("uuid", key);
        result.put("image", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(outputStream.toByteArray()));

        // 将结果写入响应流
        String json = JSON.toJSONString(result);
        response.getOutputStream().write(json.getBytes("UTF-8"));
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
//    手机号绑定
//    @GetMapping("/captcha")
//    public void getCaptcha(HttpServletResponse response, String key) throws Exception {
//        // 生成验证码文本
//        String text =defaultKaptcha.createText();
//        redisCache.setCacheObject(key,text);
//        redisCache.expire(key,3, TimeUnit.MINUTES);
//        // 使用验证码文本生成图片
//        BufferedImage image = defaultKaptcha.createImage(text);
//
//        // 设置响应格式为图片
//        response.setContentType("image/jpeg");
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ImageIO.write(image, "jpg", outputStream);
//        response.getOutputStream().write(outputStream.toByteArray());
//        response.getOutputStream().flush();
//        response.getOutputStream().close();
//    }
}
