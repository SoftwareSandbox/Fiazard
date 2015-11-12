# How to contribute
## Setting up your environment
Fork [this repo](https://github.com/SoftwareSandbox/Fiazard/) into your own account, e.g. `git@github.com:MyGithubAccount/Fiazard.git` and clone your forked repo locally:  
```
git clone https://github.com/MyGithubAccount/Fiazard
git remote add swsb https://github.com/SoftwareSandbox/Fiazard
```  

Install [Vagrant](http://vagrantup.com) and [VirtualBox](https://www.virtualbox.org/wiki/Downloads).

### On Ubuntu
Download Vagrant through https://www.vagrantup.com/downloads.html because `apt-get install vagrant` installs an older version.

You'll have to download oracle's Java 8 since openjdk still has no package out yet:
```
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
sudo apt-get install oracle-java8-set-default
```
## Vagrant
Open up a shell, navigate to the vagrant dir and type `vagrant up`. This will set up an environment with MongoDB and EventStore.

The [Vagrantfile](vagrant/Vagrantfile) downloads an Ubuntu 14.x VM and handles port forwarding. It uses [Docker](https://www.docker.com) to provision the VM with MongoDB and EventStore. This is configured in [docker-compose.yml](vagrant/docker-compose.yml).

### MongoDB
After installing [Robomongo](http://robomongo.org/), add a connection to `localhost:27017`.

### EventStore
Access the Web UI via [http://localhost:2113/web/index.html](http://localhost:2113/web/index.html).

## Gradle
There's no need to download and install Gradle, use the gradle wrapper (gradlew in the root) to run your builds.

### Running your Dropwizard Application locally
Simply run 
```
gradlew startAppDev
```
or make a target in your IDE that runs `FiazardApp.java` with arguments `server src/main/resources/dev.yml`.

## Pick up backlog items
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
