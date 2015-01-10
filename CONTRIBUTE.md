#How to contribute

##Fork this repo

Fork it baby!

##On Ubuntu

Download Vagrant through https://www.vagrantup.com/downloads.html because `apt-get install vagrant` installs an older version.

You'll have to download oracle's Java 8 since openjdk still has no package out yet:
```ssh
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
```

##Never forget...

* To sync with master before starting on new issue/feature. (with `git pull --rebase upstream master`)
* To rebase your commits into one before submitting pull requests.
* To have fun!

