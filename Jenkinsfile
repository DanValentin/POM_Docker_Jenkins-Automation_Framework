pipeline {
    // Jenkins master executor trebuie sa fie setat pe "0"
    agent any
    stages {
        stage('Build Jar') {
            steps {
                //aici executam comanda maven care ne construieste fisierele de tip .jar ale proiectului nostru
                //folosim "bat" daca rulam pe o masina cu windows, daca rulam pe o masina cu linux folosim "sh"
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                //aici executam comanda docker care ce construieste imaginea docker definita in fisierul "Dockerfile"
                //folosim "bat" daca rulam pe o masina cu windows, daca rulam pe o masina cu linux folosim "sh"
                bat "docker build -t=danpopa86/selenium-docker ."
            }
        }
        stage('Push Image') {
            steps {
                //aici specificam ce credentiale sa foloseaca pentru a face push la imaginea nou creata in "hub.docker.com" - credentialele sunt definite in prealabil in Jenkins
			    withCredentials([usernamePassword(credentialsId: 'dockerhub2', passwordVariable: 'pass', usernameVariable: 'user')]) {
			        //aici executam comanda docker in care imaginea nou construita este urcata in "hub.docker.com" cu tag-ul "latest"
			        //folosim "bat" daca rulam pe o masina cu windows, daca rulam pe o masina cu linux folosim "sh"
			        bat "docker push danpopa86/selenium-docker:latest"
			    }
            }
        }
    }
}