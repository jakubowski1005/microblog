pipeline {
    agent any

    triggers {
        pollSCM('*/5 * * * *')
    }

    stages {
        stage('Test and build') {
            steps {
                dir('api-gateway') {
                    gradlew('clean', 'build')
                }
                dir('service-registry') {
                    gradlew('clean', 'build')
                }
                dir('config-server') {
                    gradlew('clean', 'build')
                }
                dir('email-service') {
                    gradlew('clean', 'build')
                }
            }
            post {
                always {
                    junit '**/build/test-results/test/TEST-*.xml'
                }
            }
        }
        stage('Check code quality') {
            parallel {
                stage('Code Coverage') {
                    steps {
                        jacoco exclusionPattern: '**/test/**,**/lib/*', inclusionPattern: '**/*.class,**/*.java' 
                    }
                }
                stage('Static Code Analysis') {
                    steps {
                        dir('api-gateway') {
                            gradlew('pmdMain', 'pmdTest')
                        }
                        dir('service-registry') {
                            gradlew('pmdMain', 'pmdTest')
                        }
                        dir('config-server') {
                            gradlew('pmdMain', 'pmdTest')
                        }
                        dir('email-service') {
                            gradlew('pmdMain', 'pmdTest')
                        }
                    }
                }
            }
        }
        stage('Send E-Mail') {
            steps {
                script {
                    def mail = 'dev.jakubowski@gmail.com'
                    def job = currentBuild.fullDisplayName
                    def status = currentBuild.currentResult
                    emailext body: '''${SCRIPT, template="groovy-html.template"}''',
                    mimeType: 'text/html',
                    subject: "[Jenkins] ${job} - ${status}",
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
