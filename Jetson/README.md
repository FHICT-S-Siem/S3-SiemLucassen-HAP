building the dockerfile
```
cd ~/Repositories/Siem/S3-SiemLucassen-HAP/Jetson/
docker build -t siemsensor -f .Dockerfile .
```

run the container on the jetson nano
```
docker run --device /dev/i2c-1 --rm -p 5000:5000 siemsensor
```

device "/dev/i2c-1" is the SensorHub.