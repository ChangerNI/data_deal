package com.zafu.nichang.datadeal.web.util;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author nichang
 * @version 1.0 2019-01-16
 */
public class RegUtil {
    /**
     * 通过正则表达式匹配html块内容
     * @param pattern
     * @param html
     * @return
     */
    public static List<String> getRegInfoBlocks(Pattern pattern, String html){
        Matcher m = pattern.matcher(html);
        List<String> list = new LinkedList<String>();
        while (m.find()){
            list.add(m.group(0));
        }
        return list;
    }

    /**
     * 通过正则表达式匹配html产品属性内容
     * @param pattern
     * @param html
     * @return
     */
    public static LinkedList<String> getRegInfoDetails(Pattern pattern, String html){
        Matcher m = pattern.matcher(html);
        LinkedList<String> list = new LinkedList<>();
        while (m.find()){
            list.add(m.group(2));
        }
        return list;
    }

    /**
     * 通过正则表达式匹配html最大页数
     * @param pattern
     * @param html
     * @return
     */
    public static int getMaxPage(Pattern pattern, String html){
        Matcher m = pattern.matcher(html);
        if(m.find()){
            return Integer.parseInt(m.group(2));
        }
            return 1;
    }

}
