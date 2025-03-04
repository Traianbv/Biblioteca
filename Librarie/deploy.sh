#!/bin/bash

# Build the application
echo "Building application..."
mvn clean package

# Build Docker image
echo "Building Docker image..."
docker build -t biblioteca-online .

# Stop existing container if running
echo "Stopping existing container..."
docker stop biblioteca-online || true
docker rm biblioteca-online || true

# Run new container
echo "Starting new container..."
docker run -d \
  --name biblioteca-online \
  --env-file .env \
  -p 8080:8080 \
  -v /path/to/data:/app/data \
  biblioteca-online

echo "Deployment complete!" 