pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
        }
    }
}

// node {
//     stage('Get repo') {
//         git 'https://github.com/jakubowski1005/microblog.git'
//     }
//     stage('Compile') {
//         sh '''cd api/api-gateway && ./gradlew clean classes
//         cd ../auth-service && ./gradlew clean classes
//         '''
//     }
//     stage('Build') {
//         sh '''cd api/api-gateway && ./gradlew build
//         cd ../auth-service && ./gradlew build
//         ''' 
//     }
// }