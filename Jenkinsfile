pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
              checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CleanBeforeCheckout']], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/siddhirajpantoji/JavaPOC.git']]])
               //  bat 'mvn clean  package -DskipTests' //For Windows
               // Compilation of code  
               sh 'mvn clean  package -DskipTests'
            }
        }
        stage('Test') {
            steps {
            // Firing Test cases written 
          	  sh 'mvn clean  verify '
            }
        }
        stage('CodeCheck') {
            steps {
            		withSonarQubeEnv('SonarQube') {
						sh 'mvn sonar:sonar'
						
					   }
		    	     script {
		    	     // Max Retry counts =20 
		    	     retry(20) {
		    	     		// Waiting for 15 Seconds Before Proceeding 
		    	     		sleep 15
			    	      	timeout(time: 10, unit: 'SECONDS') { // Just in case something goes wrong, pipeline will be killed after a timeout
    									def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
								    	if (qg.status != 'OK') {
								      		error "Pipeline aborted due to quality gate failure: ${qg.status}"
								   		 }
								   		 
						    	} 
						    	 
							}
		      	 }
			}
        }
        stage('DockerImage') {	
            steps {
                echo 'Building Docker Image '
                script{
                 	def app
               		sh 'whoami'
               		// Image name must be dockerhub username/repository name 
                	app = docker.build("siddhirajpantoji/javapoc")
                	 docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
          				 app.push("${env.BUILD_NUMBER}")
          				  app.push("latest")
        			}
                }
			 }
        }
    }
}