Spring Boot CI/CD: Docker, GitHub Actions & AWS EC2
This project demonstrates a fully automated pipeline for deploying a Spring Boot application. Every push to the main branch triggers a workflow that builds, containerizes, and deploys the latest version to an AWS cloud environment.

🛠 Tech Stack
Language: Java 21

Framework: Spring Boot 3.x (Maven)

Containerization: Docker & DockerHub

CI/CD: GitHub Actions

Cloud: AWS EC2 (Ubuntu)

🔄 CI/CD Pipeline Flow
Push: Developer pushes code to the main branch.

Build: GitHub Actions pulls the code and runs a Maven build to generate a .jar.

Containerize: A Docker image is built using the provided Dockerfile.

Registry: The image is pushed to DockerHub.

Deploy: GitHub Actions connects to the AWS EC2 instance via SSH.

Refresh: The server pulls the new image, stops the old container, and starts the fresh version.

📂 Project Structure
Plaintext
demo-simple-server
├── .github/workflows/
│   └── deploy.yml          # GitHub Actions Pipeline
├── src/                    # Application Source Code
├── Dockerfile              # Container Definition
├── pom.xml                 # Maven Configuration
└── README.md
⚙️ Workflow Breakdown (deploy.yml)
The pipeline executes the following automated steps:

Step	Action	Description
1	actions/checkout@v4	Downloads the repository code to the runner.
2	actions/setup-java@v4	Installs Java 21 on the runner.
3	mvn clean package	Builds the project and creates the executable JAR.
4	docker/login-action	Authenticates with DockerHub using stored secrets.
5	docker build & push	Packages the app as an image and uploads it to the registry.
6	SSH Deployment	Connects to EC2 to pull the image and restart the container.
🔐 Configuration & Security
1. GitHub Secrets
Add these variables in your repository under Settings > Secrets and variables > Actions:

DOCKER_USERNAME: Your DockerHub username.

DOCKER_PASSWORD: Your DockerHub access token.

EC2_HOST: Public IP address or hostname of your EC2.

EC2_SSH_KEY: The content of your private .pem key.

2. AWS Security Group
To ensure connectivity, open the following inbound ports:

Port 22 (SSH): Allows GitHub Actions to trigger deployment.

Port 8080 (TCP): Allows users to access the Spring Boot API.

3. EC2 Server Setup
Run these commands once on your Ubuntu instance to prepare the Docker environment:

Bash
sudo apt update && sudo apt install docker.io -y
sudo usermod -aG docker ubuntu
# Note: Log out and log back in for group changes to take effect
🚀 How to Deploy
Clone the repository.

Configure the GitHub Secrets listed above.

Push to the main branch:

Bash
git add .
git commit -m "feat: trigger deployment"
git push origin main
Monitor the progress in the "Actions" tab of your GitHub repository.

🌐 Accessing the Application
Once the pipeline finishes successfully, your API will be available at:
http://<EC2_PUBLIC_IP>:8080/api/home

Example: http://98.92.43.60:8080/api/home

Would you like me to help you write the specific deploy.yml file content or the Dockerfile for this project?
