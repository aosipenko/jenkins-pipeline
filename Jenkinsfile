def scenario = "NONE";

pipeline {
    agent any  // This specifies that Jenkins can run the pipeline on any available agent

    tools {
        maven 'maven 399' // Use the name of the Maven version configured in Jenkins
    }

    stages{
        stage('Select Scenario') {
                    steps {
                        script {
                            def scenarioType = input message: 'Select bill account id',
                                                     parameters: [choice(choices: ['Bill Run', 'Payex Files'],
                                                     description: 'Please select the scenario you want to run',
                                                     name: 'BILL_SCENARIO')]
                            scenario = scenarioType;
                        }
                    }
        };
        stage('Scenario Parameters') {
            steps {
                script {
                    switch (scenario){
                        case "Bill Run":
                            echo 'Bill Run selected'
                            def billAccountId = input message: 'Enter bill account id',
                                                      parameters: [string(defaultValue: '123456789',
                                                      description: 'Please enter the bill account id to run billing for',
                                                      name: 'BILL_ACC_ID')]

                            echo "Building version ${billAccountId}"
                            sh "mvn clean test -Dtestng.suiteXmlFile=all-ng.xml"
                        break
                        case "Payex Files":
                            echo 'Payex Selected selected'
                            def billRunId = input message: 'Enter bill account id',
                                                      parameters: [string(defaultValue: '123456789',
                                                      description: 'Please enter the bill account id to run billing for',
                                                      name: 'BILL_ACC_ID')]

                            echo "Building version ${billRunId}"
                            sh "mvn clean test -Dtestng.suiteXmlFile=testng.xml -DbillRun=${billRunId}"
                        break
                        default:
                            echo 'Selected scenario is: ' + scenario
                            error "No scenario selected or unknown scenario type issued"
                    }
                }
            }
        };
    }
}