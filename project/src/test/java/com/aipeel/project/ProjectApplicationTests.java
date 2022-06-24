package com.aipeel.project;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import com.aipeel.project.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("projectProvider")
@PactFolder("src/main/pacts")
class ProjectApplicationTests {
    @LocalServerPort
    int port;

    @Autowired
    ProjectService projectService;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTest(PactVerificationContext context){
        context.verifyInteraction();
    }

    @BeforeEach
    public void setup(PactVerificationContext context){
        context.setTarget(new HttpTestTarget("localhost",port));
    }

    @State(value = "Employee has atleast one project", action = StateChangeAction.SETUP)
    public void projectExistsSetup(){

    }

    @State(value = "Employee has atleast one project", action = StateChangeAction.TEARDOWN)
    public void projectExistsTearDown(){

    }

}
