Spring Boot CI/CD: Docker, GitHub Actions & AWS EC2

This project demonstrates a fully automated pipeline for deploying a Spring Boot application. Every push to the `main` branch triggers a workflow that builds, containerizes, and deploys the latest version to an AWS cloud environment.

---

## 🛠 Tech Stack

- **Language:** Java 21  
- **Framework:** Spring Boot 3.x (Maven)  
- **Containerization:** Docker & DockerHub  
- **CI/CD:** GitHub Actions  
- **Cloud:** AWS EC2 (Ubuntu)

---

##  CI/CD Pipeline Flow

1. **Push** – Developer pushes code to the `main` branch.
2. **Build** – GitHub Actions pulls the code and runs a Maven build to generate a `.jar`.
3. **Containerize** – A Docker image is built using the provided `Dockerfile`.
4. **Registry** – The image is pushed to DockerHub.
5. **Deploy** – GitHub Actions connects to the AWS EC2 instance via SSH.
6. **Refresh** – The server pulls the new image, stops the old container, and starts the fresh version.

---

##  Project Structure


demo-simple-server
├── .github/workflows/
│ └── deploy.yml
├── src/
├── Dockerfile
├── pom.xml
└── README.md


---

## ⚙️ Workflow Breakdown (`deploy.yml`)

The pipeline executes the following automated steps:

| Step | Action | Description |
|-----|------|-------------|
| 1 | `actions/checkout@v4` | Downloads the repository code to the runner |
| 2 | `actions/setup-java@v4` | Installs Java 21 on the runner |
| 3 | `mvn clean package` | Builds the project and creates the executable JAR |
| 4 | `docker/login-action` | Authenticates with DockerHub using stored secrets |
| 5 | `docker build` & `docker push` | Packages the app as a Docker image and uploads it to DockerHub |
| 6 | `appleboy/ssh-action` | Connects to EC2 to pull the image and restart the container |

---

##  Configuration & Security

### GitHub Secrets

Add these variables in your repository under:

`Settings → Secrets and variables → Actions`


DOCKER_USERNAME
DOCKER_PASSWORD
EC2_HOST
EC2_SSH_KEY


- **DOCKER_USERNAME** – DockerHub username  
- **DOCKER_PASSWORD** – DockerHub access token  
- **EC2_HOST** – Public IP address or hostname of your EC2 instance  
- **EC2_SSH_KEY** – The content of your private `.pem` key  

---

## EC2 Server Setup

Run these commands once on your Ubuntu EC2 instance:

```bash
sudo apt update
sudo apt install docker.io -y
sudo usermod -aG docker ubuntu
```

Log out and log back in for group changes to take effect.

 AWS Security Group

Ensure the following inbound ports are open:

Port	Purpose
22	SSH deployment access
8080	Spring Boot API access
 How to Deploy

Push code to the main branch:
```bash
git add .
git commit -m "feat: trigger deployment"
git push origin main
```

Monitor progress in the Actions tab of your GitHub repository.

🌐 Accessing the Application
```bash
http://<EC2_PUBLIC_IP>:8080/api/home
```
