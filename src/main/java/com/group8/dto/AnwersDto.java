package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description: anwers <br>
 * date: 2021/12/18 12:53 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnwersDto {
    private List<ceshiDto> answers;
    private String topic;
}
