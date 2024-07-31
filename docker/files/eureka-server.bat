docker stop web-app-eureka-server
docker rm web-app-eureka-server
docker rmi web-app/eureka-server
.\gradlew :components:eureka-server:bootJar && docker build ./components/eureka-server -t web-app/eureka-server