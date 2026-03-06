# Spring Boot CI/CD Deployment with Docker, GitHub Actions, and AWS EC2

This project demonstrates a complete CI/CD pipeline for deploying a Spring Boot application using Docker, GitHub Actions, DockerHub, and AWS EC2.

Every push to the `main` branch automatically builds the application, creates a Docker image, pushes it to DockerHub, and deploys it to an EC2 server.

---

## Tech Stack

- Java 21
- Spring Boot
- Maven
- Docker
- GitHub Actions
- DockerHub
- AWS EC2

---

## CI/CD Pipeline Flow

Developer pushes code → GitHub repository → GitHub Actions builds the project → Docker image is created → image is pushed to DockerHub → GitHub Actions connects to EC2 via SSH → server pulls latest image → container is restarted with the new version.

---

## Project Structure


demo-simple-server
│
├── src/
│
├── Dockerfile
├── pom.xml
│
└── .github/
└── workflows/
└── deploy.yml


---

## Docker Setup

Build the Docker image:


docker build -t demo-simple-server .


Run the container:


docker run -d -p 8080:8080 demo-simple-server


---

## GitHub Actions Workflow

The CI/CD pipeline is defined in:


.github/workflows/deploy.yml


Pipeline steps:

1. Checkout repository
2. Setup Java
3. Build the Maven project
4. Login to DockerHub
5. Build Docker image
6. Push image to DockerHub
7. SSH into EC2 server
8. Pull latest Docker image
9. Stop existing container
10. Start new container

---

## GitHub Secrets

The following secrets must be configured in the repository:


DOCKER_USERNAME
DOCKER_PASSWORD
EC2_HOST
EC2_SSH_KEY


- `DOCKER_USERNAME` – DockerHub username  
- `DOCKER_PASSWORD` – DockerHub access token  
- `EC2_HOST` – Public EC2 IP or hostname  
- `EC2_SSH_KEY` – Private SSH key used to access EC2  

---

## EC2 Server Setup

Install Docker on the EC2 instance:


sudo apt update
sudo apt install docker.io -y


Add the ubuntu user to the docker group:


sudo usermod -aG docker ubuntu


Reconnect to apply changes.

---

## AWS Security Group

Open the following ports in the EC2 security group:


22 - SSH access
8080 - Application access


---

## Deployment

Push changes to the main branch:


git add .
git commit -m "deploy"
git push


GitHub Actions will automatically build and deploy the new version to the EC2 server.

---

## Access the Application


http://<EC2_PUBLIC_IP>:8080/api/home

---
- Enable HTTPS with Let's Encrypt
- Implement zero-downtime deployments
- Add container health checks
