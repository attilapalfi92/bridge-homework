VERSION ?= 0.0.1-SNAPSHOT

build-gradle:
	#./gradlew clean build && java -jar build/libs/bridge-homework-${VERSION}.jar
	./gradlew clean build

build-docker:
	docker build -f distribution/docker/Dockerfile --build-arg JAR_FILE=build/libs/\*.jar -t getbridge/bridge-homework-apalfi:0.0.1-SNAPSHOT .

build: build-gradle build-docker

compose-up:
	docker-compose -f distribution/docker/docker-compose.yml

compose-up-test:
	docker-compose -f distribution/docker/docker-compose-test.yml up

run-tests:
	./gradlew test
