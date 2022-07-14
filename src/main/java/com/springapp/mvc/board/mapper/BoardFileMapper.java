package com.springapp.mvc.board.mapper;

import com.springapp.mvc.board.model.BoardFileDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Mapper
public interface BoardFileMapper {
    @Select("SELECT * FROM file")
    List<BoardFileDTO> getBoardFileList();

    @Select("SELECT * FROM file WHERE board_id = #{board_id}")
    BoardFileDTO getBoardFileById(int board_id);

    @Insert("INSERT INTO file (board_id, file_name, fileOriginalName, fileUrl) VALUES (#{board_id}, #{file_name}, #{fileOriginalName}, #{fileUrl})")
    @Options(useGeneratedKeys=true, keyProperty="f_no")
    void insertBoardFile(BoardFileDTO theBoardFile);

    @Update("UPDATE file SET file_name = #{file_name}, file_url = #{file_url} WHERE board_id = #{boardId}")
    void updateBoardFile(BoardFileDTO theBoardFile);

    @Delete("DELETE FROM file WHERE board_id = #{board id}")
    void deleteBoardFileById(int board_id);
}
