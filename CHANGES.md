# Liste der Änderungen             
            
## 0.0.3

* Das Projekt wird ab sofort über die commit ID funthomas424242 realisiert.
* Die groupId wird festgelegt auf: com.github.funthomas424242
* Projekt wird auf github neu aufgesetzt
* Als CI System wird travis-ci.org genutzt
* Zum Deployment wird bintray genutzt - in der Hoffnung in Richtung
  maven central deployen zu dürfen. 
* version fixed to maven convention


## 0.2-SNAPSHOT 21.01.2012

* settings.xml aufgeräumt - profile verringert
* secret properties support für hudson per env variablen hinzugefügt
* project von sourceforge nach github umgezogen
  Grund: Das komplizierte Deployment auf sourceforge war per maven nicht oder
  nur äußerst schwierig zu automatisieren. Größte Schwierigkeit war die 
  Anforderung nacheinander 2 Shells mittels ssh öffnen zu müssen.
* Als CI System wird jetzt Hudson auf Cloudbees benutzt.
* Portierung auf maven3 api begonnen
* Deutschen Sprachsupport für die maven site hinzugefügt
* README in Markdown überführt

## 0.1 Release 20.01.2012

* Release Branch erstellt


## 0.1-SNAPSHOT 16.01.2010

* Erste Implementierung unter der maven2 plugin api
* Die Implementierung wurde auf sourceforge.org unter commit id tmichel begonnen.










