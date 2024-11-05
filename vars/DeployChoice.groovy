def call(){
    sshagent(['jumpbox-key']) {
        sh 'ssh -o StrictHostKeyChecking=no -J ubuntu@$BASTION_HOST ubuntu@$HAPROXY_HOST "kubectl $(rule) -f https://raw.githubusercontent.com/VictorA07/Kubernetes-dash/main/admin-user.yml"'
    }

}
