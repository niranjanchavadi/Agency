package com.agency.service;


import com.agency.VO.AgencyVO;
import com.agency.entitty.Agency;
import com.agency.entitty.AppUserRole;
import com.agency.entitty.Client;
import com.agency.entitty.Customer;
import com.agency.repository.AgencyRepository;
import com.agency.repository.ClientRepository;
import com.agency.repository.CustomerRepository;
import com.agency.security.JwtTokenProvider;
import com.agency.util.AppStringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.NoSuchElementException;

@Service
public class AgencyServiceImpl implements IAgencyService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AppStringUtil appStringUtil;

    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Override
    public String login(String username, String password, AppUserRole appUserRole) {
        Query query = new Query(Criteria.where("appUserRoles").is(appUserRole));
        Customer customer = mongoOperations.findOne(query, Customer.class);
        if (customer != null){
            String token=jwtTokenProvider.createToken(username, appUserRole);
            Update update = new Update();
            update.set("userName", username).set("password",password).set("token",token);
            mongoTemplate.updateFirst(query, update, Customer.class);
            return token;
        }else {
            Customer customer1 = new Customer();
            customer1.setUserName(username);
            customer1.setPassword(password);
            String token=jwtTokenProvider.createToken(username, appUserRole);
            customer1.setToken(token);
            customer1.setAppUserRoles(appUserRole);
            customerRepository.save(customer);
            return token;
        }
    }


    @Override
    public Boolean create(Agency agency) {
        if (agency != null) {
            Agency agency1 = new Agency();
            BeanUtils.copyProperties(agency, agency1);
            for (Client client : agency1.getClients()) {
                mongoOperations.save(client);
            }
            mongoOperations.save(agency1);
            return true;
        }
        return false;
    }


    @Override
    public Boolean update(String id, Client client) throws Throwable {
        Client client1 = mongoOperations.findById(id, client.getClass());
        if (client1 != null) {
            Query query = new Query(Criteria.where("_id").is(id));
            Update update = new Update();
            update.set("name", client.getName()).set("email",client.getEmail()).set("phoneNumber",client.getPhoneNumber()).set("totalBill",client.getTotalBill());
             mongoTemplate.updateFirst(query, update, Client.class);
            return true;
        }
        return false;
    }

    @Override
    public AgencyVO fetchByTotalBill(String id) {
        Agency agency = mongoOperations.findById(id, Agency.class);
        Client client = agency.getClients().stream().max(Comparator.comparingLong(Client::getTotalBill)).orElseThrow(NoSuchElementException::new);;
        AgencyVO agencyVO = new AgencyVO();
        agencyVO.setAgencyName(agency.getName());
        agencyVO.setClientName(client.getName());
        agencyVO.setTotalBill(client.getTotalBill());
        return agencyVO;
    }
}
