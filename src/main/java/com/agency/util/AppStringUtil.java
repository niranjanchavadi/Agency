package com.agency.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Component
public class AppStringUtil {
    public static final String FAILURE_RESPONSE = "FAILURE";
    public static final String SUCCESS_RESPONSE = "SUCCESS";
    public static final String VALIDATION_FAILURE_RESPONSE = "VALIDATION FAILURE";
    public static final String UPDATE_SUCCESS_RESPONSE = "UPDATE SUCCESS";
    public static final String FIND_SUCCESS_RESPONSE = "DATA IS PRESENT";
    public static final String FIND_FAILURE_RESPONSE = "NO DATA IS PRESENT";


}
