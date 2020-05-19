node {
    stage('Compile') {
        sh '''cd $WORKSPACE/api/api-gateway && ./gradlew clean classes
        cd $WORKSPACE/api/auth-service && ./gradlew clean classes
        '''
    }
    stage('Build') {
        sh '''cd $WORKSPACE/api/api-gateway && ./gradlew build
        cd $WORKSPACE/api/auth-service && ./gradlew build
        ''' 
    }
}