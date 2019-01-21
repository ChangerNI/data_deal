package com.zafu.nichang.datadeal.web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * @author 倪畅
 * @version 1.0 2019-01-16
 */
public class DateUtil {
    /**
     * 获取n天后的日期  明天或后天
     * @param value
     * @return
     * @throws Exception
     */
    public static String getTomorrowDate(Integer value) throws Exception{
        Date date=new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,value);
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
