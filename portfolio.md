# Portfolio Semester 3
## IP Learning Outcomes
Home assistant platform is made with React, Java Spring with microservice architecture and Python (with RabbitMQ communication) for the Jetson Nano scripts.


<img src="https://www.home-assistant.io/images/blog/2019-08-home-assistant-cast/hero.png">

[_image-source_](https://www.home-assistant.io/blog/2019/08/06/home-assistant-cast/)

## Table of contents
 - IP Learning Outcomes
   - [The substantiation for using these technologies](#The-substantiation-for-using-these-technologies)
   - [Project description](#Project-description)
   - [C4 Models](#C4-Models)
     - [C2 Container Diagram](#C2-Container-Diagram)
     - [C3 Component Model](#C3-Component-Model)
   - [Outcome 1: You design and build user friendly full stack web applications](#Outcome-1-You-design-and-build-user-friendly-full-stack-web-applications)
   - [Outcome 2: Tooling and methodology](#Outcome-2-Tooling-and-methodology)
   - [Outcome 3: Design and implement (release process)](#Outcome-3-Design-and-implement-release-process)
   - [Outcome 4: Professional manner](#Outcome-4-Professional-manner)
 - GP Learning Outcomes
   - [Outcome 1: Agile method](#Outcome-1-Agile-method)
   - [Outcome 2: Business processes](#Outcome-2-Business-processes)
   - [Outcome 3: Requirements and design](#Outcome-3-Requirements-and-design)
   - [Outcome 4: Cultural differences and ethics](#Outcome-4-Cultural-differences-and-ethics)
   - [Outcome 5: Professional](#Outcome-5-Professional)
   - [Outcome 6: Web application](#Outcome-6-Web-application)


## The substantiation for using these technologies

I have chosen Java over C# simply because I've been coding in C# for a while. I am eager to learn new languages such as Java so I can diversify my scope and I am certain that java would come across my path somehow in the forseeable future. Java is a popular language and provides a clean and efficient object-oriented-based development platform.
Especially for this assignment, it's useful because Java is great for running applications that may be distributed among servers and clients in a network. 

Spring is the most popular framework for Java and is most identified with dependency injection flavour of inversion of control, which I learned last year and am willing to put to use again in this project.

I have never finished a solid project with a framework/library in JavaScript before, so I took a look among the most popular frameworks (Vue, Angular, React, Next) and decided which one is the best documented and simplest framework to learn.
My main reason for choosing React is because it uses a Virtual DOM that makes the app run fast in terms of performances and promises a fast learning curve.

Python is easy to learn for making scripts for the Jetson Nano. On the Jetson Nano I have a SensorHub, which has all sorts of sensors which I used for the measurements of the temperature and brightness for now.
In Python I can easily configure and test which sensors to use at a certain interval. Flask is a micro web framework written in Python which I can use for testing if the brightness/temperature are in the correct format/units. 

PostgreSQL is an advanced object-relational database that was used for storing the Measurements from the Jetson Nano's per room. 

## Project description
The goal of this project is to have dashboard the dashboard running to get an overview with IOT-measurements for my room.
The goal is to have one screen with a small menu for my rooms, so I can have a global view of the more important thing in the home: temperature, lights (and a other features coming in the future).

## C4 Models 

### C2 Container Diagram
In this diagram you can see that the homeowner has access to the HAP-dashboard. The homeowner has the possibility to Browse all IOT measurements from the browser when authorized by the login provider Auth0.

<img src="https://i.postimg.cc/85XyDTzZ/image.png">

### C3 Component Model
In this model you can see which services the client uses, if a user makes a request, it goes through the controller that allows the user to use the sensor data, the data access component will provide the functionality related to signing in and sensor data storage.

<img src="https://i.postimg.cc/7LFRTHkb/image.png">


# Learning Outcomes
The following are the learning outcomes I need to accomplish in semester 3.

## Outcome 1: You design and build user friendly full stack web applications
You design and build user friendly full stack web applications

#### Design
First of, I made a UI-design in Figma so I have a good reference where to place the components. <br><br>
 <img src="https://i.postimg.cc/yNdhyn4X/hap-design.png"></td>

#### Identity service: Auth0
With this service the user is able to login with google, I implemented this feature so if I ever move houses I could still login with the same account and view my account settings. It is also possible for the user to see their [Auth0](https://auth0.com/) provided information. 
<table>
 <tr>
  <td><img src="https://i.postimg.cc/wT1SwKL3/Auth-login.png" width=500 height=250></td>
  <td><img src="https://i.postimg.cc/nhnsf128/Auth-profile-information.png" width=500 height=250></td>
 </tr>
</table>

#### Dashboard
In this dashboard the user is able to see all the measurements for the given room where a jetson nano is mounted. In my case there is a Jetson in my brother's (Mario) and in my own room. When a room gets clicked from the side bar, the measurements will be retrieved and shown in a measurement chart and in cards with averages.

<img src="https://i.postimg.cc/9XnsV3XN/image.png">

## Outcome 2: Tooling and methodology
You use software tooling and methodology that continuously monitors and improve the software quality during software development
 
#### Testing

Testing is done to assure the user services run without encountering any problems. For testing methodologies I used Unit, Integration and E2E testing:

*FAQ: What’s the difference between unit and integration testing?*<br>
Unit testing is focused on testing a single function whereas integration testing is focused on combining functions correctly. 

##### Unit testing 
I created multiple unit tests to tell the behaviour of the functions either with the right input or a test scenario. Before testing the unit, I mocked objects using Mockito to simulate a real scenario. One of the big advantages with mock data is that it makes it possible to simulate errors and circumstances that would otherwise be very difficult to create in a real world environment. 

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

With [Sonarcloud](https://sonarcloud.io/projects) I can continuously monitor code quality during my development. Sonarcloud analyses my code every time I push to my develop branch. (You can change these settings in your [workflow](https://docs.github.com/en/actions/quickstart))

![image](https://user-images.githubusercontent.com/48807736/146692355-b7cac62b-0464-429f-9714-c0a59b231c29.png)

When you get your overview of bugs, the bugs will be graded by reliability. In my case I left the Service testing class on public.<br>

<img src="https://i.postimg.cc/rp7T98YV/Sonarcloud-example.png">

When clicking on 'why is this an issue?' it gives you a reasoning for the bug.

<img src="https://i.postimg.cc/2Swg0n28/Sonarcloud-reasoning.png">

## Outcome 3: Design and implement (release process)
You design and implement a semi automated software release process that matches the needs of the project context

CI/CD is used to automate large parts of the deployment process which speeds up development. We can also let CI/CD handle our testing by adding the service of Sonarcloud. 

I made GitHub secret environments to separate build(development) and deployment workflows as seen below. <br/> 
I configured these environments with protection rules and secrets. The workflows will only pass once all steps are successfully completed.

![image](https://user-images.githubusercontent.com/48807736/146693344-61bcb304-9ec7-4ad2-a3ff-f33a2277e4a0.png)

 - *Build(Develop)*: The build environment scans the services with sonarcloud, builds and tests the maven project and sets things up for approval.

 - *Production*: After manually approving the production deployment workflow, the docker-compose job executes the build and up docker-compose command to deploy the services using Docker on my home-server: siemvm2.

### Setting up the build environment

By making tests you can check if everything works as it's supposed to, therefore I setup a workflow which runs all my tests. In this case the workflow builds for the sensor api, every time I push to the develop branch. 

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

To run the tests in maven we first have to setup the java version (in my case JDK 11), afterwards we build and run the tests with the following commands:

```yml
  - name: Set up JDK 11
    uses: actions/setup-java@v2
    with:
       java-version: '11'
       distribution: 'adopt'
  - name: Build and test project with Maven
    run: mvn -B test --file pom.xml
```
![image](https://user-images.githubusercontent.com/48807736/146839056-35406cb4-7e6b-44d5-a043-6af4a70e53aa.png)


I added a sonar token to my environment secrets and a project key referencing to my Sonarcloud projects, to make use of Sonarcloud. First we cache the Sonarcloud packages so we don't continuously have to install these. At last, we reference the Github token, sonar token, organization key and project key.

```yml
- name: Cache SonarCloud packages
  uses: actions/cache@v1
  with:
    path: ~/.sonar/cache
    key: ${{ runner.os }}-sonar
    restore-keys: ${{ runner.os }}-sonar
      
- name: SonarCloud Scan
  uses: sonarsource/sonarcloud-github-action@master
  env:
    GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
    SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
  with: 
    args: >
      -Dsonar.organization=fhict-s-siem
      -Dsonar.projectKey=S3-SiemLucassen-HAP_Sensor_API          
      -Dsonar.java.binaries=Sensor_API/target/classes 
      -Dsonar.sources=Sensor_API/src/main/java/
```
![image](https://user-images.githubusercontent.com/48807736/146839408-494a8604-e85f-40ec-96db-55afd724c9e1.png)

![image](https://user-images.githubusercontent.com/48807736/146838966-819ce763-0009-4838-bde1-b7607cda664c.png)

### Deployment in the production environment

When merging a new feature to the master (production) branch, the deployment workflow is triggered. However, this deployment workflow has to be manually approved by me (as seen in the figure below), so that this cannot be abused by malicious pull requests.

 ![image](https://user-images.githubusercontent.com/48807736/146750948-b9414ff9-2d36-4f9c-9dab-308523bfce62.png)

To deploy the docker containers using docker-compose on the remote server, I make use of the [docker-compose remote deployment GitHub Action](https://github.com/alex-ac/github-action-ssh-docker-compose). To do this securely, I ensure that the used SSH-key is in my environment secrets and that I use a user on the Linux server for deployment with docker; Aptly named, docker-deploy. Under the hood of this GitHub Action, it creates a temporary workspace where the repository is cloned. This latest version of the repository is used to build the docker images with the `docker-compose build` command. Then, it executes the `docker-compose up` command deploying the latest Sensor-API and dashboardspa images as containers on the server.

```yml
- uses: alex-ac/github-action-ssh-docker-compose@master
  name: Docker-Compose Remote Deployment
  with:
    ssh_host: ${{ secrets.DOCKER_HOST }}
    ssh_private_key: ${{ secrets.DOCKER_SSH_PRIVATE_KEY }}
    ssh_port: ${{ secrets.DOCKER_SSH_PORT }}
    ssh_user: docker-deploy
    docker_compose_filename: docker-compose.yml
 ```

The diagram below visualises the deployment workflow.
![CI_CD-workflowDiagram](https://user-images.githubusercontent.com/48807736/146838084-fc3f43d9-9843-4837-be24-a9f99fddf58f.png)

![image](https://user-images.githubusercontent.com/48807736/147012393-84b1297a-2b4d-4c2f-a4e5-1b8283c23d1e.png)

## Outcome 4: Professional manner
You act in a professional manner during software development and learning

### Pomodoro

Pomodoro is a time management technique that I got recommended by my teacher. This method breaks up your workday into 25 minute chunks separated by five minute breaks. These intervals are referred to as Pomodoros. I gave Pomodoro a try and came to the conclusion that this method really stimulates me to do the most in the given 25 minutes, rather than losing concentration and overthinking a problem without giving it a fresh thought. <br>

I recommend the following site that I have used for my Pomodoro:
- https://pomofocus.io/

<table>
 <tr>
  <td>
   <img src="https://i.postimg.cc/g0c4gtxF/image.png" height=500>
  </td>
  <td>
   <img src="https://i.postimg.cc/tJ9g66r5/image.png" height=500>
  </td>
  <td>
   <img src="https://i.postimg.cc/jdLbFvJf/image.png" height=500>
  </td>
 </tr>
</table>

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

## GP Learning Outcomes

### Outcome 1: Agile method
You can implement the software process for your project according to a given agile software development method. <br>
I showed the teacher that I have proficient knowledge of the agile methodologies by assessing me about SCRUM and XP.

In this semester I Implemented the usage of SCRUM along with the usage of the project board on JIRA. My team had multiple cycles of development (sprints) with 1 cycle containing 3 weeks. We had daily meetings in the morning and also did peer-feedback occasionally, to clear up any problems if needed.

I also tried Extreme Programming (XP) by continuously pair programming with my fellow team-members. <br> Especially Koen and I did this whilst also using the Pomodoro technique which resulted in a steady work-pace. <br> 
XP and SCRUM are pretty similar with concepts like release planning, daily meetings and retrospect’s etc. With XP, I can ensure the stakeholders that certain problems are looked at with more care by pair programming. This helps speeding up the tasks with the highest priority.

### Outcome 2: Business processes
You can explain simple business processes and relate them to the development of your software project. <br>
Dirk and I started by explaining what a business process entails and the relation between business processes and software development. Furthermore, we made a diagram (swimming lanes) in Visio which represents the ordering process of our application before and after the automatization. 
- [Business processes research](https://github.com/DirkLemmen/Researches/blob/master/Business%20processes.md)

### Outcome 3: Requirements and design
You translate (non-functional) requirements to extend existing (architectural) designs and can validate them using multiple types of test techniques. <br>
The teacher agreed upon the proposal of the group, to split up all test methodologies into our own UX research.

Dirk Lemmen made issues for the group, where I made my usability test (First Click):
- [Requirements and design Research](https://github.com/DirkLemmen/Researches/issues/10)

![image](https://user-images.githubusercontent.com/48807736/151007040-03a7ef2c-a13a-4d9d-92a5-079334cc743a.png)


### Outcome 4: Cultural differences and ethics
You recognize and take into account cultural differences when working with multi-site teams and are aware of ethical aspects in software development. <br>
For this research I used my experience in cultural differences within the group that im working with, and my general knowledge about it. I read one of the suggested books called The Culture Map and also asked the teacher about the TICT Tool I used in this document.
- [Culture and Ethics research](https://github.com/FHICT-S-Siem/S3-SiemLucassen-HAP/blob/main/cultural-differences.md)

### Outcome 5: Professional
You act in a professional manner during software development and learning. <br>
Below is shown that I'm able to communicate in a n honest and professional manner towards my group, teachers and stakeholders. We as a group had a lot of ups-and downs, but we made the best of it at the end. <br>

Feedpulse: <br>
![image](https://user-images.githubusercontent.com/48807736/151007752-1b9167dd-97c9-4d5c-847a-012d62f84469.png)<br>

Stakeholder-communication: <br>
![image](https://user-images.githubusercontent.com/48807736/151008539-b91a5ef5-3f4e-4c17-a587-6ab063276b30.png)


### Outcome 6: Web application
You design and build user-friendly, full-stack web applications. <br>
I made sure to make a list of my individual contribution every sprint, so the teacher is up-to date about my contribution to the project. <br>

- [JIRA SCRUM Project Board](https://fontysict.atlassian.net/jira/software/projects/SCRUM/boards/1)
- [UX-Tests](https://github.com/DirkLemmen/Researches/issues?q=is%3Aopen+is%3Aissue) <br>

[Siem Lucassen Individual contribution_Final.pdf](https://github.com/FHICT-S-Siem/S3-SiemLucassen-HAP/files/7934992/Siem.Lucassen.Individual.contribution_Final.pdf)

