pipeline {
    agent any
    
    stages {
        stage ('Build'){
            agent { label "master"}
            steps {
				withMaven (maven : 'maven_3.5.4'){
					bat 'mvn clean install'
				}
            }
        }
		
		stage ('Deploy Apps'){
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
    }
}