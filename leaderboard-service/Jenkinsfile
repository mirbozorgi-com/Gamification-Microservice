pipeline {
    agent any
    stages {
        stage('mvn build')
        {
            steps{
               sh "cd src && mvn clean package"
            }
        }
    }
}