#Imaginea de baza este urmatoarea:
FROM openjdk:8u191-jre-alpine3.8

#Intalam "curl" si "jq" in imaginea de mai sus
RUN apk add curl jq

#Spatiul de lucru de din Docker, este creat automat
WORKDIR /usr/share/proiectlicenta

#Adaugam fisierele de tip .jar
ADD target/selenium-docker.jar          selenium-docker.jar
ADD target/selenium-docker-tests.jar    selenium-docker-tests.jar
ADD target/libs                         libs
#putem adauga mai multe tipuri de fisiere(.csv / .json etc), in functie de nevoile proiectului nostru !

#Adaugam fisierele TestNG pentru a rula testele
ADD module-book-flight.xml              module-book-flight.xml
ADD module-search.xml                   module-search.xml
ADD module-online-order.xml             module-online-order.xml
ADD tests-suite.xml                     tests-suite.xml

#Aici descarc un script pentru a verifica daca nodurile de Selenium Grid sunt up and running inainte ca testele sa fie pornite
RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh

#Trebuiesc introduse 3 variabile dupa compilare: Browser(like: chrome or firefox), Host(like: localhost) and Module(like: book-flight-module or search-module)
ENTRYPOINT sh healthcheck.sh
#Comanda pentru pornirea testelor este apelata din scriptul "healthcheck.sh" de mai sus(ex. comanda: java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE)