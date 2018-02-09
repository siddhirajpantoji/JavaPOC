pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
             cmd 'mvn package' 
            }
        }
        stage('Test') {
            steps {
               cmd 'mvn verify '
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}