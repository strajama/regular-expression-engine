# Määrittelydokumentti

## Mitä algoritmeja ja tietorakenteita toteutat työssäsi

Toteutan Thompsonin [algoritmin](https://en.wikipedia.org/wiki/Thompson%27s_construction), jossa luodaan epädeterministinen äärellinen automaatti. Sen toteutuksessa tarvitaan pino ja lista. Algoritmin ymmärtämisessä on hyötyä, jos on tutustunut Laskennan mallit -kurssin sisältöön.

### Omat tietorakenteet

Stack-rajapinta toteuttaa pinon ja tuntee metodit push, pop, peek ja empty. Sen toteuttaa kaksi eri luokkaa: CharStack joka käsittelee merkkejä ja NfaStack, joka käsittelee Nfa-olioita.

StateList toteuttaa lista-toiminnallisuuden State-olioille. Se tuntee metodit add, contains ja getStates.

## Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet

Ratkaistava ongelma on säännöllisten lausekkeiden tulkki ja lähden ratkomaan ongelmaa Ken Thompsonin vuonna 1968 esittelemällä [algoritmilla](https://en.wikipedia.org/wiki/Thompson%27s_construction). Valitsin lopullisesti Thompsonin toteutuksen ongelmaan sen jälkeen, kun luin [kirjoituksen](https://swtch.com/~rsc/regexp/regexp1.html) siitä miten se joissakin erikoistapauksissa on nopeampi kuin yleisesti käytetyt toteutustavat ja tulin uteliaaksi siitä.

## Mitä syötteitä ohjelma saa ja miten näitä käytetään

Ohjelma saa syötteenä säännöllisen lausekkeen (regular expression) ja sanan tai sanoja ja tulostaa sanan/sanat, jotka säännöllinen lauseke tunnistaa.

### Ohjelman tunnistamat merkit
* "*" tarkoittaa, että jokin asia toistuu 0 - monta kertaa
* "|" tarkoittaa, että jompikumpi ehdoista on voimassa
* "+" tarkoittaa, että jokin asia toistuu 1 - monta kertaa
* "." tarkoittaa mitä tahansa symbolia
* ohjelma osaa huomioida sulut () ja käsitellä niiden sisällön ennen muita.

## Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)

Thompsonin algoritmit aikavaativuus on O(nm) ja tilavaativuus O(m), jossa n on annetun, läpikäytävän tekstin pituus ja m säännöllisen lausekkeen pituus, [lähde](https://arxiv.org/pdf/cs/0606116.pdf). Tavoitteena on päästä mahdollisimman lähelle tätä.

## Lähteet

Aiemmin linkitetyt sekä
[Implementing a Regular Expression Engine](https://deniskyashif.com/implementing-a-regular-expression-engine/)
