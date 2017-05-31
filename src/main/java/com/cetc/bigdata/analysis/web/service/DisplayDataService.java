package com.cetc.bigdata.analysis.web.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cetc.bigdata.analysis.web.dao.DisplayDataDao;
import com.cetc.bigdata.analysis.web.dto.DisplayDataResult;

@Service
public class DisplayDataService {
	
	public static final Logger logger = LoggerFactory.getLogger(DisplayDataService.class);
	
    @Value("${resultLimit:200000}")
    private int resultLimit;
    
    @Autowired
    private DisplayDataDao displayDataDao;
    

    public DisplayDataResult getData(String query) {
    	List<Map<String,Object>> list = null;
        int resultCount = 0;
        String msg = "OK";
        
        //logger.debug("resultLimit============" + resultLimit); 

        try {
            resultCount = displayDataDao.resultCount(query);
            if (resultCount > resultLimit) {
                msg = "Cube result count is " + resultCount + ", greater than limit " + resultLimit;
            } else {
            	list = displayDataDao.getData(query);
            }
        } catch (Exception e) {
            msg =  e.getMessage();
        }
        return new DisplayDataResult(list, msg, resultCount);
    }
    
    
}
