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
		    	     
		    	     retry(10) {
			    	      	timeout(time: 15, unit: 'SECONDS') { // Just in case something goes wrong, pipeline will be killed after a timeout
						    	// some block
    									def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
								    	if (qg.status != 'OK') {
								      		error "Pipeline aborted due to quality gate failure: ${qg.status}"
								   		 }
								   		 
						    	} 
						    	sleep 15 
							}
		      	 }
			}
        }
        stage('Deploy') {
        	
            steps {
                echo 'Deploying....'
               docker.build("siddhirajpantoji/testrest")
                //	bat 'mvnw dockerfile:build'
                //	bat 'mvn dockerfile:push'
            }
        }
    }
}