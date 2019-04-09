# Testausdokumentti

## Mitä on testattu, miten tämä tehtiin

Ohjelmassa on käytössä JUnitilla tehdyt automaattiset testit itse ohjelmalle domain-paketissa ja itsetehdyillä tietorakenteille datastructure-paketissa. Käyttöliittymä on rajattu testauksen ulkopuolelle. Testauksen rivikattavuutta tarkastellaan jacocon avulla.

Käyttöliittymästä on olemassa konsolissa toimiva versio, jotta ohjelman toimintaa voi henkilö mukavasti testata sekä tiedostoa lukeva versio, jotta ohjelman suorituskykyä voisi verrata grep-komennon kanssa terminaalissa. Ohjelman rakenteesta johtuen suorituskykyvertailua hiukan vääristää se, että alussa sille syötetään "matcher" ja lopussa "/no" ja "quit", mutta suurien tiedostojen kanssa tällä ei pitäisi olla isoa merkitystä.

## Suorituskykytestaus

Testausta varten on tehty testi1.txt, testi2.txt ja testi3.txt -tiedostot, joissa on 0-19 merkin pituisia sanoja merkeistä a, b ja c tehtynä. Ensimmäisessä on 1000 sanaa, toisessa 1 000 000 sanaa ja kolmannessa 1 000 000 000 sanaa. Ainoastaan testi1.txt on versionhallinnassa mukana.

## Miten testit voidaan toistaa

### Komentorivi

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

## Ohjelman toiminnan empiirisen testauksen tulosten esittäminen graafisessa muodossa.
