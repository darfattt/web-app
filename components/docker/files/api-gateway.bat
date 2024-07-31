docker stop web-app-api-gateway
docker rm web-app-api-gateway
docker rmi web-app/api-gateway
.\gradlew :components:api-gateway:bootJar && docker build ./components/api-gateway -t web-app/api-gateway