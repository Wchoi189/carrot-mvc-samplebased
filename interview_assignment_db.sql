CREATE TABLE `board` (
                         `board_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '게시글 ID',
                         `title` VARCHAR(15) NOT NULL COMMENT '게시글 제목을 저장하고 길이는 15자내로 제한합니다.',
                         `file_name` VARCHAR(100) NOT NULL COMMENT '파일의 이름만 저장하고 파일의 경로는 따로 저장합니다.',
                         `save_path` VARCHAR(100) NOT NULL COMMENT '파일이 저장된 경로를 저장합니다. 파일의 이름이 같이 저장되면 안됩니다.',
                         `reg_date` DATE NOT NULL COMMENT '게시글이 등록된 시간을 저장합니다.',
                         PRIMARY KEY (`board_id`) USING BTREE
)
    COLLATE='utf8_general_ci'
ENGINE=InnoDB
;