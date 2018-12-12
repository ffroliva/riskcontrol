# Risk Control

Take Home Test

## About the stack of technologies

I have chosen SpringBoot because it is much easier to configure and plays well with other tech stacks. I have also
used a REST API and SpringData JPA with an H2 database for future expansion of the project.

I used Spring Initializer to add all the dependencies and create a blank working project with all my configurations.

## Build

```
mvn clean install
```

## Run

```
mvn spring-boot:run
```

## Available URLs

Front-end entry point: http://localhost:8080

## Available API URLs

- http://localhost:8080/text

*Description:* This URL is responsible for reading a file by the name of text.txt. the file has mutiple lines.
All the lines are concat to previews one.

- http://localhost:8080/text/{word1}/{word2}

*Description:* This URL takes two inputs and searches all the occurrences of the word1
and replace by the word2 in a text read from a resource file.
