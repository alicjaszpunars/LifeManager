# LifeManager

LifeManager to aplikacja webowa, służąca do codzienniego zarządzania finansami oraz życiem 
użytkownika. Umożliwia zapisywanie dziennych przychodów, wydatków, nawyków, nastroju
oraz komentarzy. Pozwala także na wpisywanie oszczędności i monitorowanie ich wartości. 

Celem projektu jest zarządzanie danymi finansowymi i emocjonalnymi w jednym miejscu. 
Podsumowania dzienne pozwalają na szersze spojrzenie na dzień użytkownika, pokazując 
sumy wydatków i przychodów według kategorii. Podsumowania okresowe natomiast pozwalają
śledzić finanse, nie tylko jako wydatki i przychody, ale także zmianę wartości 
oszczędnosci. 
Poza wpisywaniem, aplikacja pozwala użtkownikowi na walidację poprawności wpisywanych 
danych oraz obsługę błędów w czytelny sposób. 


## Funkcjonalności

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


## Wymagania

Do uruchomienia projektu wymagane są:

- Java 17
- Maven
- Relacyjna baza danych (np. MySQL lub H2)
- IntelliJ IDEA (lub inne IDE obsługujące projekty Spring Boot)


## Konfiguracja

Konfiguracja aplikacji znajduje się w pliku `application.properties`.

Należy skonfigurować połączenie z bazą danych:
spring.datasource.url=jdbc:mysql://localhost:3306/LifeManager
spring.datasource.username=root
spring.datasource.password=password


## Zewnętrzne API

Aplikacja korzysta z zewnętrznego API do pobierania aktualnych wartości rynkowych 
oszczędności.

Klucz API jest obecnie zapisany na stałe w kodzie aplikacji i nie wymaga dodatkowej 
konfiguracji środowiska.


## Przykładowy endpoint
### Pobranie podsumowania dnia

Pobiera pełne podsumowanie dnia użytkownika, obejmujące nastrój, wpis dziennika, przychody, wydatki oraz nawyki.

GET /day/{date}

Przykład:
GET /day/2026-04-15

Przykładowa odpowiedź:
```json
{
  "date": "2026-04-15",
  "mood": "very_good",
  "diary": "string",
  "expenses": [
    {
      "category": "Food",
      "total": 120.50
    }
  ],
  "incomes": [
    {
      "category": "Salary",
      "total": 3000.00
    }
  ],
  "habits": [
    {
      "habitName": "Running",
      "done": true
    }
  ]
}

```



Aplikacja będzie dostępna pod adresem:
http://localhost:8080




