pipeline {
    agent any
    environment {
        // Specify the JDK version to be used for the pipeline
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk'  // For Linux-based Jenkins agents
        // For Windows-based agents, you might use something like:
        // JAVA_HOME = 'C:\\Program Files\\AdoptOpenJDK\\jdk-17\\'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/aymelari/superMarket.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                // Use the Gradle wrapper to ensure the correct Gradle version is used
                sh './gradlew clean build'  // Or use your build tool (like Maven)
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
