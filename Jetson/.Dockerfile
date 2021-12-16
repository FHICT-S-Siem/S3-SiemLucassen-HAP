# jetson nano ubuntu with python base container
FROM balenalib/jetson-nano-ubuntu-python

RUN apt-get update
RUN apt-get install -y build-essential

ENV LANG=nl_NL.UTF-8  
ENV LC_ALL=nl_NL.UTF-8 

RUN python -m pip install --upgrade pip

# install smbus package for communicating with the sensors and Flask to host webserver.
RUN pip3 install smbus Flask

# install pika: library for rabbitMQ
RUN pip3 install pika

# copy the python code.
COPY server.py server.py

# expose the port for manually fetching sensor data.
EXPOSE 5000
ENV FLASK_APP=server.py

# Default room set to siem in case there is no argument given.
ARG ROOM=siem

ENV ROOM=$ROOM

# entrypoint for the python code.
# ENTRYPOINT [ "python3", "-m", "flask", "run", "--host=0.0.0.0", "--port=5000" ]
ENTRYPOINT [ "python3", "server.py" ]