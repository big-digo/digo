package com.cetc.bigdata.analysis.web.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    List<Long> getMenuIdByUserRole(String userId);
}
