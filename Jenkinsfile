pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
              checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CleanBeforeCheckout']], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/siddhirajpantoji/JavaPOC.git']]])
               bat 'mvn package '
               
            }
        }
        stage('Test') {
            steps {
            	  script {
		            def qg = waitForQualityGate()
		            if (qg.status != 'OK') {
		              error "Pipeline aborted due to quality gate failure: ${qg.status}"
		            }
		          } 
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}