//package com.springboot.demo;
//
//import org.apache.commons.io.FileUtils;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * create pengtao
// **/
//public class VinTest {
//
//    @Test
//    public void test() throws IOException {
//
//        File allfile = new File("C:\\usr\\resendVINall.txt");
//        List<String> alllist = FileUtils.readLines(allfile);
//
//        File ledaofile = new File("C:\\usr\\resendVINledao.txt");
//        List<String> ledaolsit = FileUtils.readLines(ledaofile);
//
//        File aiteFile = new File("C:\\usr\\resendVINaite.txt");
//        List<String> aitelsit = FileUtils.readLines(aiteFile);
//
//
//        System.out.println(alllist.size());
//
//        System.out.println(ledaolsit.size());
//
//        System.out.println(aitelsit.size());
//
////        for(int i = 0 ;i < alllist.size();i ++ ){
////            for (String s : ledaolsit) {
////                if(alllist.get(i).equals(s)){
////                    alllist.remove(i);
////                }
////            }
////        }
////
////        for (String allvin : alllist) {
////            for (String s : ledaolsit) {
////                if(!allvin.equals(s)){
////                    aiteList.add(allvin);
////                }
////            }
////        }
////
////
////        StringBuffer stringBuffer = new StringBuffer();
////        for (String s : alllist) {
////            stringBuffer.append(s);
////            stringBuffer.append(" ");
////        }
////
////        File aiteFile = new File("C:\\usr\\resendVINaite.txt");
////        FileUtils.writeStringToFile(aiteFile,stringBuffer.toString());
//    }
//}
