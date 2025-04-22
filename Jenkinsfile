pipeline {
	agent any
	
	tools {
		jdk 'JDK17'
	}

	triggers {
		githubPush()
	}

	stages {
		stage('Checkout') {
			steps {
				checkout scm
			}
		}

		stage('Build') {
			steps {
				sh '''
					./gradlew clean build -x test
				'''
			}
		}

		stage('Determine Target Environment') {
			steps {
				script {
					// 현재 활성화된 환경 확인
					def currentPort = sh(
						script: "curl -s http://localhost/serverProfile || echo '8081'",
						returnStdout: true
					).trim()
					
					// 타겟 환경 설정
					if (currentPort == "8081") {
						env.TARGET_ENV = "green"
						env.TARGET_PORT = "8082"
					}
					else {
						env.TARGET_ENV = "blue"
						env.TARGET_PORT = "8081"
					}
				}
			}
		}

		stage('Prepare Target Directory') {
			steps {
				sh """
					# 대상 디렉토리 생성(없는 경우)
					mkdir -p /opt/applications/suncar/${env.TARGET_ENV}
				"""
			}
		}

		stage('Copy JAR') {
			steps {
				sh """
					# 빌드된 jar 파일을 로컬 디렉터리로 복사
					cp $(find ${WORKSPACE}/build/libs -name "*.jar" -not -name "*-plain.jar") /opt/applications/suncar/${env.TARGET_ENV}/app.jar
				"""
			}
		}

		stage('Deploy') {
			steps {
				sh '''
					sudo /opt/applications/suncar/scripts/deploy.sh
				'''
			}
		}
	}

	post {
		success {
			echo '파이프라인이 성공적으로 완료되었습니다.'
		}
		failure {
			echo '파이프라인 실행 중 오류가 발생했습니다.'
		}
	}
}
