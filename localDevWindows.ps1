docker-compose down
./gradlew build
docker-compose build
docker-compose -f docker-compose.yml -f docker-compose.local.yml up