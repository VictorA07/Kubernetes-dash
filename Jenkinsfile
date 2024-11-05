@Library('shared_lib') _
pipeline{
    agent any
    environment {
        BASTION_HOST = credentials('bastion-host')
        HAPROXY_HOST = credentials('haproxy-host')
    }
    stages {

        stage('kubernetes_dashboard') {
            when {
                expression { shouldExecuteStage('kubernetes_dashboard') }
            }
            steps {
                DeployChoice()
            }
        }
        stage('k8s_dashboard_ServiceAcc') {
            when {
                expression { shouldExecuteStage('k8s_dashboard_ServiceAcc') }
            }
            steps {

                sshagent(['jumpbox-key']) {
                    sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl $(rule) -f https://raw.githubusercontent.com/VictorA07/Kubernetes-dash/main/clusterbinding.yml"'
                }

            }
        }
        stage('k8s_dashboard_clusterbinding') {
            when {
                expression { shouldExecuteStage('k8s_dashboard_clusterbinding') }
            }
            steps {
                sshagent(['jumpbox-key']) {
                    sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl $(rule) -f https://raw.githubusercontent.com/VictorA07/Kubernetes-dash/main/clusterbinding.yml"'
                }
            }
        }
        stage('k8s_dashboard_token') {
            when {
                allOf {
                    expression { shouldExecuteStage('k8s_dashboard_token') }
                    expression { params.ACTION == 'apply' } // Only run this stage when ACTION is "apply"
                }
            }
            steps {
                sshagent(['jumpbox-key']) {
                    sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl -n kubernetes-dashboard create token admin-user"'
                }
            }
        }
    }
}
def shouldExecuteStage(stageName) {
    return params.STAGE == 'all' || params.STAGE == stageName
}
