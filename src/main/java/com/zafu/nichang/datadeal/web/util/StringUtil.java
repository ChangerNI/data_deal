package com.zafu.nichang.datadeal.web.util;
/**
 * @author MountainTop
 * @version 1.0 2018-07-16
 */
public class StringUtil {
    /**
     * 去除调包含html的部分内容
     * @param html
     * @return
     */
    public static String deleteHtmlContent(String html){
        return html.replace("<span class=\"dest_distance\">","")
                .replace("<tt class=\"mark\">","")
                .replace("</tt>","")
                .replace("</span>","")
                .replace("<b>","")
                .replace("</b>&nbsp;&nbsp;","");
    }

    /**
     * 去除调包含html的部分内容  同城
     * @param html
     * @return
     */
    public static String deleteHtmlContent3(String html){
        return html.replace("<i class=\"super-high\">","")
                .replace("</i>","");
    }
}
