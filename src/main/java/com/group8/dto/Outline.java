package com.group8.dto;

import com.group8.entity.EtmsCatalog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Outline {
    private List<EtmsCatalog> catalogs;
}
