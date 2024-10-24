node {
    stage('User Input') {
        // Prompt the user for a text input
        def userInput = input(
                id: 'userInput',
                message: 'Please enter your text:',
                parameters: [
                        string(name: 'inputText', defaultValue: '', description: 'Enter your text here')
                ]
        )

        // Use the input value
        echo "You entered: ${userInput}"
    }

    stage('Do Something with Input 1') {
        // Further steps that use the input
        echo "Running next stage with user input."
    }
    stage('Do Something with Input 2') {
        // Further steps that use the input
        echo "Running next stage with user input."
    }
    stage('Do Something with Input 3') {
        // Further steps that use the input
        echo "Running next stage with user input."
    }
    stage('Do Something with Input 4') {
        // Further steps that use the input
        echo "Running next stage with user input."
    }
}
