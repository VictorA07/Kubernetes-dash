pipeline{
    agent any
    environment {
        BASTION_HOST = credentials('bastion-host')
        HAPROXY_HOST = credentials('haproxy-host')
    }
    stages {

        stage('kubernetes_dashboard') {
            steps {
              sshagent(['jumpbox-key']) {
                sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl $(rule) -f https://raw.githubusercontent.com/VictorA07/Kubernetes-dash/main/admin-user.yml"'
              }
            }
        }
        stage('k8s_dashboard_ServiceAcc') {
            steps {

                sshagent(['jumpbox-key']) {
                    sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl $(rule) -f https://raw.githubusercontent.com/VictorA07/Kubernetes-dash/main/clusterbinding.yml"'
                }

            }
        }
        stage('k8s_dashboard_clusterbinding') {

            steps {
                sshagent(['jumpbox-key']) {
                    sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl $(rule) -f https://raw.githubusercontent.com/VictorA07/Kubernetes-dash/main/clusterbinding.yml"'
                }
            }
        }
        stage('k8s_dashboard_token') {

            steps {
                sshagent(['jumpbox-key']) {
                    sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl -n kubernetes-dashboard create token admin-user"'
                }
            }
        }
    }
}
