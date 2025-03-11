pipeline {
    agent {
        docker {
            image 'hzeroxium/petclinic-jenkins-agent'
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
                        echo "⚠️ env.GIT_COMMIT is empty, fetching manually..."
                        env.GIT_COMMIT = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                        echo "✅ Updated GIT_COMMIT: ${env.GIT_COMMIT}"
                    }
                }
            }
        }

        stage('Checkout Code') {
            steps {
                script {
                    try {
                        checkout([$class: 'GitSCM',
                            branches: [[name: '*/test/notify']],
                            doGenerateSubmoduleConfigurations: false,
                            extensions: [[$class: 'CloneOption', depth: 1, noTags: true, shallow: false]],
                            userRemoteConfigs: [[
                                url: 'https://github.com/HZeroxium/spring-petclinic-microservices',
                                credentialsId: 'github-token'
                            ]]
                        ])
                        echo "✅ Code checked out successfully"
                    } catch (Exception e) {
                        error("❌ Checkout failed: ${e.getMessage()}")
                    }
                }
            }
        }

        stage('Send GitHub Notify - PENDING') {
            steps {
                script {
                    try {
                        githubNotify context: 'Debug CI',
                                     status: 'PENDING',
                                     credentialsId: 'github-token',
                                     repo: 'HZeroxium/spring-petclinic-microservices',
                                     sha: env.GIT_COMMIT
                        echo "✅ GitHub Notify - PENDING sent successfully"
                    } catch (Exception e) {
                        error("❌ GitHub Notify - PENDING failed: ${e.getMessage()}")
                    }
                }
            }
        }

        stage('Run Dummy Task') {
            steps {
                script {
                    echo "🚀 Running a dummy task for debugging..."
                    sleep 2
                    echo "✅ Dummy task completed"
                }
            }
        }

        stage('Send GitHub Notify - SUCCESS') {
            steps {
                script {
                    try {
                        githubNotify context: 'Debug CI',
                                     status: 'SUCCESS',
                                     credentialsId: 'github-token',
                                     repo: 'HZeroxium/spring-petclinic-microservices',
                                     sha: env.GIT_COMMIT
                        echo "✅ GitHub Notify - SUCCESS sent successfully"
                    } catch (Exception e) {
                        error("❌ GitHub Notify - SUCCESS failed: ${e.getMessage()}")
                    }
                }
            }
        }
    }

    post {
        failure {
            script {
                try {
                    githubNotify context: 'Debug CI',
                                 status: 'FAILURE',
                                 credentialsId: 'github-token',
                                 repo: 'HZeroxium/spring-petclinic-microservices',
                                 sha: env.GIT_COMMIT
                    echo "❌ GitHub Notify - FAILURE sent"
                } catch (Exception e) {
                    echo "⚠️ GitHub Notify - FAILURE failed: ${e.getMessage()}"
                }
            }
        }
        always {
            echo "🔄 Pipeline execution completed"
        }
    }
}
