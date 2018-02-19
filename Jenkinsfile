pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
              checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CleanBeforeCheckout']], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/siddhirajpantoji/JavaPOC.git']]])
               bat 'mvn clean  package -DskipTests'
            }
        }
        stage('Test') {
            steps {
          	  bat 'mvn clean  verify '
            }
        }
        stage('CodeCheck') {
            steps {
            		withSonarQubeEnv('SonarQube') {
						bat 'mvn sonar:sonar'
						
					   }
		    	     script {
		    	     
		    	     retry(20) {
			    	      	timeout(time: 10, unit: 'SECONDS') { // Just in case something goes wrong, pipeline will be killed after a timeout
						    	// some block
    									def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
								    	if (qg.status != 'OK') {
								      		error "Pipeline aborted due to quality gate failure: ${qg.status}"
								   		 }
								   		 
						    	} 
						    	//sleep 15 
							}
		      	 }
			}
        }
        stage('Deploy') {
        	
        	agent {
                docker { image 'maven:3-jdk-8-alpine' }
            }
            steps {
                bat 'mvn --version'
            }
            steps {
                echo 'Building Docker Image '
               // script{
               // 	bat 'mkdir tmp'
               // 	docker.build("siddhirajpantoji/testrest")
                //}
            }
        }
    }
}