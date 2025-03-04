#!/bin/bash

# Creează directoarele necesare
mkdir -p data
mkdir -p logs

# Copiază fișierele în locațiile corecte
cp biblioteca.jar ./
cp start.sh ./
chmod +x start.sh

# Creează serviciul systemd pentru pornire automată
sudo tee /etc/systemd/system/biblioteca.service << EOF
[Unit]
Description=Biblioteca Online Service
After=network.target

[Service]
Type=simple
User=root
WorkingDirectory=/path/to/app
ExecStart=/path/to/app/start.sh
Restart=always

[Install]
WantedBy=multi-user.target
EOF

# Activează serviciul
sudo systemctl daemon-reload
sudo systemctl enable biblioteca
sudo systemctl start biblioteca

echo "Setup complete! Service is running." 