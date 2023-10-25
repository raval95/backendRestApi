package com.recruitment.rest;

import com.recruitment.rest.exception.ResourceNotFoundException;
import com.recruitment.rest.model.RequestsCount;
import com.recruitment.rest.repository.RequestsCountRepository;
import com.recruitment.rest.service.RequestsCountService;
import com.recruitment.rest.service.RequestsCountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 *
 * This tests requires:
 * * mongodb instance running on the environment
 *
 */
@RunWith(SpringRunner.class)
public class RequestsCountServiceTest {

    @TestConfiguration
    static class ServiceTest {
        @Bean
        public RequestsCountService requestsCountService() {
            return new RequestsCountServiceImpl();
        }
    }

    @Autowired
    private RequestsCountService requestsCountService;

    @MockBean
    private MongoTemplate mongoTemplate;

    @MockBean
    private RequestsCountRepository requestsCountRepository;

    private RequestsCount ragcrix;
    private RequestsCount yigit;
    private RequestsCount john;
    private RequestsCount ringo;

    @Before
    public void setup() {
        if (!mongoTemplate.collectionExists(RequestsCount.class)) {
            mongoTemplate.createCollection(RequestsCount.class);
        }
        ragcrix = new RequestsCount();
        ragcrix.setLogin("aBc123");
        ragcrix.setRequest_count(5);

        yigit = new RequestsCount();
        yigit.setLogin("dEf345");
        yigit.setRequest_count(1);

        john = new RequestsCount();
        john.setLogin("John");
        john.setRequest_count(30);

        ringo = new RequestsCount();
        ringo.setLogin("Ringo");
        ringo.setRequest_count(35);

        Mockito.when(requestsCountRepository.save(ragcrix)).thenReturn(ragcrix);
    }

    @Test
    public void createRequestCount() {
        RequestsCount foundRequestsCount = requestsCountService.createRequestsCount(ragcrix);
        assertThat(foundRequestsCount.getRequest_count(), is(ragcrix.getRequest_count()));
        assertThat(foundRequestsCount.getLogin(), is(ragcrix.getLogin()));
    }

    @Test
    public void getRequestCount() throws ResourceNotFoundException {
        String login = "newUser";
        //user dont exist in DB
        RequestsCount found = requestsCountService.getRequestsCountById(login);
        //expect  user will by created after get service
        assertNotNull(found);
        assertThat(found.getLogin(), is(login));
        assertThat(found.getRequest_count(), is(0));
    }

    @Test
    public void updateRequestCount() throws ResourceNotFoundException {
        if (mongoTemplate.collectionExists(RequestsCount.class)) {
            //start requestCount value is 30
            mongoTemplate.save(new RequestsCount(john.getLogin(), john.getRequest_count()));
            requestsCountService.updateRequestsCount(new RequestsCount(john.getLogin(), 40));
            //expect  requestCount value will be 40 after update
            Query query = new Query().addCriteria(Criteria.where("login").is(john.getLogin()));
            List<RequestsCount> users = mongoTemplate.find(query, RequestsCount.class);
            assertThat(users.get(0).getLogin(), is(john.getLogin()));
            assertThat(users.get(0).getRequest_count(), is(40));
        }
    }

    @Test
    public void searchQueryByLogin() {
        if (mongoTemplate.collectionExists(RequestsCount.class)) {
            mongoTemplate.save(john);
            mongoTemplate.save(ringo);
            Query query = new Query().addCriteria(Criteria.where("login").is(john.getLogin()));
            List<RequestsCount> users = mongoTemplate.find(query, RequestsCount.class);
            assertThat(users.size(), is(1));
            assertThat(users.get(0).getLogin(), is(john.getLogin()));
            assertThat(users.get(0).getRequest_count(), is(john.getRequest_count()));
        }
    }
}