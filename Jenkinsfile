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
        stage('Debug Git Commit') {
            steps {
                script {
                    echo "🔍 Checking GIT_COMMIT value: ${env.GIT_COMMIT}"
                    if (!env.GIT_COMMIT?.trim()) {
                        error("❌ env.GIT_COMMIT is empty! Ensure checkout scm is executed properly.")
                    }
                }
            }
        }

        stage('Checkout Code') {
            steps {
                script {
                    checkout([$class: 'GitSCM',
                        branches: [[name: '*/test/notify']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        userRemoteConfigs: [[
                            url: 'https://github.com/HZeroxium/spring-petclinic-microservices',
                            credentialsId: 'github-token'
                        ]]
                    ])
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
                                 sha: env.GIT_COMMIT,
                                 account: 'HZeroxium'
                }
            }
        }

        stage('Run Dummy Task') {
            steps {
                script {
                    echo "Running a dummy task for debugging..."
                    sleep 1
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
                                 sha: env.GIT_COMMIT,
                                 account: 'HZeroxium'
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
                             sha: env.GIT_COMMIT,
                             account: 'HZeroxium'
            }
        }
    }
}
