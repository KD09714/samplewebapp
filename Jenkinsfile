pipeline {
    agent none
	options { skipDefaultCheckout(true) }
		
    stages {
        stage('General SCM') {
			git 'https://github.com/KD09714/samplewebapp.git'
		}
		
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
		
		stage ('Approval for SIT Deploy'){
			agent none
			steps {
				input 'Approve for SIT'
			}
		}
		
		stage ('Deploy Apps to SIT Env'){
			agent { label 'master'}
			steps {
				bat "ant -f deploy.xml -Denv=SIT -Dbuildnum=${BUILD_NUMBER}"
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