package com.example.utils;

public class EmailTemplate {
    public static String topic="合研注册";
    public static String vericodeHtml1 = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>博客 邮箱验证码</title>\n" +
            "    <style>\n" +
            "\n" +
            "        .main {\n" +
            "            margin: 10px auto;\n" +
            "            width: 520px;\n" +
            "\n" +
            "            border-top: 4px solid #9373EE;\n" +
            "            padding: 24px 24px 40px;\n" +
            "            border-radius:0 0 8px 8px;\n" +
            "            box-shadow: 0px 0px 1px;\n" +
            "        }\n" +
            "\n" +
            "        .title {\n" +
            "            margin: 80px auto 32px;\n" +
            "            font-size: 32px;\n" +
            "            font-weight: 600;\n" +
            "            line-height: 45px;\n" +
            "            letter-spacing: 0px;\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "        .note {\n" +
            "            margin: 0 auto;\n" +
            "            font-size: 18px;\n" +
            "            line-height: 1.4;\n" +
            "            left: 0px;\n" +
            "            top: 77px;\n" +
            "            font-weight: 400;\n" +
            "        }\n" +
            "\n" +
            "        .code {\n" +
            "            padding: 16px;\n" +
            "            text-align: center;\n" +
            "            background: rgba(147, 115, 238, 0.04);\n" +
            "            border-radius: 4px;\n" +
            "            font-weight: 600;\n" +
            "            font-size: 24px;\n" +
            "            line-height: 140%;\n" +
            "            color: #9373EE;\n" +
            "            margin: 24px 0;\n" +
            "            letter-spacing: 1px;\n" +
            "        }\n" +
            "\n" +
            "        .claim ul {\n" +
            "            margin-top: 34px;\n" +
            "            margin-bottom: 40px;\n" +
            "            font-size: 13px;\n" +
            "            line-height: 1.6;\n" +
            "            color: #5c5c5c;\n" +
            "            padding: 25px 0;\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "        .claim ul li {\n" +
            "            color: rgba(24, 24, 25, 0.42);\n" +
            "            line-height: 30px;\n" +
            "        }\n" +
            "\n" +
            "        .footer {\n" +
            "            font-size: 13px;\n" +
            "            line-height: 1.6;\n" +
            "            color: #5c5c5c;\n" +
            "            padding: 25px 0\n" +
            "        }\n" +
            "        .title,.note,.claim,.footer {\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class=\"main\">\n" +
            "    <div class=\"title\">博客 邮箱账号验证码</div>\n" +
            "    <div class=\"note\">你正在进行邮箱验证操作，验证码为:</div>\n" +
            "    <div class=\"code\" :data=\"123456\">";
    public static String vericodeHtml2="</div>\n" +
            "\n" +
            "    <div class=\"claim\">\n" +
            "        <ul style=\"list-style: none;\">\n" +
            "            <li style=\"list-style: none;\">此验证码 15 分钟内有效</li>\n" +
            "            <li style=\"list-style: none;\">如非本人操作</li>\n" +
            "            <li style=\"list-style: none;\">转给他人将导致账号被盗和个人信息泄漏，谨防诈骗</li>\n" +
            "        </ul>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"footer\">\n" +
            "        <a href=\"https://blog.csdn.net/qq_62254095?spm=1018.2226.3001.5343\" target=\"_blank\" style=\"color: #9373EE; text-decoration: none;\">个人博客</a> - 记录学习的每一分钟\n" +
            "    </div>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";

}
