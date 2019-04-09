# Käyttöohje

Ohjelmaa voi käyttää joko komentoriville sanoja kirjoittamalla tai antamalla sille valmis tekstitiedosto. Oletuksena ohjelma avautuu konsoliversion, koska tekstitiedostoversio on tehty suorituskykytestausta varten.

## Ohjelman ConsoleIO:n käyttäminen

Ohjelma toimii komentoriville kirjoitettavilla käskyillä. Aluksi pitää valita jokin kolmesta: postfix, matcher tai quit.

* Postfix-komennolla voit katsoa miltä kieli näyttää ohjelman käsittelemässä postfix-muodossa, josta on muokattu ylimääräiset merkit pois.
* Matcher-komento kysyy ensin kieltä, jonka sanoja halutaan tunnistaa. Sen jälkeen sille voi kirjoittaa sanoja ja ohjelma palauttaa sanan näytölle, jos se tunnistaa sen. Jos kirjoittaa "/no", niin ohjelma lopettaa sanojen tunnistamisen ja kertoo kuinka monta kielen sanaa sille yhteensä syötettiin.
* Quit-komento sulkee ohjelman.

## Ohjelman FileIO:n käyttäminen

FileIO-version saa käyttöön muokkaamalla Main-luokkaan app.init -riville "new ConsoleIO()" tilalle "new FileIO(String kieli, String tekstitiedosto)", jossa "kieli" on kieli, joka halutaan tunnistaa, esim. "a*|b*" ja tekstitiedosto projektin juuressa oleva txt-päätteinen tiedosto. Projektissa on tällä hetkellä valmiina testi1.txt kokeiltavaksi, jossa on 0-19 merkin pituisia sanoja merkeistä a, b ja c.

### Ohjelman tunnistamat merkit

Ohjelmalle annetaan kieli infix-muodossa, jossa 

* "*" tarkoittaa, että jokin asia toistuu 0 - monta kertaa
* "|" tarkoittaa, että jompikumpi ehdoista on voimassa
* "+" tarkoittaa, että jokin asia toistuu 1 - monta kertaa
* "." tarkoittaa mitä tahansa symbolia
* ohjelma osaa huomioida sulut () ja käsitellä niiden sisällön ennen muita.

Ohjelma ei toimi oikein, jos näitä merkkejä tai merkkiä "·" halutaan käyttää tunnistettavana symbolina.

Esimerkkejä:

* "(ab)*|c*" -kieleen kuuluvat sanat, joissa esiintyy 0 - monta kertaa "ab" tai "c", kuten "", "ab", "c", "ababab", "cccc", mutta ei esimerkiksi "abc".
* "(ab|c)*" -kieleen sen sijaan kuuluu yllä lueteltujen lisäksi myös "abc" sekä "abcccab", "cabc" jne.
* "(ab)+|c+" -kieli eroaa ensimmäisestä siinä, että se ei hyväksy tyhjää merkkijonoa.

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
