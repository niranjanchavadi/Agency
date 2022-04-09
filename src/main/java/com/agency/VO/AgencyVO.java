package com.agency.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgencyVO {
    private String agencyName;
    private String clientName;
    private Long totalBill;
}
