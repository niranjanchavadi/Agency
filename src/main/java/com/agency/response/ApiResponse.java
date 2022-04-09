package com.agency.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private String message;
    private Object object;
    private Long totalCount;
    private int statuscode;


    public ApiResponse( String message) {
        this.message = message;
    }

    public ApiResponse( String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public ApiResponse(String message, int statuscode) {
        this.message = message;
        this.statuscode = statuscode;

    }

    public ApiResponse(String message, int statuscode,Object object) {
        this.message = message;
        this.statuscode = statuscode;
        this.object = object;


    }


    public ApiResponse(String message, Object object, String status, int statuscode) {
        this.message = message;
        this.object = object;
        this.statuscode = statuscode;
    }


}
