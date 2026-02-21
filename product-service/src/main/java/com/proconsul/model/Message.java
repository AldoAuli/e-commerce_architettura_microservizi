package com.proconsul.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    private LocalDate timestamp;

    private String message;

    private Integer status;


}
