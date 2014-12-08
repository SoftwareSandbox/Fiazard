Fiazard
=======

Learning project in which we try to make our sandwich ordering site better. This is the backend project, providing RESTful webservices with DropWizard.

## Tech Stack
[Dropwizard 0.7.x](http://dropwizard.io/), in combination with 
[Guice](https://github.com/HubSpot/dropwizard-guice) for **Dependency Injection**, and
[Swagger](http://swagger.io/) for **API docs**, and
[JDBI](http://jdbi.org/) as the "**ORM**" to our relational db should we use one, with 
MySQL/Postgres/MongoDB/... (TBD) for **persistency**, 
which will all be set up/provisioned using
[Vagrant](https://www.vagrantup.com/)/[Docker](https://www.docker.com/)/... (TBD) for **provisioning**/Infrastructure as code.


We'll eventually handle authentication and authorization using
[Apache Shiro](http://shiro.apache.org/) with the [Dropwizard Plugin](https://github.com/silb/dropwizard-shiro), with
[StormPath](https://stormpath.com/) as our User Management backend.


We'll eventually get continuous feedback thanks to
[TravisCI](https://travis-ci.org/)/[Codeship](http://codeship.io/) (TBD) as our **Continuous Integration**, which will trigger
[Gradle](http://www.gradle.org/) **build(s)** to run our tests, of which some might use
the [rest-scenarios](https://github.com/cegeka/rest-scenarios) framework to make some readable **scenario tests**.
