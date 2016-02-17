# hoverfly-blog

At OpenCredo and SpectoLabs we’re helping a lot of organisations embrace the microservice architectural style. One problematic pattern we repeatedly see when organisations are migrating from working with a single Java-based monolith to multiple microservices is the development team stumbling with orchestrating multiple services for local development and pipeline-based automated testing. 

The goal of this post is to share our practical lessons of incorporating the Hoverfly ‘API simulation’ service virtualisation tool into our local development environments and automated tests.

Let’s create an experimental playground with a fake Java ‘monolith’ and an example microservice. When working with Java microservices, I’m a big fan of the Spring Boot platform, and so this is what we will use...

## The problem of working with an unavailable monolith

```
hoverfly-blog $ cd macroservice
macroservice $ mvn spring-boot:run
```

```
hoverfly-blog $ cd microservice
microservice $ mvn spring-boot:run
```

Now let’s make a request against the microservice:


```
hoverfly-blog $ curl localhost:8090/speedyRequest
```
Which should result in

```
[{"id":"1","name":"largeObject","mappings":["one","two"]}]
```

Now kill the monolith (using ^C / SIGINT) and try calling the microservice endpoint again:
```
^C
macroservice $
```

```
hoverfly-blog $ curl localhost:8090/speedyRequest
```
```
{"timestamp":1455635508958,"status":500,"error":"Internal Server Error","exception":"org.springframework.web.client.ResourceAccessException","message":"I/O error on GET request for \"http://localhost:8080/slowFragileRequest\": Connection refused; nested exception is java.net.ConnectException: Connection refused","path":"/speedyRequest"}
```

Boom! We can no longer test our microservice due to the dependency on macroservice HTTP endpoint for downstream communication.

## Let’s introduce some API simulation...

Shut everything down, and start the monolith again

```
macroservice $ mvn spring-boot:run
```

This time we run the microservice in the TEST profile. 

```
microservice $ mvn spring-boot:run -Dspring.profiles.active=TEST
```

Let’s now start our Hoverfly proxy. I have downloaded an appropriate Hoverfly binary from the project’s Github release page, and placed this in the microservice directory. I have also made sure the Hoverfly binary is executable ($ chmod u+x hoverfly) and excluded from git the binary and the associated ‘requests.db’ file that stores Hoverfly state.

We can now start Hoverfly in ‘capture’ mode where it will record all requests and responses made via the application as a proxy
```
microservice $ ./hoverfly -capture
```
Now all of our communication between microservices and macroservice is being recorded! Lets test our microservice again:
```
hoverfly-blog $ curl localhost:8090/speedyRequest
```
which results in 
```
[{"id":"1","name":"largeObject","mappings":["one","two"]}]
```

All good. Now let’s stop Hoverfly and start it again in the default ‘virtualize’ playback mode
```
microservice $ ./hoverfly
```
Let’s kill the monolith/macroservice again with ^C

Now, make the microservice call again:

```
hoverfly-blog $ curl localhost:8090/speedyRequest
```

and we get 
```
[{"id":"1","name":"largeObject","mappings":["one","two"]}]
```

Everything still works - even without the monolith running! If we look at the logging output on Hoverfly we can see that it served the request targeted at the monolith, rather than the (non-running) monolith replying itself:
```
{"bodyLength":58,"destination":"localhost:8080","key":"7e5f6ff30f2adcf5d402e7bbbaf632db","level":"info","method":"GET","middleware":"","mode":"virtualize","msg":"Response found, returning","path":"/slowFragileRequest","rawQuery":"","status":200,"time":"2016-02-16T15:25:32Z"}
```
