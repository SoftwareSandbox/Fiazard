#How to contribute

##Clone this repo

Clone this repo (using an url of your choosing), e.g. `git@github.com:Sch3lp/Fiazard.git`, and start coding.

##Never forget...

* To work in small commits.
* To branch early and branch often.

##We experimented with pull requests
But decided we wanted to prioritize learning the tech frameworks and coding etc. then to learn Git.

Here are our *Never forgets* for working with pull-requests:

## Setting up your environment

Clone this repo using `git clone --recursive`. This will also clone the repo in the vagrant folder, which will set up your MongoDB environment.

Install [Vagrant](http://vagrantup.com) and [VirtualBox](https://www.virtualbox.org/wiki/Downloads).

Open up a shell, navigate to the vagrant dir and type `vagrant up`. This will download an Ubuntu 14.x VM box and install MongoDB Client and Server 2.6. For more info check this [forked repo](https://github.com/Sch3lp/ubuntu1404-mongodb26).

After installing [Robomongo](http://robomongo.org/), add a connection to `10.11.12.13:27017`.

### On Ubuntu

Download Vagrant through https://www.vagrantup.com/downloads.html because `apt-get install vagrant` installs an older version.

You'll have to download oracle's Java 8 since openjdk still has no package out yet:
```ssh
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
$ sudo apt-get install oracle-java8-set-default
```

There's no need to download and install Gradle, use the gradlewrapper (gradlew in the root) to run your builds.

### Running your Dropwizard Application locally

Make a target in your IDE that runs `FiazardApp.java` with arguments `server src/main/resources/dev.yml`.

## Never forget...

* To sync with master before starting on new issue/feature. (with `git pull --rebase upstream master` see this [cool article](http://kentnguyen.com/development/visualized-git-practices-for-team/) on the why)
* To rebase your commits into one before submitting pull requests.
* To have fun!

## Some Conventions

Always suffix Representation classes (java classes that will be transformed into JSON by Jackson) with an R.

`OpeningHour.java` becomes `OpeningHourR.java`
