package com.cetc.bigdata.analysis.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends BaseController {

	public static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@RequestMapping(value = "/getBoardList")
	@ResponseBody
	public Object getBoardList() {
		return JSONObject
				.parse("[{\"id\":1,\"userId\":\"1\",\"categoryId\":1,\"name\":\"图表门户\",\"layout\":{\"rows\":[{\"type\":\"widget\","
						+ "\"widgets\":[{\"widgetId\":1,\"name\":\"饼图\",\"width\":\"6\"},{\"widgetId\":3,\"name\":\"漏斗图\",\"width\":"
						+ "\"6\"}],\"height\":\"500\"},{\"type\":\"widget\",\"widgets\":[{\"widgetId\":2,\"name\":\"雷达图\",\"width\":"
						+ "\"6\"},{\"widgetId\":4,\"name\":\"图表名称\",\"width\":\"6\"}],\"height\":\"500\"},{\"type\":\"widget\","
						+ "\"widgets\":[{\"widgetId\":6,\"name\":\"桑基图\",\"width\":\"6\"},{\"widgetId\":5,\"name\":\"表单\","
						+ "\"width\":\"6\"}],\"height\":\"500\"},{\"type\":\"widget\",\"widgets\":[{\"widgetId\":9,\"name\":\"Hive饼图\","
						+ "\"width\":\"6\"},{\"widgetId\":7,\"name\":\"Hive雷达图\",\"width\":\"6\"}],\"height\":\"500\"},{\"type\":"
						+ "\"widget\",\"widgets\":[{\"widgetId\":11,\"name\":\"Hive折线图\",\"width\":\"6\"},{\"widgetId\":10,\"name\":"
						+ "\"Hive表单\",\"width\":\"6\"}],\"height\":\"500\"}]},\"categoryName\":\"myCate\"}]");
	}

	@RequestMapping(value = "/getCategoryList")
	public Object getCategoryList() {
		return JSONObject.parse("[{\"id\":1,\"userId\":\"1\",\"name\":\"myCate\"}]");
	}

	@RequestMapping(value = "/getDatasetList")
	public Object getDatasetList() {
		return JSONObject.parse("[{\"id\":1,\"userId\":\"1\",\"name\":\"myCube\",\"categoryName\":\"myCate\",\"data\":"
				+ "{\"datasource\":1,\"query\":{\"sql\":\"select organize_name,count from alarm;\"},"
				+ "\"expressions\":[]}},{\"id\":2,\"userId\":\"1\",\"name\":\"yourCube\","
				+ "\"categoryName\":\"myCate\",\"data\":{\"datasource\":3,\"query\":{\"sql\":"
				+ "\"select count(*),c.email_preferences.frequency from customers c group by c.email_preferences.frequency\"},"
				+ "\"expressions\":[]}}]");
	}

	@RequestMapping(value = "/getWidgetList")
	public Object getWidgetList() {
		return JSONObject
				.parse("[{\"id\":4,\"userId\":\"1\",\"name\":\"折线图\",\"categoryName\":\"myWidgetCate\",\"data\":{\"datasetId\":1,"
						+ "\"config\":{\"selects\":[],\"chart_type\":\"map\",\"keys\":[{\"col\":\"organize_name\",\"values\":[],\"type\":"
						+ "\"eq\"}],\"values\":[{\"name\":\"\",\"cols\":[{\"col\":\"count\",\"aggregate_type\":\"sum\"}]}],\"groups\":[],"
						+ "\"filters\":[]}}},{\"id\":11,\"userId\":\"1\",\"name\":\"折线图\",\"categoryName\":\"yourWidgetCate\",\"data\":{"
						+ "\"datasource\":3,\"query\":{\"sql\":\"select count(*) as count,c.email_preferences.frequency as frequency from customers c group by c.email_preferences.frequency\"},"
						+ "\"config\":{" + "\"selects\":[],\"valueAxis\":\"vertical\",\"chart_type\":\"line\","
						+ "\"keys\":[{\"col\":\"frequency\",\"values\":[],\"type\":\"eq\"}],\"values\":[{\"name\":\"\",\"series_type\":"
						+ "\"line\",\"type\":\"value\",\"cols\":[{\"col\":\"count\",\"aggregate_type\":\"sum\"}]}],\"groups\":[],"
						+ "\"filters\":[]}}},{\"id\":12,\"userId\":\"1\",\"name\":\"折线图_copy\",\"categoryName\":\"yourWidgetCate\","
						+ "\"data\":{\"datasource\":3,\"query\":{\"sql\":"
						+ "\"select count(*) as count,c.email_preferences.frequency as frequency from customers c group by c.email_preferences.frequency\"},"
						+ "\"config\":{\"selects\":[],\"valueAxis\":\"vertical\",\"chart_type\":"
						+ "\"line\",\"keys\":[{\"col\":\"frequency\",\"values\":[],\"type\":\"eq\"}],\"values\":[{\"name\":\"\","
						+ "\"series_type\":\"line\",\"type\":\"value\",\"cols\":[{\"col\":\"count\",\"aggregate_type\":\"sum\"}]}],"
						+ "\"groups\":[],\"filters\":[]}}},{\"id\":6,\"userId\":\"1\",\"name\":\"桑基图\",\"categoryName\":"
						+ "\"myWidgetCate\",\"data\":{\"datasetId\":1,\"config\":{\"selects\":[],\"chart_type\":\"sankey\","
						+ "\"keys\":[{\"col\":\"organize_name\",\"values\":[],\"type\":\"eq\"}],\"values\":[{\"name\":\"\","
						+ "\"cols\":[{\"col\":\"count\",\"aggregate_type\":\"sum\"}]}],\"groups\":[],\"filters\":[]}}},{\"id\":3,\"userId\":\"1\","
						+ "\"name\":\"漏斗图\",\"categoryName\":\"myWidgetCate\",\"data\":{\"datasetId\":1,\"config\":{\"selects\":[],\"chart_type\":"
						+ "\"funnel\",\"keys\":[{\"col\":\"organize_name\",\"values\":[],\"type\":\"eq\"}],\"values\":[{\"name\":\"\","
						+ "\"cols\":[{\"col\":\"count\",\"aggregate_type\":\"sum\"}]}],\"groups\":[],\"filters\":[]}}},{\"id\":5,"
						+ "\"userId\":\"1\",\"name\":\"表单\",\"categoryName\":\"myWidgetCate\",\"data\":{\"datasetId\":1,"
						+ "\"config\":{\"selects\":[],\"chart_type\":\"table\",\"keys\":[{\"col\":\"organize_name\",\"values\":[],"
						+ "\"type\":\"eq\"}],\"values\":[{\"name\":\"\",\"cols\":[{\"col\":\"count\",\"aggregate_type\":\"sum\"}]}],"
						+ "\"groups\":[],\"filters\":[]}}},{\"id\":10,\"userId\":\"1\",\"name\":\"表单\",\"categoryName\":"
						+ "\"yourWidgetCate\",\"data\":{\"datasource\":3,\"query\":{\"sql\":"
						+ "\"select count(*) as count,c.email_preferences.frequency as frequency from customers c group by c.email_preferences.frequency\"},"
						+ "\"config\":{\"selects\":[],\"chart_type\":\"table\","
						+ "\"keys\":[{\"col\":\"frequency\",\"values\":[],\"type\":\"eq\"}],\"values\":[{\"name\":\"\","
						+ "\"cols\":[{\"col\":\"count\",\"aggregate_type\":\"sum\"}]}],\"groups\":[],\"filters\":[]}}},{"
						+ "\"id\":2,\"userId\":\"1\",\"name\":\"雷达图\",\"categoryName\":\"myWidgetCate\",\"data\":{"
						+ "\"datasetId\":1,\"config\":{\"selects\":[],\"chart_type\":\"radar\",\"keys\":[{\"col\":\"organize_name\","
						+ "\"values\":[],\"type\":\"eq\"}],\"values\":[{\"name\":\"\",\"cols\":[{\"col\":\"count\",\"aggregate_type\":"
						+ "\"sum\"}]}],\"groups\":[],\"filters\":[]}}},{\"id\":7,\"userId\":\"1\",\"name\":\"雷达图\",\"categoryName\":"
						+ "\"yourWidgetCate\",\"data\":{\"datasource\":3,\"query\":{\"sql\":"
						+ "\"select count(*) as count,c.email_preferences.frequency as frequency from customers c group by c.email_preferences.frequency\"},"
						+ "\"config\":{\"selects\":[],\"chart_type\":\"radar\",\"keys\":[{"
						+ "\"col\":\"frequency\",\"values\":[],\"type\":\"eq\"}],\"values\":[{\"name\":\"\",\"cols\":[{\"col\":"
						+ "\"count\",\"aggregate_type\":\"sum\"}]}],\"groups\":[],\"filters\":[]}}},{\"id\":1,\"userId\":\"1\","
						+ "\"name\":\"饼图\",\"categoryName\":\"myWidgetCate\",\"data\":{\"datasetId\":1,\"config\":{\"selects\":[],"
						+ "\"chart_type\":\"pie\",\"keys\":[{\"col\":\"organize_name\",\"values\":[],\"type\":\"eq\"}],\"values\":[{"
						+ "\"name\":\"\",\"cols\":[{\"col\":\"count\",\"aggregate_type\":\"sum\"}]}],\"groups\":[],\"filters\":[]}}},{"
						+ "\"id\":9,\"userId\":\"1\",\"name\":\"饼图\",\"categoryName\":\"yourWidgetCate\",\"data\":{\"datasource\":3,"
						+ "\"query\":{\"sql\":"
						+ "\"select count(*) as count,c.email_preferences.frequency as frequency from customers c group by c.email_preferences.frequency\"},"
						+ "\"config\":{\"selects\":[],\"chart_type\":\"pie\",\"keys\":[{\"col\":"
						+ "\"frequency\",\"values\":[],\"type\":\"eq\"}],\"values\":[{\"name\":\"\",\"cols\":[{\"col\":\"count\","
						+ "\"aggregate_type\":\"sum\"}]}],\"groups\":[],\"filters\":[]}}}]");
	}
}
