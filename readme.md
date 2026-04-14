#LifeManager

Lifemanager to aplikacja webowa, służąca do codzienniego zarządzania finansami oraz życiem 
użytkownika. Umożliwia zapisywanie dziennych przychodód, wydatków, nawyków, nastroju
oraz komentarzy. Pozwala także na wpisywanie oszczędności i monitorowania ich wartości. 

Celem projektu jest zarządzanie danymi finansowymi i emocjonalnymi w jednym miejscu. 
Podsumowania dzienne pozwalają na szersze spojrzenie na dzień użytkownika, pokazując 
sumy wydatków i przychodów według kategorii. Podsumowania miesięczne natomiast pozwalają
śledzić nasze finanse, nie tylko jako wydatki i przychody, ale także zmiane wartości 
oszczędnosci. 
Poza wpisywaniem, aplikacja pozwala uītkownikowi na walidację poprawnosci wpisywanych 
danych oraz obsługę błędów w czytelny sposób. 


#Funkcjonalnośći

- Dodawanie dziennego przychodu
- Dodawanie dziennego wydatku
- Zapisywanie dziennego nastroju
- Walidacja danych:
    - brak przyszłej daty,
    - brak wartości ujemnych,
    - sprawdzanie istnienia powiązanych encji
- Obsługa błędów użytkownika z czytelnymi komunikatami
- Praca na repozytoriach Spring Data JPA
- Warstwa serwisowa oddzielona od kontrolerów



Aplikacja będzie dostępna pod adresem:
http://localhost:8080




