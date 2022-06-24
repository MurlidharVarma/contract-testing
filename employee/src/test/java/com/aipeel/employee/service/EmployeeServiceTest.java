package com.aipeel.employee.service;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.aipeel.employee.entity.Employee;
import com.aipeel.employee.repository.EmployeeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "projectProvider")
@ActiveProfiles("test")
public class EmployeeServiceTest {

    @Mock
    EmployeeRepo repo;

    @InjectMocks
    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);

        Employee emp = new Employee("Murli");
        emp.setId(2L);
        when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(emp));

    }

    @Pact(consumer = "employeeConsumer")
    public RequestResponsePact getAssociatedProjectsConfig (PactDslWithProvider builder){
        return builder.given("Employee has atleast one project")
                .uponReceiving("Request for Employee Details")
                .path("/project/employee/2")
                .willRespondWith()
                .status(200)
                .body(PactDslJsonArray.arrayMinLike(2)
                        .stringType("projectName")
                        .numberType("id")
                        .numberType("employeeId")
                        .numberType("size", 2)
                        .closeObject())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getAssociatedProjectsConfig", port = "9999", pactVersion = PactSpecVersion.V3)
    void getAssociateProjectsOfEmpTest(MockServer mockServer) throws IOException {
        Optional<Employee> employee = employeeService.getEmployeeWithProjects(2L);
        Assertions.assertEquals(2, employee.get().getProjects().length);
    }
}
