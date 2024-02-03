pipeline {
    agent any

    tools {
        // Ensure Jenkins has Maven and JDK configured appropriately
        maven 'Maven3'
        //jdk 'Java17' // Uncomment and configure if your Jenkins requires specifying JDK version
    }

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

        stage('Build and Push Docker Image') {
            steps {
                // Assuming Dockerfile is present in the project root directory
                script {
                    def dockerImageName = "your_image_name_here"
                    def dockerImageTag = "latest" // or use ${env.BUILD_ID} for dynamic tagging
                    // Building the Docker image
                    sh "docker build -t ${dockerImageName}:${dockerImageTag} ."
                    // Pushing the Docker image to a registry
                    sh "docker push ${dockerImageName}:${dockerImageTag}"
                }
            }
        }

        stage('SonarQube Scanner') {
            steps {
                // Running SonarQube scanner, assuming SonarQube scanner is set up in Jenkins
                // and sonar-project.properties file is present in the project root
                sh 'mvn sonar:sonar'
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
