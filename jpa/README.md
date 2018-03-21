# JPA

Java Persistence Api is the JavaEE api used to persist data.

## Main principles

JPA defines how to declare and persist entities. An entity is a functional data model, entities
can be linked together with relations. This is a persistence abstraction, it means that the developer
does not matters how it's persisted, it can be in a database, in a file, in memory,...

In the code, an entity merged in the persistence context is consider as persisted, if the physical
linked storage is a database, that means that the data is inserted.

JPA is linked with the transactional API used in your application.

As JPA is most often implemented over a database, this article will make reference to database principles.

There is two ways to work with JPA : _code first_ or _database first_. In a code first view, JPA implementation
is able to create or upgrade the database based on Entities definition. In a database first view, JPA
implementation assumes that the database matches with entities definition.

## Entities

### Definition

An entity is a class annotated with JPA annotations. The class will be translated in a table in the 
database. A class is marked to be a table with the annotation `@Entity`

Attributes of the class are consider as table columns if they are annotated. An attribute is marked
to be a column with the annotation `@Column`

`@Entity` can be qualified with an attribute to set the corresponding table name.

`@Column` can be qualified by attributes to set additional corresponding column information as
* name
* insertable
* updatable
* length
* precision
* nullable
* unique

The primary key of the entity is annotated with `@Id`.

```Java
@Entity
public class Project {
    @Id 
    Long id;
     
    @Column
    String name;
    
    ...
}
```

### Primary keys

If you want to create an composite key, you
can either annotate different components of the key with the `@Id` annotation or create a separate class
annotated with `@Embeddable` ans use `@EmbeddedId` instead of `@Id` into the entity itself.

```Java
@Entity
public class Project {
    @Id 
    Integer departmentId;
    @Id
    Long projectId;
     
    @Column
    String name;
} 
```

or

```Java
@Entity
public class Project {
    @EmbeddedId 
    ProjectId id;
     
    @Column
    String name;
}
 ```
 ```Java
@Embeddable
public Class ProjectId {
    Integer departmentId;
    Long projectId;
}
```

If you want to get generated id's you can add `@GeneratedValue` on the `@Id` annotated attribute.
Four generation strategies are available:
* `@GeneratedValue(strategy=GenerationType.AUTO)` 
  * A unique generator will be used for all entities marked as well. The generator depends on the
   database vendor but is most often based on a sequence
* @GeneratedValue(strategy=GenerationType.IDENTITY)
  * it's quite the same as "AUTO" but a separate system will be used for each entity.
* `@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")` 
  * Use a specific sequence (named "seq" in the example) which must be declared with an other annotation
  `@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)`
* `@GeneratedValue(strategy = GenerationType.TABLE, generator = "PK_Table")`
  * Use a table with last value by key, this table is used in an autonomous transaction and must be 
    declared with an annotation `@TableGenerator(name = "PK_Table", pkColumnValue = "MyEntity", pkColumnName = "ENTTY_NAME", allocationSize = 1, table = "PrimaryKeys", valueColumnName = "VAL")`

### Non persisted information

If you need to store information in the class which must not be persisted (as calculated field), 
you can annotate it with the `@Transient` annotation.

```Java
@Entity
public class Project {
    @Id 
    Integer departmentId;
    @Id
    Long projectId;
     
    @Column
    String name;
    
    @Transient
    String fullName = departmentId + "/" + projectId + "/" + name;
} 
```

### Data type conversion

JPA can work with most of java types but you can create your own converter if there is no default one.

```Java
@Converter
public class BooleanToIntegerConverter 
    implements AttributeConverter<Boolean, Integer> 
{  ... }
```

```Java
@Entity
@Table(name = "EMPLOYEE")
public class Employee
{

    @Id
    private Long id;

    @Column
    @Convert(converter = BooleanToIntegerConverter.class)
    private boolean fullTime;

}
```

If you're using the `java.util.Date` type, you can define if you want to store date only or datetime
by using additional annotation on the attribute
* `@Temporal(TemporalType.TIMESTAMP)`
  * Store a full date and time
