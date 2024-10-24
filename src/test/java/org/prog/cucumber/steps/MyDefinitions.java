package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyDefinitions {

    @Given("I print {string} {int} times")
    public void myGivenStep(String input, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(input);
        }
    }

    @When("I use different test data {string}")
    public void whenStep(String input) {
        System.out.println(input);
    }

    @Then("I print another test data piece {string}")
    public void assertStuff(String input) {
        System.out.println(input);
    }
}
