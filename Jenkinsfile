pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Debug Workspace') {
            steps {
                sh 'pwd && ls -la'
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn -B clean compile install'
            }
        }
        stage('Generate Cucumber Report') {
            steps {
                cucumber buildStatus: 'UNCHANGED',
                         fileIncludePattern: 'target/cucumber.json',
                         sortingMethod: 'ALPHABETICAL'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
    post {
        always {
            echo 'Pipeline execution complete.'
            sh 'ls -la target'
        }
        failure {
            echo 'Pipeline failed. Debugging...'
            sh 'ls -la'
        }
    }
}