* `@Temporal(TemporalType.DATE)`
  * Store date only
* `@Temporal(TemporalType.TIME)`
  * Store time only

Note that NIO2 `LocalDateTime` and `LocalDate` are managed in latest versions of JPA implementations even
if it not in the JPA 2.1 specification.

### Embedded entities

Embedded entities are used to create a class for specific information which will not be stored in a separated
table but inside the containing entity related table. You can override the name of the attribute to set
specific column name in your table

Following example will inject `AspectRation` columns into the `VideoEssence` table renaming default columns name:

```Java
@Embeddable
public class AspectRatio {

  @Column
  private Integer widthFactor;

  @Column
  private Integer heightFactor;
}
```
```Java
@Entity
public class VideoEssence {

...

@Embedded
    @AttributeOverrides( {
        @AttributeOverride(name="widthFactor", column = @Column(name="aspectRatioWidthFactor") ),
        @AttributeOverride(name="heightFactor", column = @Column(name="aspectRatioHeightFactor") )
    } )
    private AspectRatio aspectRatio;

...

}
```

### Multiple entities in a table with a discriminator field

If you need to store different entities in a single table, you can use a discriminator.

Following example will store `AudioEssence` and `VideoEssence` in the same `Essence` table:

Define an abstract class as base entity determining the name of the table and including all shared attributes.
`@DiscriminatorColumn` is used to define the name of the column containing the discriminator value.
```Java
@Entity
@DiscriminatorColumn(name = "ESSENCE_TYPE")
public abstract class Essence {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected UUID id;

}
```

Extends the base entity and specify the discriminator value with `@DiscriminatorValue`
```Java
@Entity
@DiscriminatorValue("AUDIO")
public class AudioEssence extends Essence {

  @Column
    private Integer bitDepth;

}
```

```Java
@Entity
@DiscriminatorValue("VIDEO")
public class VideoEssence extends Essence {

    @Column
        private Integer height;
}
```

### Storing enumerations

Enums can be stored as strings (using `toString()` method) or as numerical values representing the index 
of the value in the Enum. This second method must be used with caution due to index modification during
the life of the application...

Use the `@Enumerated` annotation specifying the type of storage as attribute.

```Java
@Column
  @Enumerated(EnumType.STRING)
  private BitrateMode bitRateMode;
```
or
```Java
@Column
  @Enumerated(EnumType.ORDINAL)
  private BitrateMode bitRateMode;
```

### Annotations location

Attributes annotations can be placed on the attribute definition or on the attribute accessor (getter).
The difference consists on how JPA will access data of your class.

* Annotation on attribute use introspection to set/get data
* Annotation on accessor use accessor methods to set/get data

You can use only one method in a same entity. We recommend to use annotations on attributes definition 
to have a more comprehensive code and avoid side effects when changing accessors code.

### Concurrent modification
 
JPA provides a system to avoid concurrent modification conflicts. The `@Version` annotation.
This annotation can be set on a numeric or temporal field. JPA will increase the numeric field or set
the temporal field to "now" on each modification and will check that this information has not changed
between the retrieve and the update. If the version has changed, an `OptimisticLockException` is thrown. 

```Java
@Entity
public class MyEntity {    

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Version
    private Long version;

    //...
}
```

### Partial entities

If you want to partially load an entity to avoid to get systematically a column filled with a big amount
of data you can use the `@Basic` annotation to lazily load it's value.

In the following example, when a `Message` is loaded, the `body` attribute is not loaded first. It will
be loaded when accessing the `body` attribute. 

```Java
@Entity
public class Message {    

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String subject;

    @Column
    @Basic(fetch=FetchType.LAZY)
    private String body;
    
}
```

## Relationship

Relationship between entities is the most difficult part of JPA and must be understood to avoid performance
issues.

Three main relations exists and their names are clear: `@OneToOne`, `@OneToMany` and `@ManyToOne`.

Relations are object based, it's a major difference with database relations where relation is only
represented with the primary key of the related table. It means that you have a parent object in each
child and a collection of children in the parent object. If you think on term of objects, it's normal
but it's not the first vision when you're used with database representation.

