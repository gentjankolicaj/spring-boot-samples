1.Install docker on host
2.Execute from cmd/terminal : docker pull mysql //pulls mysql server image
3.Execute from cmd/terminal : docker images //check if image is pulled
4.Execute from cmd/terminal : docker run --name mysql_container -p 3306:3306 -e
MYSQL_ROOT_PASSWORD=toor8 -d mysql:latest //Starts mysql container
5.Execute from cmd/terminal : docker ps //checks if container is started