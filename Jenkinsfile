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
                script {
                    if (fileExists('target/cucumber.json')) {
                        cucumber buildStatus: 'UNCHANGED',
                                 fileIncludePattern: 'target/cucumber.json',
                                 sortingMethod: 'ALPHABETICAL'
                    } else {
                        echo 'No cucumber.json file found'
                    }
                }
            }
        }

        stage('Archive JARs') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Publish Cucumber HTML Report') {
            steps {
                script {
                    if (fileExists('target/cucumber-report.json')) {
                        publishHTML(target: [
                            reportName: 'Cucumber Report',
                            reportDir: 'target',
                            reportFiles: 'cucumber-report.json'
                        ])
                    } else {
                        echo 'Cucumber report not found.'
                    }
                }
            }
        }
    }
}
