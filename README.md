# Getting Started

## Run it 

```
git clone git@github.com:rayandfz/test-tech.git 
cd test-tech
docker build -t myapp .
docker run -p 8080:8080 myapp
```

## Test

```
cd test-tech
mvnw test 
``` 

## Documentation

```
cd test-tech
mvnw javadoc:javadoc
```