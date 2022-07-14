package com.springapp.mvc.board.service;


import com.springapp.mvc.board.model.BoardDTO;

import java.util.List;


public interface IBoardService {
List<BoardDTO> getBoardList();
void insertBoard(BoardDTO theBoard);
BoardDTO getBoardById(Integer board_id);
void updateBoard(BoardDTO theBoard);
void deleteBoardById(Integer board_id);

}
