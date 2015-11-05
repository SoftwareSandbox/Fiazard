#How to contribute

## Setting up your environment
Fork [this repo](https://github.com/SoftwareSandbox/Fiazard/) into your own account, e.g. `git@github.com:MyGithubAccount/Fiazard.git` and clone your forked repo locally:  
```
git clone https://github.com/MyGithubAccount/Fiazard --recursive
git remote add swsb https://github.com/SoftwareSandbox/Fiazard
```  
This will also clone the repo in the vagrant folder, which will set up your MongoDB environment.

Install [Vagrant](http://vagrantup.com) and [VirtualBox](https://www.virtualbox.org/wiki/Downloads).

Open up a shell, navigate to the vagrant dir and type `vagrant up`. This will download an Ubuntu 14.x VM box and install MongoDB Client and Server 2.6. For more info check this [forked repo](https://github.com/Sch3lp/ubuntu1404-mongodb26).

After installing [Robomongo](http://robomongo.org/), add a connection to `10.11.12.13:27017`.

### On Ubuntu
Download Vagrant through https://www.vagrantup.com/downloads.html because `apt-get install vagrant` installs an older version.

You'll have to download oracle's Java 8 since openjdk still has no package out yet:
```ssh
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
sudo apt-get install oracle-java8-set-default
```

## EventStore

Install [Docker](https://www.docker.com/) on Ubuntu, or [DockerToolbox](https://www.docker.com/docker-toolbox) on Windows.
DockerToolbox will start Docker inside a VirtualBox machine. Make sure you have a recent (5.0.3 or up) VirtualBox installation. Otherwhise, you might get an IP conflict between Windows and Docker-machine.

Pull the [adbrowne/eventstore](https://hub.docker.com/r/adbrowne/eventstore/) docker container and run it with
```ssh
sudo docker run -d -p 2113:2113 -p 1113:1113 adbrowne/eventstore
```
Access the Web UI via [http://localhost:2113/web/index.html](http://localhost:2113/web/index.html).
On Windows, you'll have to replace 'localhost' with the IP address of the VirtualBox Machine that Docker runs in.
Check your Docker terminal for the correct IP.

Alternatively, on Windows, you can install [EventStore](http://geteventstore.com) natively without using Docker.

## Gradle
There's no need to download and install Gradle, use the gradlewrapper (gradlew in the root) to run your builds.

### Running your Dropwizard Application locally
Simply run 
```
gradlew startAppDev
```
or make a target in your IDE that runs `FiazardApp.java` with arguments `server src/main/resources/dev.yml`.

##Pick up backlog items
1. Pick up a backlog item at [waffle.io](https://waffle.io/softwaresandbox/fiazard) and assign it to yourself
2. Make sure you are in sync with the latest version of the repository:  
  `git pull swsb master`
3. create a local branch for the feature you will be implementing (eg feature-branch-{featureId}):  
  `git checkout -b feature-branch-{featureId}`
4. commit your changes to the local branch + push them to your forked repo:  
  `git commit -m 'connect #{featureId} {my specific comments}'`
5. push your changes to your forked repo (use -u to push the feature branch):  
  `git push origin -u feature-branch-{featureId}`
6. create a pull request in github
7. [Travis ci](https://travis-ci.org/SoftwareSandbox/Fiazard) will automatically pick up your changes and build
8. When the build is green, a contributor will accept/reject your changes + remove the feature-branch in order not to pollute the repo.
9. Remove your feature branch locally (not necessary - but will make your local repo cleaner):  
  `git branch -d feature-branch-{featureId}`
10. Remove your remote feature branch:  
  `git push --delete origin feature-branch-{featureId}`

## Some Conventions

Always suffix Representation classes (java classes that will be transformed into JSON by Jackson) with an R.

`OpeningHour.java` becomes `OpeningHourR.java`

## Usage of /resources/json/*.json files
If you're not yet using [SublimeText3](http://www.sublimetext.com/3), shame on you. Buy and download it now.

1. Install [Package Control](https://sublime.wbond.net/installation#st3).
2. Install [HttpRequester](https://github.com/braindamageinc/SublimeHttpRequester) in Sublime: `Ctrl+Shift+P`, `Install`, Http Requester.
3. Make sure your FiazardApp is running.
4. Open a .json file in Sublime, e.g. [category.json](src/test/resources/json/categories.json).
5. Press `Ctrl+Alt+R`
