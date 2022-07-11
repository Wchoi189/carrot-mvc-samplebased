package com.springapp.mvc.service;


import com.springapp.mvc.model.BoardDTO;

import java.util.List;


public interface IBoardService {
List<BoardDTO> getBoardList();
boolean insertBoard(BoardDTO theBoard);
BoardDTO getBoardById(int board_id);
void updateBoard(BoardDTO theBoard);
void deleteBoardById(int board_id);

}