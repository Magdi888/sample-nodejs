#!/usr/bin/env groovy

def BuildImage() {
    withCredentials([usernamePassword(credentialsId :'DockerHub',usernameVariable :'USER',passwordVariable :'PASSWORD')]){
       sh '''
       tag=`git log --format="%H" -n 1 | cut -c 1-7`
       docker build -t amagdi888/nodeapp:${tag}${BUILD_ID} .
       echo $PASSWORD | docker login -u $USER --password-stdin
       docker push amagdi888/nodeapp:${tag}${BUILD_ID}
       '''
    }

}


def DeployToProduction() {
    withKubeConfig([credentialsId: 'K8S']) {
                        sh '''
                        tag=`git log --format="%H" -n 1 | cut -c 1-7`
                        helm upgrade --install -n blue-tide --set image.tag=${tag}${BUILD_ID} nodeapp ./helm
                        '''
                    }
}

return this