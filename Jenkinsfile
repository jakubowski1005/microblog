node {
    stage('Compile') {
        sh '''cd api/api-gateway && ./gradlew clean classes
        cd ../auth-service && ./gradlew clean classes
        '''
    }
    stage('Build') {
        sh '''cd api/api-gateway && ./gradlew build
        cd ../auth-service && ./gradlew build
        ''' 
    }
}