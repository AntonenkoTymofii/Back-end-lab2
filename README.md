# Back-end-lab2

## Web-site:

My variant is 3: 

Here is deploy: https://back-end-lab2-6g0o.onrender.com/person/1

Postman Flow is here: https://web.postman.co/workspace/My-Workspace~108c5fc2-0032-4f7c-80e7-21acb813afc2/flow/671eb86c54c71034dc282b93

p.s. May slow down when inactive, which can delay requests by 50 seconds or more.

## Instructions
In order to run the project on a local computer,
you need to unload the repository with the command:
```
    git clone https://github.com/AntonenkoTymofii/Back-end-lab2
```

Next, you will need to run the docker-compose file.
You will need to have the docker desktop downloaded.
You can download the docker on the official website: https://www.docker.com
To run the application, go to the command line and navigate to
the root folder of the uploaded repository.
First, you need to build the project:
```
docker-compose build
```
To see the full process of launching the application, use the command:
```
docker-compose up
``` 
If not, then:
```
docker-compose up -d
```
To stop the application, write the command:
```
docker-compose down
```

To see how the application works on your computer,
follow the link: http://localhost:8080/person/1


## About project

The project involved the creation of a small endpoint that
would display the status of the REST request and the current time.
The main goal of this project was to showcase the skills of working
with docker and to deploy this small application on a free server.

