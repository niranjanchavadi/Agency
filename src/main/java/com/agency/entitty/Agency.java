package com.agency.entitty;

import com.agency.config.CascadeSave;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Agency.COLLECTION_NAME)
public class Agency implements Serializable {
    @Transient
    public static final String COLLECTION_NAME = "agency";

    @Id
    private String id;

    @NotBlank
    private String name;

    @Valid
    Address address1;

    @Valid
    Address address2;

    @DBRef(lazy = true)
    @CascadeSave
    @Valid
    private  List<Client> clients;

}
