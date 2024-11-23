pipeline {
    agent any
    tools {
        maven 'maven' // Ensure the Maven tool is properly configured in Jenkins.
    }
    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs() // Clean the workspace to avoid residual files interfering.
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B clean compile' // Always clean before compiling.
            }
        }
        stage('Test') {
            steps {
                // Run tests and generate the Cucumber JSON report.
                sh 'mvn -B test -Dcucumber.options="--plugin json:target/cucumber.json"'
            }
        }
        stage('Verify Report') {
            steps {
                // Check if the cucumber.json file exists after the test stage.
                sh 'ls -l target/cucumber.json'
            }
        }
        stage('Publish Report') {
            steps {
                // Publish the Cucumber report to Jenkins.
                cucumber buildStatus: 'UNCHANGED',
                         fileIncludePattern: '**/cucumber.json',
                         sortingMethod: 'ALPHABETICAL'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true // Archive the built artifacts.
            }
        }
    }
}
