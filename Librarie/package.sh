#!/bin/bash

# Creează directorul pentru distribuție
mkdir -p biblioteca-online
mkdir -p biblioteca-online/data
mkdir -p biblioteca-online/config

# Copiază fișierele necesare
cp target/online-library-0.0.1-SNAPSHOT.jar biblioteca-online/biblioteca.jar
cp src/main/resources/application.properties biblioteca-online/config/
cp README.md biblioteca-online/
cp start.bat biblioteca-online/
cp start.sh biblioteca-online/

# Creează arhiva ZIP
zip -r biblioteca-online.zip biblioteca-online/

# Curăță
rm -rf biblioteca-online/

echo "Arhiva biblioteca-online.zip a fost creată cu succes!" 