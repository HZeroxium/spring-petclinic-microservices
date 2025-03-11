pipeline {
    agent {
            node {
                label 'docker-agent-petclinic'
                }
          }

    environment {
        GITHUB_TOKEN = credentials('github-token')
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    checkout scm
                }
            }
        }

        stage('Send GitHub Notify - PENDING') {
            steps {
                script {
                    githubNotify context: 'Debug CI',
                                 status: 'PENDING',
                                 credentialsId: 'github-token',
                                 repo: 'HZeroxium/spring-petclinic-microservices',
                                 sha: env.GIT_COMMIT
                }
            }
        }

        stage('Run Dummy Task') {
            steps {
                script {
                    echo "Running a dummy task for debugging..."
                    sleep 5  // Giả lập thời gian chạy task
                }
            }
        }

        stage('Send GitHub Notify - SUCCESS') {
            steps {
                script {
                    githubNotify context: 'Debug CI',
                                 status: 'SUCCESS',
                                 credentialsId: 'github-token',
                                 repo: 'HZeroxium/spring-petclinic-microservices',
                                 sha: env.GIT_COMMIT
                }
            }
        }
    }

    post {
        failure {
            script {
                githubNotify context: 'Debug CI',
                             status: 'FAILURE',
                             credentialsId: 'github-token',
                             repo: 'HZeroxium/spring-petclinic-microservices',
                             sha: env.GIT_COMMIT
            }
        }
    }
}
