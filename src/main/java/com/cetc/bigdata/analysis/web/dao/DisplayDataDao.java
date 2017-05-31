package com.cetc.bigdata.analysis.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Created by Qu Gang on 2017/5/22.
 */
@Repository
public class DisplayDataDao {
	
	private static final Logger logger = LoggerFactory.getLogger(DisplayDataDao.class);
	
	@Value("${jdbc.driverClassName}")
	private String driver;
	
	@Value("${jdbc.url}")
	private String jdbcurl;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;

    public List<Map<String,Object>> getData(String query) throws Exception {

        Connection con = getConnection();

        String sql = query;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String,Object>> list = null;
        Map<String,Object> map = null;

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            if(columnCount > 0){
            	list = new LinkedList<Map<String,Object>>();
            }
            while (rs.next()) {
            	map = new HashMap<String,Object>();
                for (int j = 0; j < columnCount; j++) {
                	map.put(metaData.getColumnLabel(j + 1), rs.getString(j + 1));
                }
                list.add(map);
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }

        return list;
    }

    public int resultCount(String query) throws Exception {
        Connection con = getConnection();
        StringBuffer cubeSqlBuffer = new StringBuffer();
        String querySql = query.replace(";", "");
        boolean isOracle = driver.toLowerCase().indexOf("oracle") >= 0;
        cubeSqlBuffer.append("SELECT count(*) AS cnt FROM ( ")
                .append(querySql)
                .append(" ) ")
                .append(isOracle ? "" : " AS ")
                .append("cube_query_");

        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {
            ps = con.prepareStatement(cubeSqlBuffer.toString());
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt("cnt");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }
        return count;
    }

    private Connection getConnection() throws Exception {

    	//logger.debug("driver===========" + driver); 
    	//logger.debug("username===========" + username); 
        //logger.debug("password===========" + password); 
        //logger.debug("jdbcurl===========" + jdbcurl); 
        
        Class.forName(driver);        
        Properties props = new Properties();
        props.setProperty("user", username);        
        props.setProperty("password", password);
        return DriverManager.getConnection(jdbcurl, props);
    }
}
