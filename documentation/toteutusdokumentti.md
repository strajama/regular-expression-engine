# Toteutusdokumentti

## Ohjelman yleisrakenne

Ohjelma rakentaa annetun kielen pohjalta epädeterministinen äärellisen automaatin (nondeterministic finite automata eli NFA), joka tunnistaa kuuluuko sille syötettävä sana kyseiseen kieleen vai ei.

### NFA:n rakenne

NFA koostuu tiloista (State) ja siirtymistä (Transition). State-oliolla on tieto siitä onko se hyväksyvä eli lopettava tila, paikka yhdelle symbolisiirtymälle (SymbolTransition) ja kaksipaikkainen taulukko mahdollisille epsilonsiirtymille (EpsilonTransition). Transition-rajapinnan toteuttavilla siirtymiä on kaksi erilaista: SymbolTransition ja EpsilonTransition. Kummallakin on tieto tilasta, josta siirtymä alkaa ja minne se päättyy. Lisäksi SymbolTransitionilla on symboli, jonka pitää mätsätä, jotta siirtymän voi ns. kulkea. Yhdellä State-oliolla voi olla joko yksi SymbolTransition tai 1-2 EpsilonTransition-oliota.

Oman kielensä sanan tunnistava NFA kootaan yhdistämällä pienempiä NFA-olioita yhteen. NFA:lla on aloitustila, josta se aloittaa suorituksen ja johon ei tule siirtymiä mistään muusta automaatin tilasta sekä hyväksyvä eli lopetustila, josta ei ole siirtymiä mihinkään muuhun automaatin tilaan.

Nfa-rajapinnan toteuttaa abstrakti yliluokka NfaClass ja muut Nfa-luokat perivät sen.

Yksinkertaisimmat Nfa:t ovat EpsilonNfa ja SymbolNfa. Molemmissa on yksi alku- ja lopputila (State) ja EpsilonNfa:ssa näiden välillä epsilonsiirtymä (EpsilonTransition) ja SymbolNfa:lla symbolisiirtymä (SymbolTransition). Erilaisia sääntöjä soveltaen näistä perus-Nfa:ista saadaan tehtyä monimutkaisia Nfa:ita.

ConcatNfa-luokalla voi tehdä kahdelle Nfa:lle konkatenaation niin, että tuloksena on Nfa, joka tunnistaa sellaisen kielen sanan, jonka alkuosa toteuttaa ensimmäisenä Nfa:n kielen ja sen perään toisen Nfa:n kielen. Esim. jos tehdään ConcatNfa kahdesta Nfa:sta, joista toinen tunnistaa kielen "a" ja toinen kielen "b", niin ConcatNfa tunnistaa kielen "a·b", johon käytännössä kuuluu vain sana "ab".

UnionNfa-luokalla voi tehdä kahdelle Nfa:lle yhdisteen niin, että tuloksena on Nfa, joka tunnnistaa jomman kumman sille annetun Nfa:n kielen sanan. Esim. jos tehdään UnionNfa taas kielen "a" ja kielen "b" tunnistavista Nfa:ista, niin UnionNfa tunnistaa kielen "a|b", johon kuuluu sanat "a" ja "b".

ClosureNfa-luokalla voi toteuttaa Nfa:lle ns. tähtioperaation eli ClosureNfa 
