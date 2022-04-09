package com.agency.controller;


import com.agency.VO.AgencyVO;
import com.agency.entitty.Agency;
import com.agency.entitty.Client;
import com.agency.security.JwtTokenProvider;
import com.agency.service.IAgencyService;
import com.agency.response.ApiResponse;
import com.agency.util.AppStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/agents")
@RestController
public class AgentController {

    @Autowired
    private IAgencyService agencyService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

//create the Agency and clients
@PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody  Agency agency ,BindingResult result)  throws Throwable {
        if (result.hasErrors()) {
            return ResponseEntity.ok(new ApiResponse( AppStringUtil.VALIDATION_FAILURE_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.value(),result.getAllErrors()));

        }
        else{
            Boolean isSaved = agencyService.create(agency);
            if (isSaved){
                return ResponseEntity.ok(new ApiResponse( AppStringUtil.SUCCESS_RESPONSE, HttpStatus.OK.value()));
            } else {
                return ResponseEntity.ok(new ApiResponse(  AppStringUtil.FAILURE_RESPONSE, HttpStatus.NOT_FOUND.value()));
            }
        }
    }


    //update client  based on id of client and update Object
    @PutMapping("/update/{id}")
    public ResponseEntity<?> Update(@PathVariable("id") String id, @Valid @RequestBody Client client , BindingResult result)  throws Throwable {
        if (result.hasErrors()) {
            return ResponseEntity.ok(new ApiResponse( AppStringUtil.VALIDATION_FAILURE_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR.value(),result.getAllErrors()));
        }
        else{
            Boolean isSaved = agencyService.update(id,client);
            if (isSaved){
                return ResponseEntity.ok(new ApiResponse( AppStringUtil.UPDATE_SUCCESS_RESPONSE, HttpStatus.OK.value()));
            } else {
                return ResponseEntity.ok(new ApiResponse(  AppStringUtil.FAILURE_RESPONSE, HttpStatus.NOT_FOUND.value()));
            }
        }
    }

    //fetch Max total bill,agentName,ClientName based on id of agent
    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> getTotalBill(@PathVariable("id") String id )  throws Throwable {
            AgencyVO agency = agencyService.fetchByTotalBill(id);
            if (agency != null){
                return ResponseEntity.ok(new ApiResponse( AppStringUtil.FIND_SUCCESS_RESPONSE, HttpStatus.OK.value(),agency));
            } else {
                return ResponseEntity.ok(new ApiResponse(  AppStringUtil.FIND_FAILURE_RESPONSE, HttpStatus.NOT_FOUND.value()));
            }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
