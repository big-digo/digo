package com.cetc.bigdata.analysis.web.dao;

import com.cetc.bigdata.analysis.web.pojo.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoryDao {

    List<Category> getCategoryList();

    int save(Category category);

    long countExistCategoryName(Map<String, Object> map);

    int update(Category category);

    int delete(Long id);
}
