pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
              checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CleanBeforeCheckout']], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/siddhirajpantoji/JavaPOC.git']]])
               //  bat 'mvn clean  package -DskipTests' //For Windows 
               sh 'mvn clean  package -DskipTests'
            }
        }
        stage('Test') {
            steps {
          	  sh 'mvn clean  verify '
            }
        }
        stage('CodeCheck') {
            steps {
            		withSonarQubeEnv('SonarQube') {
						sh 'mvn sonar:sonar'
						
					   }
		    	     script {
		    	     
		    	     retry(20) {
		    	     		sleep 15
			    	      	timeout(time: 10, unit: 'SECONDS') { // Just in case something goes wrong, pipeline will be killed after a timeout
						    	// some block
    									def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
								    	if (qg.status != 'OK') {
								      		error "Pipeline aborted due to quality gate failure: ${qg.status}"
								   		 }
								   		 
						    	} 
						    	 
							}
		      	 }
			}
        }
        stage('Deploy') {
        	
        	//agent {
           //     docker { image 'maven:3-jdk-8-alpine' }
          //  }
           // steps {
            //    sh 'mvn --version'
            //}
            steps {
                echo 'Building Docker Image '
                script{
                 	def app
               // 	bat 'mkdir tmp'
               		sh 'whoami'
                	app = docker.build("siddhirajpantoji/javapoc")
                	 docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
          				 // app.push("${env.BUILD_NUMBER}")
          				  app.push("latest")
        			}
                }
			 }
        }
    }
}