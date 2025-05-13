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
                git branch: 'main', url: 'https://github.com/deepika-j17/Nutri-Nest.git'
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

        // ❌ This stage is commented out because Jenkins is not authenticated to Kubernetes.
        // ✅ You already applied the YAMLs manually. You can re-enable this once Jenkins can access the cluster.
        /*
        stage('Kubernetes Apply YAMLs') {
            steps {
                sh '''
                    kubectl apply -f k8s/nutrinest-deployment.yaml --validate=false
                    kubectl apply -f k8s/nutrinest-service.yaml --validate=false
                '''
            }
        }
        */

        stage('Kubernetes Deploy') {
            steps {
                sh "kubectl set image deployment/$KUBE_DEPLOYMENT $IMAGE_NAME=$DOCKER_REGISTRY/$IMAGE_NAME --namespace=$KUBE_NAMESPACE"
            }
        }
    }
}
