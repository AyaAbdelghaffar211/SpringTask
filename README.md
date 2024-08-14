## Docker Commands

To run the Docker image for this application's database, run this command in the terminal:

```bash
$ docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=tasksdb -p 3306:3306 -d mysql:latest
