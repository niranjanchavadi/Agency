package com.agency.entitty;

import com.crowdlending.enums.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Customer.COLLECTION_NAME)
public class Customer {
    @Transient
    public static final String COLLECTION_NAME = "customer";
    @Id
    private String id;
    private String userName;
    private String password;
    AppUserRole appUserRoles;
    private String token;
}
