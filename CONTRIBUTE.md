#How to contribute

##Fork this repo

Fork it baby!

## Setting up your environment
Clone this repo using `git clone --recursive`. This will also clone the repo in the vagrant folder, which will set up your MongoDB environment.

Install [Vagrant](http://vagrantup.com) and [VirtualBox](https://www.virtualbox.org/wiki/Downloads).

Open up a shell, navigate to the vagrant dir and type `vagrant up`. This will download an Ubuntu 14.x VM box and install MongoDB Client and Server 2.6. For more info check this [forked repo](https://github.com/Sch3lp/ubuntu1404-mongodb26).

After installing [Robomongo](http://robomongo.org/), add a connection to `10.11.12.13:27017`.

##On Ubuntu

Download Vagrant through https://www.vagrantup.com/downloads.html because `apt-get install vagrant` installs an older version.

You'll have to download oracle's Java 8 since openjdk still has no package out yet:
```ssh
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
$ sudo apt-get install oracle-java8-set-default
```

##Never forget...

* To sync with master before starting on new issue/feature. (with `git pull --rebase upstream master`)
* To rebase your commits into one before submitting pull requests.
* To have fun!

