pipeline {
    agent any
     environment {
        MAVEN_OPTS = "-Dmaven.repo.local=.m2/repository"
    }
   
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/katsiaryne/BigNotes-UI-tests'
            }
        }

        stage('Build') {
            steps {
                echo 'Creating application.conf...'
                bat '''if not exist src\\test\\resources mkdir src\\test\\resources
                    echo url="https://qa.skillbox.ru/module15/bignotes/#/" > src/test/resources/application.conf'''
                bat 'mvn clean package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('UI Tests') {
            steps {
                echo 'Running UI tests in headless mode with Selenide...'
                bat 'mvn surefire:test -Pjenkins' 
            }
            post {
                always {
                    junit 'target/surefire-reports/TEST-*.xml'
                    archiveArtifacts artifacts: 'target/allure-results/**', allowEmptyArchive: true
                    allure([
                        includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed!'
        }
    }
}
