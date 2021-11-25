<img src="https://www.home-assistant.io/images/blog/2019-08-home-assistant-cast/hero.png">

# Portfolio Semester 3
Home assistant platform is made with React, Java Spring with microservice architecture and Python (with RabbitMQ communication) for the Jetson Nano scripts.

## The substantiation for using these technologies

I chose Java over C# simply because I've been coding in C# for a long time, I am eager to learn new languages such as Java so I can diversify my scope and I am certain
that java would come across my path somehow in the forseeable future. Java is a popular language and provides a clean and efficient object-oriented-based development platform.
Espacially for this assignment, it's useful because Java is great for running applications that may be distributed among servers and clients in a network. 

Spring is the most popular framework for Javs and is most indentified with dependency injection flavor of inversion of control, which I learned last year and am willing to put to use again in this project.

I have never finished a solid project with a framework/library in JavaScript before, so I took a look among the most popular frameworks (Vue, Angular, React, Next) and decided which one is the best documentated and simplest framework to learn.
My main reason for choosing React is because it uses a Vitrual DOM that makes the app run fast in terms of performances and promises a fast learning curve.

Python is easy to learn for making scripts for the Jetson Nano. On the Jetson Nano I have a SensorHub, which has all sorts of sensors which I used for the measurements of the temperature and brightness for now.
In Python I can easily configure and test which sensors to use at a certain interval. Flask is a micro web framework written in Python which I can use for testing if the brightness/temperature are in the correct format/units. 

PostgreSQL is an advanced object-relational database that was used for storing the Measurements from the Jetson Nano's per room. 

## Project description
The goal of this project is to have a wall mounted tablet running a dashboard to get an overview with measurements for my room.
The goal is to have one screen with just a small menu for my rooms, so I can have a global view of the more important thing in the home: temperature, lights and a camera.

## C4 Models 

### C2 - Container Diagram
In this diagram you can see that the user has acces to the dashboardSPA. He has the possibility to Browse all sensordata from the browser when authorized by the  login provider Auth0.

![Container diagram](https://user-images.githubusercontent.com/48807736/143461583-e95969fe-f4a4-47aa-9005-43e8e28d0124.png)

### C3 - Component model
In this model you can see which services the client uses, if a user makes a request, it goes trough the controller that allows the user to use the sensor data, the data access component will provide the functionality related to signin in and sensor data storage.

![Component Diagram](https://user-images.githubusercontent.com/48807736/143461581-78c8bfb0-980f-42da-92db-fffa8abbcaa7.png)


# Learning Outcomes

## You design and build user-friendly, full-stack web applications.


## You use software tooling and methodology that continuously monitors and improve the software quality during software development.

 
## You design and implement a (semi)automated software release process that matches the needs of the project context.


## You act in a professional manner during software development and learning.
