//package com.springboot.demo;
//
//import cn.hutool.cron.CronUtil;
//import com.springboot.demo.model.MailConfigBean;
//import org.apache.commons.io.FileUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@RunWith(SpringRunner.class)
//@EnableConfigurationProperties(MailConfigBean.class)
//@SpringBootTest
//public class DemoApplicationTests {
//
//	private Logger log = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	private MailConfigBean mailConfigBean;
//
//	@Test
//	public void contextLoads() {
//
//        try {
//            Thread.sleep (6000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //获取配置文件
//		File file = new File("c:\\usr\\sendStatus.txt");
//		if(!file.exists()) {
//			System.out.println("找不到vin文件");
//			System.exit(0);
//		}
//
//		//获取当前小时数
//		Date date = new Date();
//		SimpleDateFormat hh = new SimpleDateFormat("HH:mm");
//		String format = hh.format(date);
//
//		log.info(format);
//
//		List<String> vins= null;
//		try {
//			vins = FileUtils.readLines(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Map<Object,String> retMap = new HashMap<>();
//
//		String[] split = mailConfigBean.getSendTopic().split(",");
//		for(int j = 0;j < split.length ;j++){
//			retMap.put(j,split[j]);
//		}
//
//
//		List<String> timeList = new ArrayList<>();
//		for (int i = 0 ;i < vins.size();i ++) {
//			if(i % 2 != 0){
//				String sendTime = vins.get(i).substring(vins.get(i).length()-27,vins.get(i).length() - 22);
//				timeList.add(sendTime);
//			}
//		}
//
//		for (int j = 0 ;j < timeList.size();j++){
//			if(timeList.get(j).equals(format)){
//				System.out.println(retMap.get(j) + "没有问题");
//			} else {
//				System.out.println(retMap.get(j) + "有问题");
//			}
//		}
//
//	}
//
//}
