package com.agency.entitty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Client.COLLECTION_NAME)
public class Client {
    @Transient
    public static final String COLLECTION_NAME = "client";
    @Id
    private String id;
    private String name;

    @Pattern(regexp = "^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$", message = "Please provide valid emailAdress")
    private String email;
    @Pattern(regexp = "[7-9]{1}[0-9]{9}", message = "Please Enter Valid PhoneNumber")
    private String phoneNumber;
    private Long totalBill;
}
