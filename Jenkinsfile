pipeline {
	
    agent any	
    
    
    stages {   
         
        
        stage('Initialize Build') {
            steps {
                script {
                     gv = load 'script.groovy'
                }
            }
        }
        stage('BuildImage') {
            steps {
                script {
                    gv.BuildImage()
                }
            }
        }
          
        stage('Deploy to production environment'){
                steps {
                    script {
                        gv.DeployToProduction()
                    }
                }
        }
    }
}
