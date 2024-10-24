pipeline {
    stage('Bill Account Id') {
        // Prompt the user for a text input
        def billAccountId = input(
                id: 'billAccountId',
                message: 'Please enter your text:',
                parameters: [
                        string(name: 'billAccountId', defaultValue: '', description: 'Enter BillAccount ID')
                ]
        )

        // Use the input value
        echo "You entered: ${billAccountId}"
    }

    stage('Do Something with Input 1') {
        steps{
            script{
                sh "mvn clean test -Dtype="${billAccountId}
            }
        }
    }
}
