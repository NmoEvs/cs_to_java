# Spring

## A brief history

A lot of java advanced functionality are provided through the JavaEE API. It means that you need a JavaEE server
  to have this API benefits or you have to provide your own implementations. One of the main missing implementation
  was the bean container used by EJB.
  
EJB has a poor reputation due to awful firsts versions, the first usable version is the 3.1. Nevertheless,
the idea of a container of beans with their own lifecycle was good and a framework named Spring was created
 to provide a lightweight bean container usable in a JavaSE environment.
  
With years, a lot of other projects were created based on this framework and the core project is now 
called `Spring Framework` instead of simply Spring to distinguish it from other projects of the Spring.io team.
 
## The inversion of control
 
Spring Framework provides an inversion of control (IOC). This pattern applies the Hollywood principle:
 "_Don't call me, I'll call you_" ! You just declare your needed objects and the system injects them instead
 of letting you create them.
 By this way, your code is more concise and you can extract technical boiler plate of object creation to
 focus on the business.
  
### Transitive benefits

Using IOC gives you a way to work with interfaces delegating the implementation selection to the dependency
management tool. Your business service needs a DAO interface to get data and Spring will inject an implementation
found in the classpath at runtime, this implementation is determined by your gradle file loading a particular
implementation as a stub on in development and a JPA one in production.

## The container

Spring Framework create a container called `context` at startup. This container allows management of beans.

> What's a Bean ?
>
> In OO world, a bean is a class with only a default constructor, attributes and accessors. Nowadays in java,
  this kind of class is called `JavaBean` and a `bean` represents an `EJB` or a `Spring Bean`

The container manage the beans lifecycle. Each bean is view as an autonomous entity which can be injected
in an other bean. Beans can be services, dao's, data storage,... they are created by Spring and available
in the container.

### Context definition

The context can be defined either by an XML file, by an annotated class or by API. Firsts applications were 
using an XML file but most applications use now the annotation configuration.
  
By default, the class containing the context initialization annotation defines the root of the application,
every class in this package and subpackages is scanned at startup to detect annotations and create beans
if necessary. You can add some other packages to be scanned to create beans found in libraries or sub projects.

Each bean has a name, by default the name of the bean is the name of the class with a lower case first letter.

### Beans definitions

The easiest way to create a bean is to annotate the class with a Spring bean annotation. Three annotations 
are provided to determine the usage of the bean:

* `@Configuration` : Spring configuration class
* `@Service` : Used for services classes providing a business logic.
* `@Repository` : Used for DAO's, those specialized beans allows to get a transaction injected per user even
if the bean itself is a singleton.
* `@Component` : Used for data storage such as configurations.
* `@Controller` : Spring MVC controller

```Java
...
@Repository
public class JPAMediaAssetDAO implements MediaAssetDAO {
...
```

An other way to create bean is to create a factory method with a `@Bean` annotation. This way allows an less
intrusive way to create beans but needs more boiler plate code.

```Java
@Bean
public ContentService.Client getContentServerClient() {
    return clientProvider.createClient(this, ContentService.Client::new);
}
```



### Beans injection

Beans are selected using the object type. When the code ask a MediaAssetDAO injected bean, Spring will inject a bean
of this class. It means that you can have only one bean definition per Class. It could be a problem if you have multiple
implementations of an interfaces annotated with beans annotations. That's why it's a good practice to split different 
implementations into separate projects to allow implementation selection through the dependency management.

You can infer this logic using [@Qualifier](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Qualifier.html)
annotation. See [example here](https://spring.io/blog/2014/11/04/a-quality-qualifier).

#### By annotation

You can use the @Autowired annotation on an attribute, Spring will inject an instance at runtime.

```Java
 @Autowired
 private MediaAssetDAO mediaAssetDAO; 
```

#### By constructor

If you create a constructor for a bean class using attributes, Spring will inject existing beans instances of attributes types.

```Java
 @Servcie
 public class MediaAssetService{
  
  private MediaAssetDAO mediaAssetDAO; 
  
  public MediaAssetService ( MediaAssetDAO mediaAssetDAO ){
    this.mediaAssetDAO = mediaAssetDAO;
  }
  
 } 
```

#### GOLD RULE

> You can only inject a bean into a bean, otherwise you will have a null object. If you have a NullPointerException you
> probably inject a non bean class in a bean or a bean in a non bean class.

### Useful annotations

Two useful annotations allows you to trigger code during the bean lifecycle:

* @PostConstruct
  * Called after the constructor (attributes are injected) when the bean is created.
* @PreDestroy
  * Called before the destruction of the bean.


### Beans scope

By default, a bean is a singleton. If you need to specify that a bean must not be a singleton, use the `@Scope` annotation
to make it a `prototype`. Each time a prototype bean is injected, a new instance of it is created.

```Java
 @Component
 @Scope("prototype")
 public class MyNonSingletonClass{
  
 }
```

In a web environment, specific scopes are available: `Request`, `Application`, `Session`,... 

## Transactions

Spring provides a transactional API. By annotating a method with `@Transactional` you implicitly create a transaction
by entering the method. The transaction will be committed in case of return and rollbacked in case of exception (by default
only on Runtime exceptions).

This is "just" an API, it means you have to determine an implementation such as a Database implementation.
 
This annotation provides some customization attributes to determine the propagation method, the read only,...
 
[Transaction management reference](http://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/transaction.html)


## Other projects

Spring provides lot of other project helping java developers in their day to day life

* `Spring Boot`
  * Takes an opinionated view of building production-ready Spring applications. Spring Boot favors convention over configuration 
  and is designed to get you up and running as quickly as possible.
* `Spring Cloud`
  * Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems 
  (e.g. configuration management, service discovery, circuit breakers, intelligent routing, micro-proxy, control bus, 
  one-time tokens, global locks, leadership election, distributed sessions, cluster state). Coordination of distributed 
  systems leads to boiler plate patterns, and using Spring Cloud developers can quickly stand up services and applications 
  that implement those patterns. They will work well in any distributed environment, including the developer's own laptop, 
  bare metal data centres, and managed platforms such as Cloud Foundry.
* `Spring Data`
  * Spring Data’s mission is to provide a familiar and consistent, Spring-based programming model for data access while still
   retaining the special traits of the underlying data store. It makes it easy to use data access technologies, relational 
   and non-relational databases, map-reduce frameworks, and cloud-based data services. This is an umbrella project which 
   contains many subprojects that are specific to a given database. The projects are developed by working together with 
   many of the companies and developers that are behind these exciting technologies.
* `Spring Security`
  * Spring Security is a powerful and highly customizable authentication and access-control framework. It is the de-facto 
  standard for securing Spring-based applications.


[Complete list of Spring projects](https://spring.io/projects)

## Reference

* [Spring framework reference documentation](http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/)
* [Spring framework API](http://docs.spring.io/spring-framework/docs/current/javadoc-api/)
* [Spring guides](https://spring.io/guides)
