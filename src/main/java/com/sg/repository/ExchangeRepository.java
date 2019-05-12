package com.sg.repository;

import com.sg.model.Exchange;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends MongoRepository<Exchange, String> {
    Exchange findBySymbol(String symbol);
}