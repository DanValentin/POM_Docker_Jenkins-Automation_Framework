pipeline {
    // Jenkins master executor trebuie sa fie setat pe "0"
    agent any
    stages {
        stage('Build Jar') {
            steps {
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                bat "docker build -t=danpopa86/selenium-docker ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub2', passwordVariable: 'pass', usernameVariable: 'user')]) {
			       // bat "docker login --username=${user} --password=${pass}"
			        bat "docker push danpopa86/selenium-docker:latest"
			    }
            }
        }
    }
}