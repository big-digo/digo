package com.cetc.bigdata.analysis.web.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Qu Gang on 2017/5/22.
 */
public class DisplayDataResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Map<String,Object>> data;
    private String msg;
    private int resultCount = 0;

    public DisplayDataResult(List<Map<String,Object>> data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public DisplayDataResult(List<Map<String,Object>> data, String msg, int resultCount) {
        this.data = data;
        this.msg = msg;
        this.resultCount = resultCount;
    }

    public List<Map<String,Object>> getData() {
        return data;
    }

    public void setData(List<Map<String,Object>> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
}
