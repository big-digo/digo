package com.cetc.bigdata.analysis.web.dao;

import com.cetc.bigdata.analysis.web.pojo.Board;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BoardDao {

    int save(Board board);

    List<Board> getBoardList(String userId);

    long countExistBoardName(Map<String, Object> map);

    int update(Board board);

    int delete(Long id, String userId);

    Board getBoard(Long id);

    long checkBoardRole(String userId, Long boardId);
}
