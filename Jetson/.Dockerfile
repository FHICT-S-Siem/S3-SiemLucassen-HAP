# jetson nano ubuntu with python base container
FROM balenalib/jetson-nano-ubuntu-python

RUN apt-get update
RUN apt-get install -y build-essential

RUN python -m pip install --upgrade pip

# install smbus package for communicating with the sensors and Flask to host webserver.
RUN pip3 install smbus Flask

# copy the python code.
COPY server.py server.py

# expose the port for manually fetching sensor data.
EXPOSE 5000
ENV FLASK_APP=server.py

# entrypoint for the python code.
ENTRYPOINT [ "python3", "-m", "flask", "run", "--host=0.0.0.0", "--port=5000" ]