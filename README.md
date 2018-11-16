NUBE PROJECT
============
A **Spring Cloud** integration project (\#under construction).

## Index

<!--ts-->
   * [1 Project Modules and Submodules](#1-Project-Modules-and-Submodules)
   * [2 Getting started](#2-Getting-started)
      * [2.1 Requirements](#2.1-Requirements)
      * [2.2 Build](#2.2-Build)
<!--te-->
## 1 Project Modules and Submodules
NUBE is composed with the following modules and submodules:
- **nube-assembler:** Set up and assembly all files (Configuration files, SSL files ...) required by the other modules and submodules. 
    - **assembly:** Settings setup.
        - **configuration-setup:** Replace all *configuration files* token's with parameterised values of root parent pom.
    - **keytool:** Package all *ssl files* required by all nube services for *https* connections. 
    
        If you want override and generate new SSL files, you must add __-Dssl-setup=true__ parameter in project build command:
        
        Linux
        ```bash
        cd [$project_dir or $project_assembler_module_dir]
        mvn clean install -Dassembly-keytool=true
        ```
        Windows
        ```cmd
        cd [%project_dir% or %project_assembler_module_dir%]
        mvn clean install -Dassembly-keytool=true
        ```
- **nube-configuration-files:** Contains all filtered configuration files, is a *git submodule* used by **_configurator-service_**
- **nube-core:**
- **nube-services:** All spring cloud services and micro services.
    - **eureka** Netflix Eureka Discovery service.
    - **configurator** Spring Cloud Config service.
    - **zipkin** Spring Cloud Sleuth & Zipkin service.

## 2 Getting started
This section explains how to install the whole project in some steps.
### 2.1 Requirements
Before start, is mandatory to have already installed this software in your machine:
* **JDK** >= 1.8
* [**git**](https://git-scm.com/downloads): Version control.
* [**Apache Maven**](https://maven.apache.org/download.cgi): Project management and comprehension tool.
* [**Docker**](https://www.docker.com/get-started): Container solution.
---
[Only if you want run in local without **Docker**].
* [**Node.js**](https://nodejs.org/en/download/): Client side applications.
* [**mongoDB**](https://www.mongodb.com/download-center): Data Base storage.
* [**RabbitMQ**](https://www.rabbitmq.com/download.html): Message broker.

Please make sure you set `$JAVA_HOME` points to the correct JDK and that the `mvn`, `node` and `npm` commands are on your machine `$PATH`.
### 2.2 Build
First open **bash shell / command promp** and go to root parent project folder

Linux
```bash
cd $project_dir
mvn install
```
Windows
```cmd
cd %project_dir%
mvn install
```

##### Developed by:
Rubén García Ríos - @github/RubenGarciaRios
