pipeline {
    agent any

    environment {
        // You can set environment variables here if needed
        APP_NAME = "Delhi-Metro"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning repository from GitHub...'
                git branch: 'main', url: 'https://github.com/akashsubhransh12/Delhi-Metro.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                // Example: If your project uses Node.js, Maven, or Gradle — add relevant build commands
                // For example, if it’s a Node.js app:
                // sh 'npm install'
                // sh 'npm run build'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                // Add your test commands here
                // sh 'npm test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                // Example deployment command — adjust to your server setup
                // sh 'scp -r ./build user@server:/var/www/html/'
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed. Please check the logs.'
        }
    }
}
