pipeline {
    agent any

    triggers {
        pollSCM('*/5 * * * *')
    }

    stages {
        stage('Compile') {
            steps {
                dir('api/api-gateway') {
                    gradlew('clean', 'classes')
                }
                dir('api/auth-service') {
                    gradlew('clean', 'classes')
                }
            }
        }
        stage('Test and build') {
            steps {
                dir('api/api-gateway') {
                    gradlew('build')
                }
                dir('api/auth-service') {
                    gradlew('build')
                }
            }
            post {
                always {
                    junit '**/build/test-results/test/TEST-*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
        }
    }
}

def gradlew(String... args) {
    sh "./gradlew ${args.join(' ')} -s"
}