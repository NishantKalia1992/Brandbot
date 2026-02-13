pipeline{
	agent any
	tools{
		maven 'maven-3.9.9'
		jdk 'jdk-17'
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
