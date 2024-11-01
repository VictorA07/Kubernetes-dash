pipeline{
    agent any
    environment {
        BASTION_HOST = credentials('bastion-host')
        HAPROXY_HOST = credentials('haproxy-host')
    }
    stages {

        stage('Deploy kubernetes dashboard') {
            steps {
                sshagent(['jumpbox-key']) {
                    sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl apply -f https://raw.githubusercontent.com/VictorA07/Kubernetes-dash/main/kubernetes-dashboard.yml"'
                }
            }
        }
        stage('Deploy k8s dashboard ServiceAcc') {
            steps {
                sshagent(['jumpbox-key']) {
                    sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl apply -f https://raw.githubusercontent.com/VictorA07/Kubernetes-dash/main/admin-user.yml"'
                }
            }
        }
        stage('Deploy k8s dashboard clusterbinding') {
            steps {
                sshagent(['jumpbox-key']) {
                    sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl apply -f https://raw.githubusercontent.com/VictorA07/Kubernetes-dash/main/clusterbinding.yml"'
                }
            }
        }
        stage('k8s dashboard token') {
            steps {
                sshagent(['jumpbox-key']) {
                    sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl -n kubernetes-dashboard create token admin-user"'
                }
            }
        }
    }
}
