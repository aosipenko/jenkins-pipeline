pipeline {
    agent any  // This specifies that Jenkins can run the pipeline on any available agent

    tools {
        maven 'maven 399' // Use the name of the Maven version configured in Jenkins
    }
    stages{
        stage('Build') {
                    steps {
                        script {
                            def userInput = input message: 'Enter the build version:',
                                                 parameters: [string(defaultValue: '1.0.0',
                                                 description: 'Please enter the version number for this build.',
                                                 name: 'BUILD_VERSION')]

                            echo "Building version ${userInput}"
                        }
                        // Add your build commands here, using the input version if needed
                        // Example: sh "mvn clean package -Dversion=${userInput}"
                    }
                }

        stage('Do Something with Input 1') {
            steps{
                script{
                    sh "mvn clean test -Dtype="${billAccountId}
                }
            }
        }
    }
}