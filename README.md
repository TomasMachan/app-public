# Simple greeting service on Liberty Profile

## Start in dev mode

```bash
cd app
./mvnw liberty:dev
```

## Package

```bash
cd app
./mvnw clean package
```

## Build an application image

```bash
export BASE_IMAGE=icr.io/appcafe/websphere-liberty:23.0.0.6-kernel-java8-openj9-ubi

# a build with online access to a feature repository
podman build --platform=linux/amd64 -t greeting:latest --build-arg BASE_IMAGE=$BASE_IMAGE .

# a build with a locally hosted feature repository
podman build --platform=linux/amd64 -t greeting:latest --build-arg BASE_IMAGE=$BASE_IMAGE --build-arg FEATURE_REPO_URL="http://host.containers.internal:8080/23.0.0.6/repo.zip" --build-arg VERBOSE=true .
```

## Run a container

```bash
export GREETINGS="Hello from WebSphere Liberty.;How are you?"
podman run -d --name greeting --rm -p 9080:9080 -e GREETINGS=$GREETINGS greeting:latest
```

## Endpoints

The `/greetings` endpoint is accessible at `http://<host>:<port>/api/greetings`

## Environment variables

| Env         | Description                        | Default value                          |
| ----------- | ---------------------------------- | -------------------------------------- |
| `GREETINGS` | A list of greetings divided by ";" | `(You did not specify any greetings.)` |
