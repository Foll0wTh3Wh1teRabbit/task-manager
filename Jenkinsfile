pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Foll0wTh3Wh1teRabbit/task-manager.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        steps('Deploy') {
            steps {
                sh """

                """
            }
        }
    }
    post {
        success {
            echo 'Build and Deploy succeeded!'
        }
        failure {
            echo 'Build or Deploy failed!'
        }
    }
}