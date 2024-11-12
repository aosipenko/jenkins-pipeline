library 'stable'

/**
 Documentation for deliveryPipelineV2 can be found at:
 https://jira.se.telenor.net/wiki/display/PERF/Delivery+Pipeline+V2
 */

def getSecret(secretName) {
    def secret = ''
    // Access credentials using Jenkins built-in methods
    withCredentials([string(credentialsId: secretName, variable: 'SECRET')]) {
        secret = env.SECRET
    }
    return secret
}

def scenario = "NONE";
def testEnv = "NONE";
def cucumberTags = "NONE";
def testParams = "NONE";
def mavenBase = "mvn clean test -DTEST_DB_USER=" + getSecret('BILLFISH_DB_USER') +
        ' -DTEST_DB_PASSWORD=' + getSecret('BILLFISH_DB_PWD')

library 'stable'

pipeline {
    agent {
        label 'linux-medium'
    }
    options {
        ansiColor('xterm')
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 1, unit: 'HOURS')
    }
    environment {
        DOCKER_IMAGE = 'custom/maven-3.6.3_java-17.0.2_chrome'
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
                            echo 'Bill Run selected'
                            def billAccountId = input message: 'Enter bill account id',
                                    parameters: [string(defaultValue: '123456789',
                                            description: 'Please enter the bill account id to run billing for',
                                            name: 'BILL_ACC_ID')]
                            cucumberTags = '-Dcucumber.filter.tags=@BillRun'
                            testParams = "-DbillAccountId=${billAccountId}"
                            break
                        case "Payex Files":
                            echo 'Payex Selected selected'
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
        stage('Run Tests with Parameters') {
            agent {
                docker {
                    image env.DOCKER_IMAGE
                    reuseNode true
                }
            }
            steps {
                script {
                    sh "${mavenBase} ${cucumberTags} ${testParams} ${testEnv}"
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
        success {
            echo "Build succeeded!"
        }
        failure {
            echo "Build failed!"
        }
    }
}
