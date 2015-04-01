CALL gradlew fatJar
IF NOT EXIST dist (mkdir dist)
move build\libs\bloomberg-helper-dev-client-all-1.0.jar dist\bloomberg-helper-dev-client.jar
java -jar dist/bloomberg-helper-dev-client.jar
pause