package com.cetc.bigdata.analysis.web.dao;

import com.cetc.bigdata.analysis.web.pojo.Widget;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WidgetDao {

    List<String> getCategoryList();

    List<Widget> getWidgetList(String userId);

    int save(Widget widget);

    long countExistWidgetName(Map<String, Object> map);

    int update(Widget widget);

    int delete(Long id, String userId);

    Widget getWidget(Long id);

    long checkWidgetRole(String userId, Long widgetId);
}
