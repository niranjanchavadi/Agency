package com.agency.repository;


import com.agency.entitty.Agency;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgencyRepository extends MongoRepository<Agency, String> {
}
