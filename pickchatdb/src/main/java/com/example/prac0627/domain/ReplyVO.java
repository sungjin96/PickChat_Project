package com.example.prac0627.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReplyVO {
    private int rno;
    private int bno;
    private String writer;
    private String content;
    private Date regdate;
    private Date updatedate; 

}