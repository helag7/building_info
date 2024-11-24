# building_info

Dla administratorów budynków, którzy pragną optymalizować koszty zarządzania budynkami  nasza aplikacja Building Info umożliwi pozyskanie informacji o parametrach budynku na poziomie pomieszczeń, kondygnacji oraz całych budynków. Aplikacja będzie dostępna poprzez GUI a także jako zdalne API dzięki czemu można ją zintegrować z istniejącymi narzędziami.

Struktura danych (do umieszczenia w pliku README.md)
* Lokacja to budynek, poziom, lub pomieszczenie
* Budynek może składać się z poziomów a te z pomieszczeń
* Każda lokalizacja jest charakteryzowana przez:
  * id – unikalny identyfikator
  * name – opcjonalna nazwa lokalizacji
* Pomieszczenie dodatkowo jest charakteryzowane przez:
  * area = powierzchnia w m^2
  * cube = kubatura pomieszczenia w m^3
  * heating = poziom zużycia energii ogrzewania (float)
  * light – łączna moc oświetlenia

Zasady punktowania sprintów:\
https://docs.google.com/spreadsheets/d/e/2PACX-1vTn6j3M8pmGEzrsQk8mXse7lVHUdhYWkfxbkQiYI23rBtwM4N3bWw0qtupW-gesfCkcYasnZ-eEXl-F/pubhtml
