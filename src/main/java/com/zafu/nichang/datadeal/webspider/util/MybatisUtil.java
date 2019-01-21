package com.zafu.nichang.datadeal.webspider.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author ：倪畅
 * @date ：Created in 2019/1/21 23:21
 * @description：Mybatis工具类
 * @modified By：
 * @version: 1.0$
 */
public class MybatisUtil {

    private static SqlSessionFactory factory;
    // 在静态代码块下,factory只会被创建一次

    static {
        System.out.println("static factory==============");
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 打开SqlSession
     * @return
     */
    public static SqlSession createSqlSession() {
        // true 为自动提交事务 默认为true
        return factory.openSession(false);
    }

    /**
     * 关闭SqlSession
     * @param sqlsession
     */
    public static void closeSqlSession(SqlSession sqlsession) {
        if (null != sqlsession) {
            sqlsession.close();
        }
    }
}