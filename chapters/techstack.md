## Tech Stack
[Dropwizard 0.7.x](http://dropwizard.io/), with
[Swagger](http://swagger.io/) for **API docs**, and
[JDBI](http://jdbi.org/) as the "**ORM**" to our relational db should we use one, with MongoDB for **persistency**, 
which will all be set up/provisioned using
[Vagrant](https://www.vagrantup.com/) and [Docker](https://www.docker.com/) for **provisioning**/**Infrastructure as code**.

We'll eventually handle **authentication and authorization** using
[Apache Shiro](http://shiro.apache.org/) with the [Dropwizard Plugin](https://github.com/silb/dropwizard-shiro), with
[StormPath](https://stormpath.com/) as our **User & Access Management** backend.


We're getting continuous feedback thanks to
[TravisCI](https://travis-ci.org/) as our **Continuous Integration**, which will trigger
[Gradle](http://www.gradle.org/) **build(s)** to run our tests, of which some might use
the [rest-scenarios](https://github.com/cegeka/rest-scenarios) framework to make some readable **scenario tests**.

### Notable Changes
#### No more Guice, just Dependency Injection
We were using [Guice](https://github.com/HubSpot/dropwizard-guice) for **Dependency Injection**, and then stopped doing that in favor of building `components` using Dropwizard Bundles.

This way our code remains nicely encapsulated, but is still bootstrappable in DropWizard.