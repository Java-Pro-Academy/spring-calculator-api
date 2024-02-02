pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Clean and compile the project
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                // Run unit tests
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            // Always run this block after the stages
            echo 'Build and Test Stages Completed'
        }
        success {
            // Actions to take if the pipeline succeeds
            echo 'Build and Test Succeeded'
        }
        failure {
            // Actions to take if the pipeline fails
            echo 'Build or Test Failed'
        }
    }
}
