pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
              sh 'mvn package' 
            }
        }
        stage('Test') {
            steps {
                sh 'mvn verify '
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}