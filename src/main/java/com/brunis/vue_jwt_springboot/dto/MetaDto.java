package com.brunis.vue_jwt_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetaDto {
    private String title;
    private String description;
    private String cover;
}
