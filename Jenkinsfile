pipeline {
    agent any

    tools {maven "maven"}

    stages {
        stage('Pull')
        {
            steps {
                git "${githubURL}"
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build') {
            // Run the maven build
            steps {
                sh 'mvn package -DskipTests=true'
            }
        }
        stage('Deploy') {
            steps 
            {
                script 
                {
                    docker.withTool('docker')
                    {
                        repoId = "${dockerUser}/${projectName}"
                        sh 'docker -v'
                        image = docker.build(repoId)
                        docker.withRegistry("${ContainerRegistryURL}", "${ContainerRegistryCredId}")
                        {
                            image.push()
                        }
                    }
                }
                
            }
        }
    }
    post {
        success {
            slackSend(color: 'good', message: "Maven project ${projectName} has passed all tests and was successfully built and pushed to the CR.")
        }
        failure {
            slackSend(color: 'danger', message: "Maven project ${projectName} has failed to complete pipeline.")
        }
    }
    
}
