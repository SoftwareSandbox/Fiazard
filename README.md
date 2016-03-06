Fiazard
=======

Learning project in which we try to make our sandwich ordering site better. This is the backend project, providing RESTful webservices with DropWizard to the [FiAngulartje frontend](https://github.com/SoftwareSandbox/FiAngulartje).

[![Build Status](https://travis-ci.org/SoftwareSandbox/Fiazard.svg?branch=master)](https://travis-ci.org/SoftwareSandbox/Fiazard)

[![Stories in Ready](https://badge.waffle.io/softwaresandbox/fiazard.svg?label=ready&title=Ready)](http://waffle.io/softwaresandbox/fiazard)
[![Stories in Ready](https://badge.waffle.io/softwaresandbox/fiazard.svg?label=in%20progress&title=In%20Progress)](http://waffle.io/softwaresandbox/fiazard)

[![Throughput Graph](https://graphs.waffle.io/softwaresandbox/fiazard/throughput.svg)](https://waffle.io/softwaresandbox/fiazard/metrics)

## Architecture Handbook
[Fiazards Architecture Handbook on GitBook.com](https://www.gitbook.com/book/sch3lp/fiazard-arch-handbook), managed by the [architecture-handbook](https://github.com/SoftwareSandbox/Fiazard/tree/architecture-handbook) branch.

## Tech Stack
[Dropwizard 0.9.x](http://dropwizard.io/), in combination with
[Swagger](http://swagger.io/) for **API docs**, and
[JDBI](http://jdbi.org/) as the "**ORM**" to our relational db should we use one, with 
MySQL/Postgres/MongoDB/... (TBD) for **persistency**, and
[EventStore]() as our event store,
which will all be set up/provisioned using
[Docker](https://www.docker.com/) on [Vagrant](https://www.vagrantup.com/) for **provisioning**/Infrastructure as code.

We'll eventually handle **authentication and authorization** using
[Apache Shiro](http://shiro.apache.org/) with the [Dropwizard Plugin](https://github.com/silb/dropwizard-shiro), with
[StormPath](https://stormpath.com/) as our **User & Access Management** backend.


We'll eventually get continuous feedback thanks to
[TravisCI](https://travis-ci.org/)/[Codeship](http://codeship.io/) (TBD) as our **Continuous Integration**, which will trigger
[Gradle](http://www.gradle.org/) **build(s)** to run our tests, of which some might use
the [rest-scenarios](https://github.com/cegeka/rest-scenarios) framework to make some readable **scenario tests**.

## Contributing
Check [this important info](CONTRIBUTE.md) if you're interested in Contributing.
