# Käyttöohje

Ohjelmaa voi käyttää joko komentoriville sanoja kirjoittamalla tai antamalla sille valmis tekstitiedosto. Oletuksena ohjelma avautuu konsoliversion. 
## Ohjelman ConsoleIO:n käyttäminen

Ohjelma toimii komentoriville kirjoitettavilla käskyillä. Aluksi pitää valita jokin kolmesta: postfix, matcher tai quit.

* Postfix-komennolla voit katsoa miltä kieli näyttää ohjelman käsittelemässä postfix-muodossa, josta on muokattu ylimääräiset merkit pois.
* Matcher-komento kysyy ensin kieltä, jonka sanoja halutaan tunnistaa. Sen jälkeen sille voi kirjoittaa sanoja ja ohjelma palauttaa sanan näytölle, jos se tunnistaa sen. Jos kirjoittaa "/no", niin ohjelma lopettaa sanojen tunnistamisen.
* Quit-komento sulkee ohjelman.

## Ohjelman FileIO:n käyttäminen

FileIO-version saa käyttöön muokkaamalla Main-luokkaan app.init -riville "new ConsoleIO()" tilalle "new FileIO(String kieli, String tekstitiedosto)", jossa "kieli" on kieli, joka halutaan tunnistaa, esim. "a*|b*" ja tekstitiedosto projektin juuressa oleva txt-päätteinen tiedosto. Projektissa on tällä hetkellä valmiina testi1.txt ja testi2.txt kokeiltavaksi.

Ohjelman ajamalla näytölle tulostuu kaikki tiedoston sanat, jotka kuuluvat annettuun kieleen.

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
