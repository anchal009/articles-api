# Articles API

#### Overview
>API that makes a request to the article and ContentMarketing URLs, 
> and returns JSON containing an array created from the two responses.
> 
> The returned array should consist of the first five articles from the article response, 
> followed by the first content marketing from the content marketing response. 
> This pattern should repeat, taking the next five articles followed by the next 
> content marketing. If no more content marketing is available, you should continue 
> the pattern, but insert an object in the place of content marketing with a single field,
> Type, that has the value “Ad”.
> 
> Article URL: https://storage.googleapis.com/aller-structure-task/articles.json
> 
> Content Marketing URL: https://storage.googleapis.com/aller-structure-task/contentmarketing.json

#### Exposed REST apis
Here below the most relevant features exposed using REST Apis:

#### Features

* JSON containing an array created from the two responses

## REST apis exposed
REST apis with Swagger and Ope Api docs:

http://localhost:8081/swagger-ui.html

http://localhost:8081/v3/api-docs

Postman can also be used.

## Quick Start
Execute the microservice code using Maven:

    ./mvnw spring-boot:run
    
Open a browser and explore the REST apis:

http://localhost:8081/swagger-ui.html

The service should be up and running.

Everything should be up and running :)
