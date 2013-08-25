# sherlock

Analytics API written in Java ([javadoc][]) with a DOM Interface Javascript application.


## Requirements

  JDK 1.7; Maven 3.x; Windows 7.

## Building + Running the Web Application Sample

The project uses the maven-based build system [Apache Maven][] (version 3.x):

    $ brew update && brew install maven (OSX+homebrew, check website for other OS)
    $ git clone https://github.com/helmedeiros/sherlock.git
    $ cd sherlock
    $ mvn package # build war file
    $ mvn jetty:run # run the web application sample

The repo contains a `pom.xml` file which can be used to build, test and run the project.

You can see the static and dynamic content at http://localhost:8080/.

[javadoc]: http://helmedeiros.github.com/sherlock/javadoc/1.0/com/br/rbs/api/package-summary.html
[Apache Maven]: http://maven.apache.org/