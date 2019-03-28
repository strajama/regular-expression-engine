# Toteutusdokumentti

## Ohjelman yleisrakenne

Ohjelma rakentaa annetun kielen pohjalta epädeterministinen äärellisen automaatin (nondeterministic finite automata eli NFA), joka tunnistaa kuuluuko sille syötettävä sana kyseiseen kieleen vai ei.

### NFA:n rakenne

NFA koostuu tiloista (State) ja siirtymistä (Transition). State-oliolla on tieto siitä onko se hyväksyvä eli lopettava tila, paikka yhdelle symbolisiirtymälle (SymbolTransition) ja kaksipaikkainen taulukko mahdollisille epsilonsiirtymille (EpsilonTransition). Transition-rajapinnan toteuttavilla siirtymiä on kaksi erilaista: SymbolTransition ja EpsilonTransition. Kummallakin on tieto tilasta, josta siirtymä alkaa ja minne se päättyy. Lisäksi SymbolTransitionilla on symboli, jonka pitää mätsätä, jotta siirtymän voi ns. kulkea. Yhdellä State-oliolla voi olla joko yksi SymbolTransition tai 1-2 EpsilonTransition-oliota.
