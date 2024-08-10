pipeline {
  agent any
  environment {
    BASE_URL = 'https://test-environment-url.com'
  }
  stages {
    stage('Install Dependencies') {
      steps {
        sh 'npm install'
      }
    }
    stage('Run Tests') {
      steps {
        sh 'npx playwright test --reporter=html'
      }
    }
    stage('Publish Report') {
      steps {
        publishHTML(target: [
          allowMissing: false,
          alwaysLinkToLastBuild: true,
          keepAll: true,
          reportDir: 'reports/html',
          reportFiles: 'index.html',
          reportName: 'HTML Report'
        ])
      }
    }
  }
  post {
    always {
      archiveArtifacts artifacts: 'reports/**/*.html', allowEmptyArchive: true
    }
    cleanup {
      cleanWs()
    }
  }
}
