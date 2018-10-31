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
				bat 'ant -f deploy.xml'
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