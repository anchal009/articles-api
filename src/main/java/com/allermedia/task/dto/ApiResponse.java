package com.allermedia.task.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private float httpStatus;
    Article response;
}