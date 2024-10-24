pipeline {
    agent any  // This specifies that Jenkins can run the pipeline on any available agent

    tools {
        maven 'maven 399' // Use the name of the Maven version configured in Jenkins
    }
    stages{
        stage('Build') {
                    steps {
                        script {
                            def billAccountId = input message: 'Enter bill account id',
                                                 parameters: [string(defaultValue: '123456789',
                                                 description: 'Please enter the bill account id to run billing for',
                                                 name: 'BILL_ACC_ID')]

                            echo "Building version ${billAccountId}"
                            sh "mvn clean test -Dtype="${billAccountId}
                        }
                        // Add your build commands here, using the input version if needed
                        // Example: sh "mvn clean package -Dversion=${billAccountId}"
                    }
        };
    }
}