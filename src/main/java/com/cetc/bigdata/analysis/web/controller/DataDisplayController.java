package com.cetc.bigdata.analysis.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cetc.bigdata.analysis.web.dto.DisplayDataResult;
import com.cetc.bigdata.analysis.web.service.DisplayDataService;

/**
 * Created by Qu Gang on 2017/5/22.
 */
@RestController  
@RequestMapping("/display")
public class DataDisplayController extends BaseController{
	public static final Logger logger = LoggerFactory.getLogger(DataDisplayController.class);

    @Autowired
    private DisplayDataService displayDataService;
//    public static final Gson gson = new Gson();

    @RequestMapping(value = "/getData")
    public Object getDisplayData(@RequestBody String querySql) {
    	
    	
    	logger.info("==================进入dataDisplayCtrl===================="); 
    	logger.info("==================querySql====================" + querySql); 
        DisplayDataResult result = displayDataService.getData(querySql.replaceAll("\"", "")); 
        logger.info("==================result====================" + result); 
        return result;
    }

}
