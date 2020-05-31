pipeline {
    agent any

    triggers {
        pollSCM('*/5 * * * *')
    }

    stages {
        stage('Test and build') {
            steps {
                dir('api/api-gateway') {
                    echo 'Building api gateway...'
                    gradlew('clean', 'build')
                }
                dir('api/service-registry') {
                    echo 'Building auth service...'
                    gradlew('clean', 'build')
                }
            }
            post {
                always {
                    junit '**/build/test-results/test/TEST-*.xml'
                }
            }
        }
        stage('Send E-Mail') {
            steps {
                echo 'Sending E-Mail...'
                script {
                    def mail = 'jakubowski1005@gmail.com'
                    def job = currentBuild.fullDisplayName
                    emailext body: '''${SCRIPT, template="groovy-html.template"}''',
                    mimeType: 'text/html',
                    subject: "[Jenkins] ${job}",
                    to: "${mail}",
                    replyTo: "${mail}",
                    recipientProviders: [[$class: 'CulpritsRecipientProvider']]
                }
            }
        }
    }
}

def gradlew(String... args) {
    sh "./gradlew ${args.join(' ')} -s"
}