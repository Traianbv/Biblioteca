# Biblioteca Online

## Instalare și Pornire

### Windows
1. Asigură-te că ai Java 17 sau mai nou instalat
2. Dezarhivează fișierul biblioteca-online.zip
3. Deschide directorul biblioteca-online
4. Dublu-click pe start.bat

### Linux/Mac
1. Asigură-te că ai Java 17 sau mai nou instalat
2. Dezarhivează fișierul biblioteca-online.zip
3. Deschide un terminal în directorul biblioteca-online
4. Dă permisiuni de execuție: `chmod +x start.sh`
5. Rulează: `./start.sh`

## Credențiale implicite
- Username: admin
- Password: admin

## Configurare
Fișierul de configurare se află în config/application.properties.
Poți modifica:
- Portul serverului (implicit 8080)
- Credențialele bazei de date
- Alte setări

## Backup
Pentru backup, copiază întregul director data/.

## Suport
Pentru probleme sau întrebări, contactează administratorul.

## Cerințe de sistem

- Java 17 sau mai nou
- Maven
- Memorie RAM: minim 2GB
- Spațiu pe disc: minim 500MB

## Configurare și Instalare

### 1. Bază de date

Aplicația folosește H2 Database (file-based). Configurația se găsește în `application.properties`: 