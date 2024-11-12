def scenario = "NONE";
def testEnv = "NONE";
def cucumberTags = "NONE";
def testParams = "NONE";

pipeline {
    agent any  // This specifies that Jenkins can run the pipeline on any available agent

    tools {
        maven 'maven 399' // Use the name of the Maven version configured in Jenkins
    }

    stages {
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
        }
        stage('Set Test Parameters') {
            steps {
                script {
                    switch (scenario) {
                        case "Bill Run":
                            def billRunInputs = input(
                                    id: 'UserInputs', message: 'Provide the following inputs', parameters: [
                                    string(name: 'BILL_ACC_ID', defaultValue: '', description: 'bill account id'),
                                    string(name: 'BILLING_PERIOD', defaultValue: '', description: 'Billing period YYYYMM'),
                                    string(name: 'MESSAGE', defaultValue: '', description: 'Bill run message')
                            ]
                            )
                            cucumberTags = '-Dcucumber.filter.tags=@BillRun'
                            testParams =
                                    "-DbillAccountId=${billRunInputs['BILL_ACC_ID']}" +
                                    " -DbillPeriod=${billRunInputs['BILLING_PERIOD']}" +
                                    " -Dmessage=${billRunInputs['MESSAGE']}"
                            break
                        case "Payex Files":
                            def billRunId = input message: 'Enter bill run id',
                                    parameters: [string(defaultValue: 'XXXXXXXXX',
                                            description: 'Please enter the bill run id to generate files for',
                                            name: 'BILL_RUN_ID')]
                            cucumberTags = '-Dcucumber.filter.tags=@PayexFiles'
                            testParams = "-DbillRunId=${billRunId}"
                            break
                        default:
                            echo 'Selected scenario is: ' + scenario
                            error "No scenario selected or unknown scenario type issued"
                    }
                }
            }
        }
        stage('Select Env') {
            steps {
                script {
                    def envValue = input message: 'SELECT ENV',
                            parameters: [choice(choices: ['DEV', 'SIT', 'SAT'],
                                    description: 'Select your env',
                                    name: 'ENV_VALUE')]
                    testEnv = " -Denv=" + envValue;
                }
            }
        }
    }
}