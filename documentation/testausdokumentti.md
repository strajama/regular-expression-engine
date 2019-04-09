# Testausdokumentti

## Mitä on testattu, miten tämä tehtiin

Ohjelmassa on käytössä JUnitilla tehdyt automaattiset testit itse ohjelmalle domain-paketissa ja itsetehdyillä tietorakenteille datastructure-paketissa. Käyttöliittymä on rajattu testauksen ulkopuolelle. Testauksen rivikattavuutta tarkastellaan jacocon avulla.

Käyttöliittymästä on olemassa konsolissa toimiva versio, jotta ohjelman toimintaa voi henkilö mukavasti testata sekä tiedostoa lukeva versio, jotta ohjelman suorituskykyä voisi verrata grep-komennon kanssa terminaalissa. Ohjelman rakenteesta johtuen suorituskykyvertailua hiukan vääristää se, että alussa sille syötetään "matcher" ja lopussa "/no" ja "quit", mutta suurien tiedostojen kanssa tällä ei pitäisi olla isoa merkitystä.

## JUnit-testaus

JUnitilla testattiin ohjelman ydintoiminnallisuus ja omat tietorakenteet. Rivikattavuus on tarpeeksi kattava.

![](https://github.com/strajama/regular-expression-engine/blob/master/documentation/kuvia/all.png)

![](https://github.com/strajama/regular-expression-engine/blob/master/documentation/kuvia/domain.png)

Yksi neljästä haarasta jää testaamatta yhdessä kohtaa Postfix-luokan toPostfix-metodissa.

![](https://github.com/strajama/regular-expression-engine/blob/master/documentation/kuvia/postfix.png)

![](https://github.com/strajama/regular-expression-engine/blob/master/documentation/kuvia/toPostfix.png)

## Suorituskykytestaus

Suorituskykyvertailutestausta varten tehtiin aluksi testi1.txt, testi2.txt ja testi3.txt -tiedostot, joissa on 0-19 merkin pituisia sanoja merkeistä a, b ja c tehtynä. Ensimmäisessä on 1000 sanaa, toisessa 1 000 000 sanaa ja kolmannessa 1 000 000 000 sanaa. Ainoastaan testi1.txt on versionhallinnassa mukana.

Koska yksinkertaisella "a*" -kielellä kokeiltaessa grep jäi pyörimään hyvin pitkäksi aikaa, niin tehtiin vielä 100 miljoonan sanan testi4.txt -tiedosto ja 10 miljoonan sanan testi5.txt -tiedosto.

Kielellä "a*" kesti testi3.txt -tiedoston ajaminen grepillä real: 57 min 42,291 s; user: 55 min 17,147 s; sys: 2 min 5,257 s. Tämän katsottiin olevan niin pitkä aika, että kyseisellä tiedostolla testaaminen ei enää palvele tämän työn suorituskykyvertailun tarkoitusta.

Suorituskykyvertailua varten annettiin komento

```
time grep -w -E "kieli" "testitiedosto.txt"
```

ja sen tulostamat ajat kirjattiin ylös.

Omassa ohjelmassa muokattiin ohjelmaa niin, että Main-luokassa annetaan App-luokalle FileIO-parametrina ja FileIO-luokalle parametreina "kieli" ja "tekstitiedosto.txt", jonka jälkeen ohjelma ajettiin.

## Miten testit voidaan toistaa

### Komentorivi

JUnit-testit suoritetaan komennolla

```
mvn test
```

ja niiden testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suorituskykytestit

Koska suorituskykytestauksessa käytetyt testitiedostot olivat niin suuria eivät ne ole versionhallinnan piirissä. 

Testituloksia voi toistaa luomalla tekstitiedoston, jossa on satunnaisesti a-, b- ja c-merkkejä 0-19 merkin pituisissa sanoissa jokainen sana omalla rivillään. Tiedoston ensimmäisellä rivillä tulee lukea "matcher", toiseksi viimeisellä "/no" ja viimeisellä "quit".

## Ohjelman toiminnan empiirisen testauksen tulosten esittäminen graafisessa muodossa.
