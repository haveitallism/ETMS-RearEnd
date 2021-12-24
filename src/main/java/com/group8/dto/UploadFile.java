package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author acoffee
 * @create 2021-12-21 18:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFile {
    int id;
    MultipartFile file;
}
