pipeline {
    agent any

    environment {
        PROJECT_ID = 'playground-s-11-442a953c'
        CLUSTER_NAME = 'java-jenkins'
        CLUSTER_ZONE = 'us-central1'
        IMAGE_NAME = "gcr.io/${env.PROJECT_ID}/bookstore"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
               url:'https://github.com/keerthana-balu-sada/bookstore.git'
            }
        }

        stage('Build with Maven') {
            steps {
                script {
                    // Use Maven to clean and package the application
                    sh 'mvn clean package'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Run unit tests
                    sh 'mvn test'
                }
            }
        }

        stage('Docker Build and Push') {
            steps {
                script {
                    // Build Docker image
                    sh "docker build -t ${env.IMAGE_NAME}:${env.BUILD_NUMBER} ."

                    // Authenticate with GCR and push the image
                    sh "gcloud auth configure-docker"
                    sh "docker push ${env.IMAGE_NAME}:${env.BUILD_NUMBER}"
                }
            }
        }

        stage('Deploy to GKE') {
            steps {
                script {
                    // Get GKE credentials
                    sh "gcloud container clusters get-credentials ${CLUSTER_NAME} --zone ${CLUSTER_ZONE} --project ${PROJECT_ID}"

                    // Deploy the Docker image to Kubernetes
                    sh "kubectl set image deployment/bookstore bookstore=${IMAGE_NAME}:${env.BUILD_NUMBER} --record"
                }
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
       