A basic relation would be:

```Java
@Entity
public class Parent {

  @Id
  private UUID id;
  
  @OneToMany
  private Set<Child> children = new HashSet<>();
}
```

```Java
@Entity
public class Child {

  @Id
  private UUID id;
  
  @ManyToOne
  private Parent parent;
}
```

Represented in database as
 
|PARENT|
|------|
|UUID  id|

|CHILD|
|------|
|UUID  id|
|UUID  parent_id|


> Use `Set` instead of `List` to store collections to avoid empty records !

When setting a `@ManyToMany` relationship, both entities have a collection of other entity.
It's impossible to represent it as is in the database. By default, JPA will create a association
table named with the name of the entities separated with an underscore and containing id's of 
linked entities. You can infer the association table creation with a `@JoinTable`

```Java
@Entity
public class Employee {
  @Id
  @Column(name="ID")
  private long id;
  ...
  @ManyToMany
  @JoinTable(
      name="EMP_PROJ",
      joinColumns=@JoinColumn(name="EMP_ID", referencedColumnName="ID"),
      inverseJoinColumns=@JoinColumn(name="PROJ_ID", referencedColumnName="ID"))
  private Set<Project> projects;
  .....
}
```
```Java
@Entity
public class Project {
  @Id
  @Column(name="ID")
  private long id;
  ...
  @ManyToMany(mappedBy="projects")
  private Set<Employee> employees;
  ...
}
```

To complete the definition of a relation, you have to determine the join column and determine if you
want a bi-directional relation. A bi-directional relation will set a `Parent` object in the `Child` entity,
an uni-directional relation (without the `mappedBy` attribute) will use a `UUID` instead of a `Parent` in the 
`Child`.

Bi-directional
```Java
@Entity
public class Parent {

  @Id
  private UUID id;
  
  @OneToMany(mappedBy="parent")
  private Set<Child> children = new HashSet<>();
}
```

```Java
@Entity
public class Child {

  @Id
  private UUID id;
  
  @ManyToOne
  @JoinColumn(name="parent_id")
  private Parent parent;
}
```

Uni-directional
```Java
@Entity
public class Parent {

  @Id
  private UUID id;
  
  @OneToMany
  private Set<Child> children = new HashSet<>();
}
```

```Java
@Entity
public class Child {

  @Id
  private UUID id;
  
  @Column(name="parent_id")
  private UUID parentId;
}
```

### Cascading actions

By default, operations on an entity are not cascaded to their relations. It means that if you delete
a parent, children are not deleted.

You can infer the cascading using attribute on relation annotation.

```Java
@Entity
public class Parent {

  @Id
  private UUID id;
  
  @OneToMany(cascade = CascadeType.ALL)
  private Set<Child> children = new HashSet<>();
}
```

Cascade types can be `ALL`, `DETACH`, `MERGE`, `PERSIST`, `REFRESH` or `REMOVE` and you can set a collection of 
values. See Entity Manager section for further details on those operations.

