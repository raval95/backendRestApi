package com.recruitment.rest.service;

import com.recruitment.rest.exception.ResourceNotFoundException;
import com.recruitment.rest.model.RequestsCount;
import com.recruitment.rest.repository.RequestsCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RequestsCountServiceImpl implements RequestsCountService {

    @Autowired
    private RequestsCountRepository requestsCountRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public RequestsCount createRequestsCount(RequestsCount requestsCount) {
        return requestsCountRepository.save(requestsCount);
    }

    @Override
    public void updateRequestsCount(RequestsCount requestsCount) throws ResourceNotFoundException {
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(requestsCount.getLogin()));
        RequestsCount requestsCountsDb = mongoTemplate.findOne(query, RequestsCount.class);
        if (requestsCountsDb != null) {
            Update update = new Update();
            update.set("request_count", requestsCount.getRequest_count());
            mongoTemplate.updateFirst(query, update, RequestsCount.class);
        } else {
            throw new ResourceNotFoundException("Record not found with login : " + requestsCount.getLogin());
        }
    }

    @Override
    public RequestsCount getRequestsCountById(String login) throws ResourceNotFoundException {
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(login));
        List<RequestsCount> requestsCounts = mongoTemplate.find(query, RequestsCount.class);
        if (requestsCounts.size() > 0) {
            return requestsCounts.get(0);
        } else {
            RequestsCount requestsCount = new RequestsCount(login, 0);
            createRequestsCount(requestsCount);
            return requestsCount;
        }
    }
}
