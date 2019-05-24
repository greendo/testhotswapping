# testhotswapping

1. You should first run run.sh from a terminal, later you can use docker intellij plugin for managing container.
    Add SpringBoot configuration

    main class: org.springframework.boot.devtools.RemoteSpringApplication  
    program arguments: http://localhost:8080
  
2. pom.xml should contain next lines for enabling devtools for development in docker (prod should not contain these lines!): 

    excludeDevtools=false
      
3. application.properties shold contain next line:
    spring.devtools.remote.secret=thisismysecret
  
Feel free to change TestController.kt, then Build->Rebuild Project to hot swap.
