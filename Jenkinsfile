/*pipeline{
	agent any
	tools{
		maven 'Maven_3_9_9'
		jdk 'jdk-21'
	}
	stages{
		stage('Checkout'){
			steps{
                checkout scm
			}
		}
        stage('Build JAR'){
            steps{
				echo 'Building the JAR file...'
				echo 'Using Maven to clean and package the application...'
                bat 'mvn clean package'
            }
        }
        stage('Deploy(Docker Compose)'){
            steps{
				echo 'Deploying the application using Docker Compose...'
				script {
					                    // Check if the Docker Compose file exists
                    if (fileExists('docker-compose.yml')) {
                        echo 'Docker Compose file found. Deploying...'
                        // Run Docker Compose to deploy the application
                        bat 'docker-compose up -d'
                    } else {
                        error 'Docker Compose file not found. Please ensure docker-compose.yml is in the repository.'
                    }
				}
            }
        }
    }
  }
*/
pipeline {
    agent any

    tools {
        // These match the names you fixed in your settings
        maven 'Maven_3_9_9'
        jdk 'jdk-21'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                echo '--- Compiling and Packaging ---'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build & Push to Docker Hub') {
            steps {
                script {
                    echo '--- Step 1: Login to Docker Hub ---'
                    // This line unlocks the "Safe" inside Jenkins where you stored your password.
                    // 'docker-hub-creds' MUST match the ID you typed in the Jenkins menu.
                    withCredentials([usernamePassword(credentialsId: 'd52af70c-fee1-4cc1-93ed-5682570026b9', passwordVariable: 'MY_PASS', usernameVariable: 'MY_USER')]) {
                        
                        // %MY_USER% and %MY_PASS% are placeholders. 
                        // Jenkins injects your real username (nishantkalia13) and password safely here.
                        bat 'docker login -u %MY_USER% -p %MY_PASS%'
                        
                        echo '--- Step 2: Build the Image ---'
                        // We tag the image with your Docker Hub username so Docker knows where to upload it.
                        // Format: username/repository-name:tag
                        bat 'docker build -t nishantkalia13/brandbot:latest .'
                        
                        echo '--- Step 3: Push (Upload) ---'
                        // This sends the files from your laptop to the Docker Hub website.
                        bat 'docker push nishantkalia13/brandbot:latest'
                    }
                }
            }
        }
    }
}