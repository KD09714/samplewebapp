pipeline {
    agent none
    
    stages {
        stage ('Build'){
            agent { label "master"}
            steps {
				withMaven (maven : 'maven_3.5.4'){
					bat 'mvn clean install'
				}
            }
        }
		
		stage ('Archive'){
            agent { label "master"}
            steps {
				bat "ant -f archive.xml -Dbuildnum=${BUILD_NUMBER}"
            }
        }
		
		stage ('Deploy Apps to QA'){
			agent { label 'master'}
			steps {
				bat "ant -f deploy.xml -Dbuildnum=${BUILD_NUMBER}"
			}
		}
		
		stage ('Test'){
			agent { label "master"}
			steps { 
				withMaven (maven : 'maven_3.5.4'){
					bat 'mvn test -DskipTests=false'
				}
			}
		}
		
		stage ('Approve for Prod'){
			agent none
			steps {
				input 'Approve for Prod'
			}
		}
		
		stage ('Deploy Apps to Production'){
			agent { label 'master'}
			steps {
				bat "ant -f deploy.xml -Denv=PROD -Dbuildnum=${BUILD_NUMBER}"
			}
		}
    }
	
	post {
	success {
		echo 'This is Success Case'
	}
	
	failure {
		echo 'This is Failure Case'
	}
	}
}