## Mobiquity Assignment
### Assignment: Package Challenge

#### Prerequisite:
- Java JDK 15
- Multithreading - optimistic locking
- Spring boot
  
#### Testing
The application is made with the spring of TDD, and that is the reason it contains a lot of unit tests. Integration test are also added. Unit and integration tests both are added in seperate directories you can find.

#### Technologies
- Java 15
- Spring boot
- Spring AOP
- TDD & Integration testing
- Junit5
- Openpojo
- Apache commons
- Lombok
- Initialization On Demand Holder Pattern
- Builder Pattern
- Single Responsibilities

#### Thread safety
For thread safety, i am using java 1.8 StampedLock, in which we have optimistic read lock. Which make synchronization overhead is very low.
#### Data Structure
There can be many ways to solve this problem like using dynamic programming, But i choose to solve this problem,
with fast as well as keeping in mind of lightweight solution. keeping in mind of time and space complexity i decided to use the mixture of
<strong>Heap (PriorityQueue)</strong> of java and <b>Map</b>
I created in this customized datastructures into a class name <b>KnapSackQueue</b> which is a class collect and sort products keeping in mind of knapsack algorithm.

#### Built With
- Java
- Spring Boot
- Maven
- Clean And Build
- mvn clean install

#### Build And Test
- mvn clean test

#### Authors
- Aqib Javed
- Thanks to Mobiquity for this assignment
