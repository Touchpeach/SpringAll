package com.springboot.demo.job;

import cn.hutool.http.HttpUtil;

/**
 * create pengtao
 **/
public class SendEmailJob {

    public void sendEmail(){
        HttpUtil.get("http://localhost:8009/job/guardSend");
    }
}
