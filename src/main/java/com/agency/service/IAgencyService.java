package com.agency.service;


import com.agency.VO.AgencyVO;
import com.agency.entitty.Agency;
import com.agency.entitty.AppUserRole;
import com.agency.entitty.Client;

public interface IAgencyService {

    Boolean create(Agency agency) throws Throwable;
    Boolean update(String id , Client client) throws Throwable;
    AgencyVO fetchByTotalBill(String id)throws Throwable;
    String login(String username, String password, AppUserRole appUserRole);

}
