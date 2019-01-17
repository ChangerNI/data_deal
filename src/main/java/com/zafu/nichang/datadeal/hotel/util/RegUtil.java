package com.zafu.nichang.datadeal.hotel.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author MountainTop
 * @version 1.0 2018-07-16
 */
public class RegUtil {
    /**
     * 通过正则表达式匹配html内容
     * @param reg
     * @param html
     * @return
     */
    public static String getRegInfo(String reg,String html){
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(html);
        while(m.find()){
            return m.group(2);
        }
        return null;
    }
    /**
     * 是否包含某个字符串
     * @param reg
     * @param html
     * @return
     */
    public static boolean isFindHotel(String reg,String html){
        boolean b=false;
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(html);
        while(m.find()){
            return true;
        }
        return b;
    }


}
