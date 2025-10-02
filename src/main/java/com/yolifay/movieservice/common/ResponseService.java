package com.yolifay.movieservice.common;

import lombok.Data;

@Data
public class ResponseService {
    private String responseCode;
    private String responseDesc;
    private Object data;
}
