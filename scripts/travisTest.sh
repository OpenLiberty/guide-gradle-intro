./gradlew -version
./gradlew tasks
./gradlew build -x openBrowser -i
curl http://localhost:9080/GradleSample/servlet | grep Hello
./gradlew libertyStop
