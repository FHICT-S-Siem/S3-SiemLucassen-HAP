building the dockerfile
```
cd 'C:\Users\siem2\Desktop\Fontys\Semester 3\IP\House Assistant Platform\S3-SiemLucassen-HAP\Jetson'
docker build -t siemvm2:5000/siemsensor -f .Dockerfile .
docker push siemvm2:5000/siemsensor
```

run the container on the jetson nano
```
docker pull siemvm2:5000/siemsensor
docker stop siemsensor || true && docker rm siemsensor || true
docker run --device /dev/i2c-1 -d --network host -p 5000:5000 --name siemsensor siemvm2:5000/siemsensor
```

device "/dev/i2c-1" is the SensorHub.