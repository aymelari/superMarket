pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/aymelari/superMarket.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                sh './gradlew build'  // Or use your build tool (like Maven)
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'  // Or use your test tool (like Maven)
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the application'
            }
        }
    }
}
