# Getting Started

Dans le cadre de la modernisation du SI PMU vers une architecture événementielle, vous devez
développer un nouveau microservice responsable du cycle de vie d’une course et de ses partants
(chevaux).
Après un rude interrogatoire du métier, vous apprenez que :
• Une course a lieu un jour donné et possède un nom et un numéro unique pour ce jour ;
• Une course possède au minimum 3 partants ;
• Chaque partant possède un nom et un numéro ;
• Les partants d’une course sont numérotés à partir de 1, sans doublon ni trou.
Pour le MVP on vous demande de développer une API permettant de créer des courses et leurs
partants, de stocker les informations en base de données et de les exposer au reste du SI par un
message publié sur un bus.


NB: Pour effectué les tests du bus kafka, nous avons utilisé l'image Docker Kafka.
Il est necessaire de le démarer via le docker-compose. 
A défaut, faudra fournir le bon ootstrap-servers url dans les 
properties afin de démarer le bus kafka. 



### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