[CascadeType API](http://docs.oracle.com/javaee/7/api/javax/persistence/CascadeType.html)

>The cascading can be set on both side of the relation, be careful, if you set a cascade ALL on both side,
a delete on a child will invoke a delete on the parent and then on other children, it's not necessary
what is attendee.

### Lazy loading

By default, a relation is lazy loaded. It means that a request on a parent will fill a Parent object
with a collection of fake Children, you can count the children but when you want to get data from a 
child, a new request is done on the database to get information about this element.

The good thing is that your first query is quick and light but it means you need to stay in your
session to access children information. If your entity gets out of the transaction, a call to a child
attribute will cause an Exception.

If you want to load the whole information in the first query you can use the `fetch` attribute on the 
relation ans set it to `EAGER` instead of the default `LAZY`
 
```Java
@Entity
public class Parent {

  @Id
  private UUID id;
  
  @OneToMany(fetch = FetchType.EAGER)
  private Set<Child> children = new HashSet<>();
}
```

> Be careful, if your relations are set in EAGER mode, you could load all database in one query... 

Choosing between EAGER and LAZY is important in term of performance, memory impact and side effects.

### inner or outer join 
 
On a @OneToOne or a @ManyToOne relation, you can set an attribute `optional` which is default set to 
`true` and determines if the linked object is always set or not. If this attributes is set to `true`
the request will use an `outer join` but if the attributes is set to `false`, an `inner join` will be used.

In term of performance, an inner join is better but can be used only if all references exists. Think to
set this attribute correctly to improve performance without having false empty result sets.


## The Entity Manager

The `EntityManager` is the entry point to the persistence context. It's configured to receive a datasource
and is injected as a bean in your dto's.

```Java
@Repository
public class JPARecorderDAO implements RecorderDAO{

    @PersistenceContext
    private EntityManager em;
}
```

[EntityManager API](http://docs.oracle.com/javaee/7/api/javax/persistence/EntityManager.html)

You can use the EntityManager to make a lot of operations with entities as basic ones

* `persist` : Make an entity persistent (insert)
* `merge` : Merge the state of a persisted entity into the persistence context (update) 
* `find` : Gets a persisted entity by it's ID (select)
* `refresh` : reload the current state of a loaded entity
* `remove` : Remove an entity from the persistence context (delete, no instance returned)
* `detach` : Remove an entity from the persistence context but keep it detached (delete and remove ID from returned instance)

`EntityManager` is also the entry point to make more complex data manipulation as queries, criteria,...

## Data manipulation

### Queries

Queries are an easy way to make data manipulation. It's very close to a SQL query execution.

#### JPQL

As JPA is implementation agnostic, it does not use `SQL` but `JPQL`. Both syntax are very closed but
JPQL is talking about entities, you are querying entities and filtering on attributes, relations are known, you don't have to 
set joins if the relations are defined in the entities.

The folowing code will return the Parent with ID `parentId` with all its children:
```Java
Parent parent = em.createQuery("SELECT p FROM Parent p WHERE p.id=:parent_id", Parent.class)
                              .setParameter("parent_id", parentId)
                              .getSingleResult();
```

The following code will return a list of Child with a parent named "John":
```Java
List<Child> children = em.createQuery("SELECT c FROM Child c WHERE c.parent.name=:parent_name", Child.class)
                              .setParameter("parent_name", "John")
                              .getResultList();
```

#### Named queries

To get queries and entities together, you can store often used queries directly in the entity and 
give them names. It's called `named queries`.

```Java
@Entity
@NamedQueries({
  @NamedQuery(
    name="findAllParentsWithName",
    query="SELECT p FROM Parent p WHERE p.name LIKE :parentName"),
  @NamedQuery(
    name="findAllParentsOlderThen",
    query="SELECT p FROM Parent p WHERE p.birthday < :searchDate")
})
public class Parent {

  @Id
  private UUID id;
  
  @Column
  private String name;
  
  @Column
  private LocalDate birthday;
}
```

You can use thous named queries through the entity manager

```Java
List<Parent> parents = em.createNamedQuery("Parent.findAllParentsWithName", Parent.class)
                              .setParameter("parentName", "John")
                              .getResultList();
```

#### Native queries

If you need to use a specific platform SQL syntax which has no equivalent in JPQL, you can use native
SQL syntax. This must be used only when mandatory because you're linking your application to a specific
database implementation.

```Java
Query q = em.createNativeQuery("SELECT p.name, to_date(p.birthday,'dd/MM/YY') FROM parent_table p WHERE p.id = :id");
q.setParameter("id", UUID.fromString("f83f5cf7-a662-4d62-99d5-ea1042a322e7"));
Object[] parent = (Object[]) q.getSingleResult();
```

> In a native query, use the database tables and columns name, not entities one

### Criteria

When a request must be dynamically generated, you can use the `criteria` API. A criteria is an object
aggregating filters, predicates,... to create complex where clause, grouping,...

How to generate this sql in criteria ?

```SQL
SELECT c FROM Country c WHERE c.population > :p
```

```Java
CriteriaBuilder cb = em.getCriteriaBuilder();
 
  CriteriaQuery<Country> q = cb.createQuery(Country.class);
  Root<Country> c = q.from(Country.class);
  ParameterExpression<Integer> p = cb.parameter(Integer.class);
  q.select(c).where(cb.gt(c.get("population"), p));
```

Criteria can quickly be complex but allows a lot of dynamic features.

```Java
CriteriaQuery<Pet> cq = cb.createQuery(Pet.class);
Root<Pet> pet = cq.from(Pet.class);
cq.groupBy(pet.get(Pet_.color));
cq.having(cb.in(pet.get(Pet_.color)).value("brown").value("blonde"));
```

```Java
Map<SingularAttribute<Transaction, ?>, Object> params = ...;
CriteriaBuilder cb = em.getCriteriaBuilder();           
CriteriaQuery<Tuple> cq = cb.createTupleQuery();     
Root<Transaction> r = cq.from(Transaction.class);

Predicate p= cb.conjunction();
for (Map.Entry<SingularAttribute<Transaction, ?>, Object> param: params.entrySet())
    p = cb.and(p, cb.equal(r.get(param.getKey()), param.getValue()));

cq.multiselect(r.get(Transaction_.id), r.get(Transaction_.status), 
          r.get(Transaction_.created_at))
    .where(p)
    .orderBy(cb.asc(r.get(Transaction_.id)));

List<Tuple> result = em.createQuery(cq).getResultList();
```
### Entity graph

Lazy loading was often an issue with JPA 2.0. You have to define at the entity if you want to use 
FetchType.LAZY (default) or FetchType.EAGER to load the relation and this mode is always used. 
FetchType.EAGER is only used if we want to always load the relation. FetchType.LAZY is used in 
almost all of the cases to get a well performing and scalable application.

But this is not without drawbacks. If you have to use an element of the relation, you need to make 
sure, that the relation gets initialized within the transaction that load the entity from the 
database. This can be done by using a specific query that reads the entity and the required 
relations from the database. But this will result in use case specific queries. Another option 
is to access the relation within your business code which will result in an additional query for 
each relation. Both approaches are far from perfect.

JPA 2.1 entity graphs are a better solution for it. The definition of an entity graph is independent 
of the query and defines which attributes to fetch from the database. An entity graph can be used 
as a fetch or a load graph. If a fetch graph is used, only the attributes specified by the entity 
graph will be treated as FetchType.EAGER. All other attributes will be lazy. If a load graph is 
used, all attributes that are not specified by the entity graph will keep their default fetch type.

#### Example

For this example we will use an order with a list of items and each item has a product. All 
relations are lazy.

**The Order entity**
```Java
@Entity
@Table(name = "purchaseOrder")
@NamedEntityGraph(name = "graph.Order.items", 
               attributeNodes = @NamedAttributeNode(value = "items", subgraph = "items"), 
               subgraphs = @NamedSubgraph(name = "items", attributeNodes = @NamedAttributeNode("product")))
public class Order implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String orderNumber;

   @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
   private Set<OrderItem> items = new HashSet<OrderItem>();

   ...
   
```

**The OrderItem entity**
```Java
@Entity
public class OrderItem implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private int quantity;

   @ManyToOne
   private Order order;

   @ManyToOne(fetch = FetchType.LAZY)
   private Product product;
   
```

**The Product entity**
```Java
@Entity
public class Product implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String name;
```

**Named entity graph**

The definition of a named entity graph is done by the `@NamedEntityGraph` annotation at the entity.
It defines a unique name and a list of attributes (the attributeNodes) that have be loaded.
The following example shows the definition of the entity graph `graph.Order.items` which will load 
the list of `OrderItem` of an `Order`.

```Java
@Entity
@Table(name = "purchase_order")
@NamedEntityGraph(name = "graph.Order.items", 
      attributeNodes = @NamedAttributeNode("items"))
public class Order implements Serializable {

   ...
```

Now that we have defined the entity graph, we can use it in a query. Therefore we need to create a 
Map with query hints and set it as an additional parameter on a find or query method call.
The following code snippet shows how to use a named entity graph as a fetch graph in a find statement.

```Java
EntityGraph graph = this.em.getEntityGraph("graph.Order.items");

Map hints = new HashMap();
hints.put("javax.persistence.fetchgraph", graph);

return this.em.find(Order.class, orderId, hints);
```

**Named sub graph**

We used the entity graph to define the fetch operation of the Order entity. If we want to do the 
same for the OrderItem entity, we can do this with an entity sub graph. The definition of a named 
sub graph is similar to the definition of an named entity graph and can be referenced as an 
attributeNode.

The following code snippets shows the definition of a sub graph to load the Product of each 
OrderItem. The defined entity graph will fetch an Order with all OrderItems and their Products.

```Java
@Entity
@Table(name = "purchase_order")
@NamedEntityGraph(name = "graph.Order.items", 
               attributeNodes = @NamedAttributeNode(value = "items", subgraph = "items"), 
               subgraphs = @NamedSubgraph(name = "items", attributeNodes = @NamedAttributeNode("product")))
public class Order implements Serializable {
```
  
What’s happening inside?

OK, from a development point of view entity graphs are great. They are easy to use and we do not 
need to write additional code to avoid lazy loading issues. But what is happening inside? How many 
queries are send to the database? Lets have a look at the hibernate debug log.

```LOG
2014-03-22 21:56:08,285 DEBUG [org.hibernate.loader.plan.build.spi.LoadPlanTreePrinter] (pool-2-thread-1) LoadPlan(entity=blog.thoughts.on.java.jpa21.entity.graph.model.Order) - Returns - EntityReturnImpl(entity=blog.thoughts.on.java.jpa21.entity.graph.model.Order, querySpaceUid=<gen:0>, path=blog.thoughts.on.java.jpa21.entity.graph.model.Order) - CollectionAttributeFetchImpl(collection=blog.thoughts.on.java.jpa21.entity.graph.model.Order.items, querySpaceUid=<gen:1>, path=blog.thoughts.on.java.jpa21.entity.graph.model.Order.items) - (collection element) CollectionFetchableElementEntityGraph(entity=blog.thoughts.on.java.jpa21.entity.graph.model.OrderItem, querySpaceUid=<gen:2>, path=blog.thoughts.on.java.jpa21.entity.graph.model.Order.items.<elements>) - EntityAttributeFetchImpl(entity=blog.thoughts.on.java.jpa21.entity.graph.model.Product, querySpaceUid=<gen:3>, path=blog.thoughts.on.java.jpa21.entity.graph.model.Order.items.<elements>.product) - QuerySpaces - EntityQuerySpaceImpl(uid=<gen:0>, entity=blog.thoughts.on.java.jpa21.entity.graph.model.Order) - SQL table alias mapping - order0_ - alias suffix - 0_ - suffixed key columns - {id1_2_0_} - JOIN (JoinDefinedByMetadata(items)) : <gen:0> -> <gen:1> - CollectionQuerySpaceImpl(uid=<gen:1>, collection=blog.thoughts.on.java.jpa21.entity.graph.model.Order.items) - SQL table alias mapping - items1_ - alias suffix - 1_ - suffixed key columns - {order_id4_2_1_} - entity-element alias suffix - 2_ - 2_entity-element suffixed key columns - id1_0_2_ - JOIN (JoinDefinedByMetadata(elements)) : <gen:1> -> <gen:2> - EntityQuerySpaceImpl(uid=<gen:2>, entity=blog.thoughts.on.java.jpa21.entity.graph.model.OrderItem) - SQL table alias mapping - items1_ - alias suffix - 2_ - suffixed key columns - {id1_0_2_} - JOIN (JoinDefinedByMetadata(product)) : <gen:2> -> <gen:3> - EntityQuerySpaceImpl(uid=<gen:3>, entity=blog.thoughts.on.java.jpa21.entity.graph.model.Product) - SQL table alias mapping - product2_ - alias suffix - 3_ - suffixed key columns - {id1_1_3_} 

2014-03-22 21:56:08,285 DEBUG [org.hibernate.loader.entity.plan.EntityLoader] (pool-2-thread-1) Static select for entity blog.thoughts.on.java.jpa21.entity.graph.model.Order [NONE:-1]: select order0_.id as id1_2_0_, order0_.orderNumber as orderNum2_2_0_, order0_.version as version3_2_0_, items1_.order_id as order_id4_2_1_, items1_.id as id1_0_1_, items1_.id as id1_0_2_, items1_.order_id as order_id4_0_2_, items1_.product_id as product_5_0_2_, items1_.quantity as quantity2_0_2_, items1_.version as version3_0_2_, product2_.id as id1_1_3_, product2_.name as name2_1_3_, product2_.version as version3_1_3_ from purchase_order order0_ left outer join OrderItem items1_ on order0_.id=items1_.order_id left outer join Product product2_ on items1_.product_id=product2_.id where order0_.id=? 
```

The log shows that only one query is created. Hibernate uses the entity graph to create a load plan 
with all 3 entities (Order, OrderItem and Product) and load them with one query.

#### Dynamic graph

You can also create a graph dynamically without defining it in the entity. Remember that in a graph 
mode, all attributes are lazily loaded except if you specify a `@Basic` annotation to force eager mode.

```Java
@Entity
public class EmailMessage implements Serializable {
    @Id    
    String messageId;
    @Column
    @Basic(fetch=EAGER)
    String subject;
    @Column
    String body;
    @Column
    String sender;
    @OneToMany(mappedBy="message", fetch=LAZY)
    Set<EmailAttachment> attachments;
    ...
}
```

With a `fetch graph`, you force a graph without taking care of existing annotations. This example will
load only the `body` attribute ignoring existing predicates :

```Java
EntityGraph<EmailMessage> eg = em.createEntityGraph(EmailMessage.class);
eg.addAttributeNodes("body");
...
Properties props = new Properties();
props.put("javax.persistence.fetchgraph", eg);
EmailMessage message = em.find(EmailMessage.class, id, props);
```

If you want to customize existing graph definitions or `@Basic` predicates, use a `load graph`.
This example will load the entire entity without attachments but including the body :

```Java
EntityGraph<EmailMessage> eg = em.createEntityGraph(EmailMessage.class);
eg.addAttributeNodes("body");
...
Properties props = new Properties();
props.put("javax.persistence.loadgraph", eg);
EmailMessage message = em.find(EmailMessage.class, id, props);
```

[Entity Graphs tutorial](https://docs.oracle.com/javaee/7/tutorial/persistence-entitygraphs.htm)

## Spring data integration

Spring provides a way to expose data with a JPA implementation through [Spring Data JPA project](http://projects.spring.io/spring-data-jpa/).

The idea is to provide default DAO methods on an entity. Just create a DAO interface extending `JpaRepository`
and use it...

```Java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
```

The `JpaRepository` must be typed with your entity and the type of the ID. It provides lots of methods
described in the [JpaRepository API](http://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html)

* count()
* delete(ID)
* delete(Entity)
* deleteAll()
* FindOne(ID)
* ...

Data can be paginated or sorted using `PagingAndSortingRepository` methods or versioned using a 
`RevisionRepository`.

Spring data also provides an automatic query tool based on the method name called Query Derivation Mechanism.
By adding a method with a name beginning with `findBy` followed by the name of an attribute of the 
entity, a query will be generated at runtime to get all occurrences of the entity with the 
corresponding attribute value

```Java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
}
```
This method declaration will generate automatically this code at runtime without implementing yourself:
```Java
return em.createQuery("SELECT c from Customer c where c.lastName = :lastName",Customer.class)
  .setParameter("lastName",lastName)
  .getResultList();
```

## Reference

* [The Java EE Tutorial : Persistence](http://docs.oracle.com/javaee/7/tutorial/partpersist.htm#BNBPY)
* [JPA API reference](https://docs.oracle.com/javaee/7/api/javax/persistence/package-summary.html)
* [Java persistence wiki book](https://en.wikibooks.org/wiki/Java_Persistence)