# sherlock

An early analytics experiment — a Java analytics API paired with a small
JavaScript front end that talks to the DOM.

It was a first attempt at building an analytics service end to end: a Java/Maven
backend persisting through Hibernate, served with Jetty, and a browser-side
application under `src/main/webapp` to exercise it.

## Stack

- Java 7, Maven 3
- Hibernate (configured in `src/main/resources/hibernate.cfg.xml`)
- jQuery front end under `src/main/webapp`, built with Grunt

## Build & run

```sh
mvn package      # build the WAR
mvn jetty:run    # run the sample web application
```

## License

[MIT](LICENSE)
