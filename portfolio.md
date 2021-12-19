# Portfolio Semester 3
Home assistant platform is made with React, Java Spring with microservice architecture and Python (with RabbitMQ communication) for the Jetson Nano scripts.


<img src="https://www.home-assistant.io/images/blog/2019-08-home-assistant-cast/hero.png">

[_image-source_](https://www.home-assistant.io/blog/2019/08/06/home-assistant-cast/)

## Table of contents
 - Portfolio Semester 3
   - [The substantiation for using these technologies](#The-substantiation-for-using-these-technologies)
   - [Project description](#Project-description)
   - [C4 Models](#C4-Models)
     - [C2 Container Diagram](#C2-Container-Diagram)
     - [C3 Component Model](#C3-Component-Model)
 - Learning Outcomes
   - [Outcome 1: You design and build user friendly full stack web applications](#Outcome-1-You-design-and-build-user-friendly-full-stack-web-applications)
   - [Outcome 2: Tooling and methodology](#Outcome-2-Tooling-and-methodology)
   - [Outcome 3: Design and implement (release process)](#Outcome-3-Design-and-implement-release-process)
   - [Outcome 4: Professional manner](#Outcome-4-Professional-manner)


## The substantiation for using these technologies

I chose Java over C# simply because I've been coding in C# for a while. I am eager to learn new languages such as Java so I can diversify my scope and I am certain that java would come across my path somehow in the forseeable future. Java is a popular language and provides a clean and efficient object-oriented-based development platform.
Especially for this assignment, it's useful because Java is great for running applications that may be distributed among servers and clients in a network. 

Spring is the most popular framework for Java and is most indentified with dependency injection flavor of inversion of control, which I learned last year and am willing to put to use again in this project.

I have never finished a solid project with a framework/library in JavaScript before, so I took a look among the most popular frameworks (Vue, Angular, React, Next) and decided which one is the best documentated and simplest framework to learn.
My main reason for choosing React is because it uses a Vitrual DOM that makes the app run fast in terms of performances and promises a fast learning curve.

Python is easy to learn for making scripts for the Jetson Nano. On the Jetson Nano I have a SensorHub, which has all sorts of sensors which I used for the measurements of the temperature and brightness for now.
In Python I can easily configure and test which sensors to use at a certain interval. Flask is a micro web framework written in Python which I can use for testing if the brightness/temperature are in the correct format/units. 

PostgreSQL is an advanced object-relational database that was used for storing the Measurements from the Jetson Nano's per room. 

## Project description
The goal of this project is to have a wall mounted tablet running a dashboard to get an overview with measurements for my room.
The goal is to have one screen with just a small menu for my rooms, so I can have a global view of the more important thing in the home: temperature, lights and a camera.

## C4 Models 

### C2 Container Diagram
In this diagram you can see that the homeowner has access to the HAP-dashboard. The homeowner has the possibility to Browse all IOT measurements from the browser when authorized by the login provider Auth0.

<img src="https://i.postimg.cc/85XyDTzZ/image.png">

### C3 Component Model
In this model you can see which services the client uses, if a user makes a request, it goes trough the controller that allows the user to use the sensor data, the data access component will provide the functionality related to signin in and sensor data storage.

<img src="https://i.postimg.cc/7LFRTHkb/image.png">


# Learning Outcomes
The following are the learning outcomes I need to accomplish in semester 3.

## Outcome 1 You design and build user friendly full stack web applications
You design and build user friendly full stack web applications

#### Design
First of, I made a UI-design in Figma so I have a good reference where to place the components. 
<table>
 <tr>
  <td><img src="https://i.postimg.cc/yNdhyn4X/hap-design.png"></td>
 </tr>
</table>

#### Identity service: OAuth2
With this service the user is able to login with google, I implemented this feature so if I ever move houses I could still login with the same account and view my account settings. It is also possible for the user to see their OAuth provided information. 
<table>
 <tr>
  <td><img src="https://i.postimg.cc/wT1SwKL3/Auth-login.png" width=500 height=250></td>
  <td><img src="https://i.postimg.cc/nhnsf128/Auth-profile-information.png" width=500 height=250></td>
 </tr>
</table>

#### Dashboard
In this dashboard the user is able to see all the measurements for the given room where a jetson nano is mounted. In my case there is a Jetson in my brother's (mario) and in my own room. When a room gets clicked from the side bar, the measurements will be retrieved and shown in a measurement chart and in cards with averages.

<img src="https://i.postimg.cc/9XnsV3XN/image.png">

## Outcome 2 Tooling and methodology
You use software tooling and methodology that continuously monitors and improve the software quality during software development
 
#### Testing

Testing is done to assure the user services run without encountering any problems. For testing methodologies I used Unit, Integration and E2E testing:

*FAQ: What’s the difference between unit and integration testing?*<br>
Unit testing is focused on testing a single function where as integration testing is focused on combining functions correctly. 

##### Unit testing 
I created multiple unit tests to tell the behavior of the functions either with the right input or a test scenario. Before testing the unit, I mocked objects using Mockito to simulate a real scenario. One of the big advantages with mock data is that it makes it possible to simulate errors and circumstances that would otherwise be very difficult to create in a real world environment. 

<table>
 <tr>
  <td><img src="https://i.postimg.cc/bJhH7nZs/Unittest-successcenario.png" width=500 height=250></td>
  <td><img src="https://i.postimg.cc/GmmPrr3x/Unittest-example.png" width=500 height=250></td>
 </tr>
</table>

##### Integration testing
I wrote my integration tests with [MockMVC](https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/), this allows you to create fake requests to a controller. In this case I am looking whether the response code of the tests are acting upon it's expected behaviour. 

<img src="https://i.postimg.cc/wMPScFB1/Integrationtest-goodscenario.png">

<img src="https://i.postimg.cc/C193bm5z/Integrationtest-testscenario.png">

#### Code coverage

Whith [Sonarcloud](https://sonarcloud.io/projects) I can continously monitor code quality during my development. Sonarcloud analyzes my code everytime I push to my develop branch. (You can change these settings in your [workflow](https://docs.github.com/en/actions/quickstart))

![image](https://user-images.githubusercontent.com/48807736/146692355-b7cac62b-0464-429f-9714-c0a59b231c29.png)

When you get your overview of bugs, the bugs will be graded by reliability. In my case I left the Service testing class on public.<br>

<img src="https://i.postimg.cc/rp7T98YV/Sonarcloud-example.png">

When clicking on 'why is this an issue?' it gives you a reasoning for the bug.

<img src="https://i.postimg.cc/2Swg0n28/Sonarcloud-reasoning.png">

## Outcome 3 Design and implement (release process)
You design and implement a semi automated software release process that matches the needs of the project context

CI/CD is used to automate large parts of the deployment process which speeds up development. We can also let CI/CD handle our testing by adding the service of Sonarcloud.

### CI


...

By making tests you can check if everything works as it's supposed to, therefore I setup a workflow which runs all my tests everytime I push to the develop branch. 

```yml
name: "sensor api"
on: 
  workflow_dispatch:
  push:
    branches:
      - develop
    paths:
      - Sensor_API/**
```

### CD



#### Secure deployment

First of all I made evironments to describe a general deployments target as seen below. I configured the environments with protection rules and secrets. My deployments workflow will only pass if it meets all my protection rules.

![image](https://user-images.githubusercontent.com/48807736/146693344-61bcb304-9ec7-4ad2-a3ff-f33a2277e4a0.png)


 - *Build(Develop)*: The build environment scans the services with sonarcloud, builds and tests my maven project, setting things up for approval.

 - *Production*: After being approved by me, the dockercompose file executes a command to deploy the microservices with Docker on my server: siemvm2.

 - Nginx...


## Outcome 4 Professional manner
You act in a professional manner during software development and learning

### Pomodoro

Pomodoro is a time management technique that I got recommended by my teacher. This method breaks up your workday into 25 minute chunks separated by five minute breaks. These intervals are referred to as pomodoros. I gave Pomodoro a try and came to the conclusion that this method really stimulates me to do the most in the given 25 minutes, rather than losing concentration and overthinking a problem without giving it a fresh thought.

The following site was used for Pomodoro:
- https://pomofocus.io/

### Project board

In my project board I keep track of my features. With labels I indicate if a feature is linked to a certain sprint, enhancements of an existing story or if help is wanted with a bug. 
<img src="https://i.postimg.cc/rpnWjWks/image.png">

In the description of a feature I state the use case, acceptance-criteria and tasks to keep track of the criteria to show what has to be done to complete the story.
<table>
 <tr>
  <td>
    <img src="https://i.postimg.cc/sDYTcn02/image.png">
   </td>
   <td>
    <img src="https://i.postimg.cc/yxBVSQbZ/image.png"> 
  </td>
  <td>
   <img src="https://i.postimg.cc/mrkxKx0j/image.png">
  </td>
 </tr>
</table>
