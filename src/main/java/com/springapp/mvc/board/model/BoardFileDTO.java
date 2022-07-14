package com.springapp.mvc.board.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardFileDTO {
    private int f_no; //파일번호
    private int board_id; //게시판 번호와 동기화
    private String file_name;  //저장할 때
    private String fileoriginname;  //받아올 때 파일 이름
    private String fileurl;   //저장 및 불러올 경로

    public int getF_no() {
        return f_no;
    }

    public void setF_no(int f_no) {
        this.f_no = f_no;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFileoriginname() {
        return fileoriginname;
    }

    public void setFileoriginname(String fileoriginname) {
        this.fileoriginname = fileoriginname;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
