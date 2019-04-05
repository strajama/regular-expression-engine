# Käyttöohje

## Komentorivitoiminnot

Avaa sovellus komennolla mvn compile exec:java -Dexec.mainClass=app.Main

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```
generoi hakemistoon _target_ suoritettavan jar-tiedoston regular-expression-engine-1.0-SNAPSHOT.jar

### Javadoc

JavaDoc on luotavissa komennolla 

```
mvn javadoc:javadoc
```
