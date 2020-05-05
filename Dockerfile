FROM openjdk:8u191-jre-alpine3.8

#Spatiul de lucru de din Docker, este creat automat
WORKDIR /usr/share/SeleniumDockerProject

#Adaugam fisierele de tip .jar
ADD target/selenium-docker.jar          selenium-docker.jar
ADD target/selenium-docker-tests.jar    selenium-docker-tests.jar
ADD target/libs                         libs

#putem adauga mai multe tipuri de fisiere(.csv / .json etc) 

#Add suite files
ADD module-book-flight.xml              book-flight-module.xml
ADD module-search.xml                   search-module.xml

#Trebuiesc introduse 3 variabile dupa compilare: 
#Browser(like: chrome or firefox), Host(like: localhost) and Module(like: book-flight-module or search-module)
ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE

