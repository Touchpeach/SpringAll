package com.springboot.demo.controller;

import cn.hutool.cron.CronUtil;
import com.springboot.demo.model.MailConfigBean;
import com.springboot.demo.model.ResponseBo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * create pengtao
 **/
@RestController
@RequestMapping("/job")
public class SendStausJobController {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String from;


    @Autowired
    private MailConfigBean mailConfigBean;

    /**
     * 对外提供接口，查看转发的状态
     * @return
     */
    @RequestMapping("/guardSend")
    public ResponseBo guardSend(){

        try {
            Thread.sleep (6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //更新最新的日志文件
        try {
            Process exec = Runtime.getRuntime().exec("sh /data/server/send1_ledao/lookLog/lookout.sh");
            exec.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(mailConfigBean.getLocal());
        //获取配置文件
        File file = new File(mailConfigBean.getLocal());
        if(!file.exists()) {
            System.out.println("找不到vin文件");
            System.exit(0);
        }

        //获取当前小时数
        Date date = new Date();
        SimpleDateFormat hh = new SimpleDateFormat("HH:mm");
        String format = hh.format(date);

        log.info("现在时间：" + format);

        List<String> vins= null;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            vins = FileUtils.readLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Object,String> topicMap = new HashMap<>();

        String[] split = mailConfigBean.getSendTopic().split(",");
        for(int j = 0;j < split.length ;j++){
            topicMap.put(j,split[j]);
        }


        List<String> timeList = new ArrayList<>();
        for (int i = 0 ;i < vins.size();i ++) {
            if(i % 2 != 0){
                stringBuffer.append("vinlog>>>>>>>>" + vins.get(i));
                String sendTime = vins.get(i).substring(vins.get(i).length()-27,vins.get(i).length() - 22);
                timeList.add(sendTime);
            }
        }

        int flag = 0;

        for (int j = 0 ;j < timeList.size();j++){
            if(timeList.get(j).equals(format)){
                stringBuffer.append(topicMap.get(j) + "没有问题  ");
            } else {
                stringBuffer.append(topicMap.get(j) + "有问题  ");
                flag = 1;
            }
        }

        String s = "";

        if(flag == 1){
            log.info(stringBuffer.toString());
            s = SendEmail(stringBuffer.toString());
        }

        return ResponseBo.ok(stringBuffer.toString()+ "  " + s);
    }


    public String SendEmail(String mail){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(new String[]{"pengtao.li@futuremove.cn","shaowang.wei@futuremove.cn","yugang.yang@futuremove.cn"}); // 接收地址
            message.setSubject("转发服务状态"); // 标题
            message.setText(mail); // 内容
            jms.send(message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
