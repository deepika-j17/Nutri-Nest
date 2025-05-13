pipeline {
    agent any

    environment {
        IMAGE_NAME = "nutrinest"
        DOCKER_REGISTRY = "deepikajayaraman" 
        KUBE_DEPLOYMENT = "nutrinest-deployment"
        KUBE_NAMESPACE = "default"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/deepika-j17/Nutri-Nest.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t $DOCKER_REGISTRY/$IMAGE_NAME ."
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                    sh "docker push $DOCKER_REGISTRY/$IMAGE_NAME"
                }
            }
        }

        stage('Kubernetes Deploy') {
            steps {
                sh "kubectl set image deployment/$KUBE_DEPLOYMENT $IMAGE_NAME=$DOCKER_REGISTRY/$IMAGE_NAME --namespace=$KUBE_NAMESPACE"
            }
        }
    }
}
