# Toteutusdokumentti

## Ohjelman yleisrakenne

Ohjelma rakentaa annetun kielen pohjalta epädeterministinen äärellisen automaatin ([nondeterministic finite automata eli NFA](https://en.wikipedia.org/wiki/Nondeterministic_finite_automaton)), joka tunnistaa kuuluuko sille syötettävä sana kyseiseen kieleen vai ei. Toteutus on Java-versio [tästä](https://deniskyashif.com/implementing-a-regular-expression-engine/) Javascript-ohjelmasta.

### NFA:n rakenne

NFA koostuu tiloista (State) ja siirtymistä (Transition). State-oliolla on tieto siitä onko se hyväksyvä eli lopettava tila, paikka yhdelle symbolisiirtymälle (SymbolTransition) ja kaksipaikkainen taulukko mahdollisille epsilonsiirtymille (EpsilonTransition). Transition-rajapinnan toteuttavilla siirtymiä on kaksi erilaista: SymbolTransition ja EpsilonTransition. Kummallakin on tieto tilasta, josta siirtymä alkaa ja minne se päättyy. Lisäksi SymbolTransitionilla on symboli, jonka pitää mätsätä, jotta siirtymän voi ns. kulkea. Yhdellä State-oliolla voi olla joko yksi SymbolTransition tai 1-2 EpsilonTransition-oliota.

Oman kielensä sanan tunnistava NFA kootaan yhdistämällä pienempiä NFA-olioita yhteen. NFA:lla on aloitustila, josta se aloittaa suorituksen ja johon ei tule siirtymiä mistään muusta automaatin tilasta sekä hyväksyvä eli lopetustila, josta ei ole siirtymiä mihinkään muuhun automaatin tilaan.

Nfa-rajapinnan toteuttaa abstrakti yliluokka NfaClass ja muut Nfa-luokat perivät sen.

Yksinkertaisimmat Nfa:t ovat EpsilonNfa ja SymbolNfa. Molemmissa on yksi alku- ja lopputila (State) ja EpsilonNfa:ssa näiden välillä epsilonsiirtymä (EpsilonTransition) ja SymbolNfa:lla symbolisiirtymä (SymbolTransition). Erilaisia sääntöjä soveltaen näistä perus-Nfa:ista saadaan tehtyä monimutkaisia Nfa:ita.

ConcatNfa-luokalla voi tehdä kahdelle Nfa:lle konkatenaation niin, että tuloksena on Nfa, joka tunnistaa sellaisen kielen sanan, jonka alkuosa toteuttaa ensimmäisenä Nfa:n kielen ja sen perään toisen Nfa:n kielen. Esim. jos tehdään ConcatNfa kahdesta Nfa:sta, joista toinen tunnistaa kielen "a" ja toinen kielen "b", niin ConcatNfa tunnistaa kielen "a·b", johon käytännössä kuuluu vain sana "ab".

UnionNfa-luokalla voi tehdä kahdelle Nfa:lle yhdisteen niin, että tuloksena on Nfa, joka tunnnistaa jomman kumman sille annetun Nfa:n kielen sanan. Esim. jos tehdään UnionNfa taas kielen "a" ja kielen "b" tunnistavista Nfa:ista, niin UnionNfa tunnistaa kielen "a|b", johon kuuluu sanat "a" ja "b".

ClosureNfa-luokalla voi toteuttaa Nfa:lle ns. tähtioperaation eli ClosureNfa tunnistaa kielen, jossa esiintyy sille annetun Nfa:n kielen sanoja nollasta moneen kertaa. Esim. jos tehdään ClosureNfa kielen "a" tunnistavasta Nfa:sta, niin ClosureNfa tunnistaa kielen "a*", johon kuuluu "", "a", "aa", "aaa", jne.

BuilderNfa-luokalle annetaan [postfix-muodossa](http://www.cs.man.ac.uk/~pjj/cs212/fix.html) kieli ja se rakentaa kieltä tulkiten Nfa:n, joka tunnistaa kyseiseen kieleen kuuluvat sanat.

### Postfix

Ohjelmassa muutetaan infix-muodossa annettu kieli postfix-muotoon, jotta operaatiot voidaan toteuttaa helposti niiden [presedenssin](http://mathworld.wolfram.com/Precedence.html) (precedence) mukaan.

Postfix-luokka käsittelee ohjelmalle annetun kielen niin, että siitä voidaan luoda kielen sanat tunnistava BuilderNfa. Aluksi se muuntaa '+'-merkin korvaamalla sen '*'-merkillä ja sen perään merkin tai merkkijonon, joka halutaan esittää vähintään kerran. Tämän jälkeen se lisää kieleen konkatenaatio-operaation tunnistaman merkin '·' ja tämän jälkeen muuntaa kaiken infix-muodosta postfix-muotoon.

Postfix-luokka kommunikoi muiden luokkien kanssa vain palauttamalla kielen muokatussa muodossa toString-metodilla.

### BuilderNfa:n rakentaminen

BuilderNfa-luokan konstruktori käy jokaisen merkin (character) läpi postfix-muodossa syötteenä saamastaan kielestä ja muodostaa siitä yhden ison Nfa-olion.

* Alussa luodaan EpsilonNfa ja jos parametrina on saatu tyhjä merkkijono, niin se jää lopulliseksi Nfa:ksi.
* Luodaan NfaStack, jonne laitetaan Nfa-rajapinnan toteuttavia olioita.
* Käydään parametrina saatua merkkijonoa läpi merkki kerrallaan. Jos kyseessä on jokin Operator-enumin tunnistama operaatio, niin toimitaan sen mukaan, muuten luodaan uusi SymbolNfa kyseisestä merkistä ja pannaan pinoon. 
  * Operaatioista '*' ottaa pinosta päällimmäisen Nfa:n ja tekee siitä uuden ClosureNfa:n, jonka laittaa takaisin pinoon. 
  * Operaatioista '|' ottaa pinosta kaksi Nfa:ta ja laittaa tilalle uuden UnionNfa:n pinoon niin, että jälkimmäisenä pinosta nostettu Nfa on UnionNfa:n ensimmäinen parametri ja ensimmäisenä nostettu toinen parametri. 
  * Operaatioista '·' ottaa pinosta kaksi Nfa:ta ja laittaa tilalle samoilla periaatteilla uuden ConcatNfa:n pinoon.
* Kun koko merkkijono on käyty läpi, niin pinosta otetaan sinne viimeisenä jäänyt Nfa ja määritellään sen olevan BuilderNfa.

### NfaClass:in search-metodi

NfaClass on abstrakti yliluokka, joka toteuttaa Nfa-rajapinnan ja jonka kaikki Nfa-päätteiset luokat perivät. NfaClass:illa on search-metodi, joka käy läpi parametrina annetun sanan ja kertoo tunnistaako kyseinen Nfa sen eli päädytäänkö automaatin läpikäynnillä hyväksyvään tilaan (State, jonka isEnd on "true").

EpsilonTransition on siirtymä, jolla joko siirrytään tai ei-siirrytä kahden tilan välillä ja se on ns. ilmainen eli siirtymän tekeminen ei vie merkkiä merkkijonosta. Epsilon-siirtymien takia automaatti voi olla samaan aikaan monessa eri tilassa ja ne käydään läpi rekursiivisella addNextState-kutsulla, joka palauttaa listan kaikista tiloista, joihin nykyisestä tilasta voi siirtyä epsilon-siirtymillä.

Search-metodi käy läpi parametrina annetun merkkijonon merkki kerrallaan ja pitää listana kirjaa missä kaikissa Nfa:n tiloissa (State) se sillä hetkellä voi olla. Kun uusi merkki luetaan, niin search-metodi päivittää mahdollisten tilojen listan sisältämään uudet mahdolliset tilat. Jos lopussa, kun merkkijono on käyty läpi, jokin mahdollisista tiloista on hyväksyvä tila, niin search-metodi palauttaa "true" merkiksi siitä, että Nfa tunnistaa parametrina annetun sanan.

### Kaikki yhteen

Kaikki kootaan yhteen Matcher-luokalla, joka saa konstruktorin syötteenä kielen String-muodossa. Matcher muuntaa sen sopivaan muotoon tekemällä siitä uuden Postfix-olion, jonka metodia toString-kutsuu ja sen jälkeen antaa sen parametrina uudelle BuilderNfa:lle. Tämän jälkeen Matcher-oliolle voi antaa sanoja wordMatches-metodille, joka kertoo kuuluuko kyseinen sana Matcherin kieleen vai ei.

## Saavutetut aika- ja tilavaativuudet

## Suorituskykyvertailu grepin kanssa

Testausta varten on tehty testi1.txt, testi2.txt ja testi3.txt -tiedostot, joissa on 0-19 merkin pituisia sanoja merkeistä a, b ja c tehtynä, jokainen omalla rivillään. Ensimmäisessä on 1000 sanaa, toisessa 1 000 000 sanaa ja kolmannessa 1 000 000 000 sanaa. Ainoastaan testi1.txt on versionhallinnassa mukana. Myöhemmin tehtiin vielä testi4.txt, jossa on 100 miljoonaa sanaa sekä testi5.txt, jossa on 10 miljoonaa sanaa, koska miljardin sanan lukemiseen meni grepillä todella kauan aikaa.

Hyvin nopeasti huomattiin ettei ohjelman vertailu grepin kanssa onnistu tällä tavoin, koska ohjelma oli niin paljon hitaampi. Tämä saattoi johtua ratkaisusta käyttää tiedoston lukemiseen Javan Scanner-luokkaa tai sitten ohjelma on vain todella hidas.

Grepillä kieliä tunnistaessa huomattiin, että grep käsitteli huomattavasti nopeammin tiedoston, jos sille oli annettu "a+" kuin "a*" -kieli, vaikka näiden ainoa ero on siinä, että "a*" hyväksyy myös tyhjän merkkijonon. Tämä toistui kaikissa kielissä, joiden ainoa ero oli samantapainen. Päätettiin verrata ohjelmaa grepin toimintaan katsomalla toistuuko sama ilmiö ohjelman suorituksessa.

## Työn mahdolliset puutteet ja parannusehdotukset

Ohjelman luokkarakenne on vielä hakusessa. En ole varma ovatko erilliset Postfix- ja Matcher-luokat kovin hyvä idea, mutta niiden yhdistäminen loisi monimutkaisen megaluokan, jota ei olisi helppo lukea.

Ohjelma tunnistaa vain perustoiminnot eikä monia yleisiä säännöllisiin lausekkeisiin kuuluvia erikoismerkkejä tai ilmaisuja kuten [a-z], {n}, ?, $, ^.

Ohjelma luottaa siihen, että käyttäjä ei tee virheitä tai yritä käyttää ohjelmaa väärin. Ohjelma ei millään tavalla tarkasta onko annettu kieli oikeassa muodossa ja jos kielessä on ns. turhat sulut, niin ohjelma ei osaa käsitellä niitä oikein.
