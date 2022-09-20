package com.springapp.mvc.board.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class BoardDTO {
    private Integer board_id;
    @NotNull(message ="title cannot be empty")
    @Size(min = 1, max= 15, message = "내용은 15문자를 초과 할  수 없습니다.")
    private String title;
    @NotNull(message ="file must be uploaded")
    private String file_name;
    private String save_path;
  /*  @JsonFormat(pattern="yyyy.MM.dd", timezone="Asia/Seoul")*/
    private String reg_date;

    public MultipartFile getBoard_image() {
        return board_image;
    }

    public void setBoard_image(MultipartFile board_image) {
        this.board_image = board_image;
    }

    private MultipartFile board_image;




    @Override
    public String toString() {
        return "BoardDTO{" +
                "board_id='" + board_id + '\'' +
                ", title='" + title + '\'' +
                ", file_name='" + file_name + '\'' +
                ", save_path='" + save_path + '\'' +
                ", reg_date=" + reg_date +
                '}';
    }
}
