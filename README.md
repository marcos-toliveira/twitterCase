## Micronaut 2.3.0 Documentation

- [User Guide](https://docs.micronaut.io/2.3.0/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.3.0/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.3.0/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Google Cloud Run GraalVM GitHub Workflow

Workflow file: [`.github/workflows/google-cloud-run-graalvm.yml`](.github/workflows/google-cloud-run-graalvm.yml)

### Workflow description
For pushes to the `master` branch, the workflow will:
1. Setup the build environment with respect to the selected java/graalvm version.
2. Setup of [Google Cloud CLI](https://cloud.google.com/sdk).
3. Authenticate docker to use [Google Container Registry (GCR)](https://cloud.google.com/container-registry/docs).
4. Build, tag and push Docker image with Micronaut application to GCR.
6. Deploy [Google Cloud Run](https://cloud.google.com/run) application.

### Dependencies on other GitHub Actions
- [Setup GraalVM `DeLaGuardo/setup-graalvm`](https://github.com/DeLaGuardo/setup-graalvm)
- [Setup Google Cloud CLI `google-github-actions/setup-gcloud`](https://github.com/google-github-actions/setup-gcloud)

### Setup
Add the following GitHub secrets:

| Name | Description |
| ---- | ----------- |
| GCLOUD_PROJECT_ID | Project id. |
| GCLOUD_SA_KEY | Service account key file. See more on [Creating and managing service accounts](https://cloud.google.com/iam/docs/creating-managing-service-accounts#iam-service-accounts-create-gcloud) and [Deployment permissions for CloudRun](https://cloud.google.com/run/docs/reference/iam/roles#additional-configuration) |
| GCLOUD_IMAGE_REPOSITORY | (Optional) Docker image repository in GCR. For image `[GCLOUD_REGION]/[GCLOUD_PROJECT_ID]/foo/bar:0.1`, the `foo` is an _image repository_. |

The workflow file also contains additional configuration options that are now configured to:

| Name | Description | Default value |
| ---- | ----------- | ------------- |
| GCLOUD_REGION | Region where the Cloud Run application will be created. See [Cloud Run Release Notes](https://cloud.google.com/run/docs/release-notes) to find out what regions are supported. | `europe-west3` |
| GCLOUD_GCR | Google Container Registry url. See [Overview of Container Registry](https://cloud.google.com/container-registry/docs/overview) to find out valid GCR endpoints. | `eu.gcr.io` |

### Verification
From the workflow step `Deploy Cloud Run` copy out url `https://twitterCase-__________run.app` of the invoke endpoint:
```
Invoke endpoint:
https://twitterCase-__________run.app
```

Call the api endpoint:
```
curl https://twitterCase-__________run.app/twitterCase
```

## Push GraalVM Native Image To Docker Registry Workflow

Workflow file: [`.github/workflows/graalvm.yml`](.github/workflows/graalvm.yml)

### Workflow description
For pushes to the `master` branch, the workflow will:
1. Setup the build environment with respect to the selected java/graalvm version.
2. Login to docker registry based on provided configuration.
3. Build, tag and push Docker image with Micronaut application to the Docker container image.

### Dependencies on other GitHub Actions
- [Docker login](`https://github.com/docker/login-action`)(`docker/login`)
- [Setup GraalVM](`https://github.com/DeLaGuardo/setup-graalvm`)(`DeLaGuardo/setup-graalvm`)

### Setup
Add the following GitHub secrets:

| Name | Description |
| ---- | ----------- |
| DOCKER_USERNAME | Username for Docker registry authentication. |
| DOCKER_PASSWORD | Docker registry password. |
| DOCKER_REPOSITORY_PATH | Path to the docker image repository inside the registry, e.g. for the image `foo/bar/micronaut:0.1` it is `foo/bar`. |
| DOCKER_REGISTRY_URL | Docker registry url. |
#### Configuration examples
Specifics on how to configure public cloud docker registries like DockerHub, Google Container Registry (GCR), AWS Container Registry (ECR),
Oracle Cloud Infrastructure Registry (OCIR) and many more can be found in [docker/login-action](https://github.com/docker/login-action)
documentation.

#### DockerHub

- `DOCKER_USERNAME` - DockerHub username
- `DOCKER_PASSWORD` - DockerHub password or personal access token
- `DOCKER_REPOSITORY_PATH` - DockerHub organization or the username in case of personal registry
- `DOCKER_REGISTRY_URL` - No need to configure for DockerHub

> See [docker/login-action for GCR](https://github.com/docker/login-action#dockerhub)

#### Google Container Registry (GCR)
Create service account with permission to edit GCR or use predefined Storage Admin role.

- `DOCKER_USERNAME` - set exactly to `_json_key`
- `DOCKER_PASSWORD` - content of the service account json key file
- `DOCKER_REPOSITORY_PATH` - `<project-id>/foo`
- `DOCKER_REGISTRY_URL` - `gcr.io`

> See [docker/login-action for GCR](https://github.com/docker/login-action#google-container-registry-gcr)

#### AWS Elastic Container Registry (ECR)
Create IAM user with permission to push to ECR (or use AmazonEC2ContainerRegistryFullAccess role).

- `DOCKER_USERNAME` - access key ID
- `DOCKER_PASSWORD` - secret access key
- `DOCKER_REPOSITORY_PATH` - no need to set
- `DOCKER_REGISTRY_URL` - set to `<aws-account-number>.dkr.ecr.<region>.amazonaws.com`

> See [docker/login-action for ECR](https://github.com/docker/login-action#aws-elastic-container-registry-ecr)

#### Oracle Infrastructure Cloud Registry (OCIR)
[Create auth token](https://www.oracle.com/webfolder/technetwork/tutorials/obe/oci/registry/index.html#GetanAuthToken) for authentication.

- `DOCKER_USERNAME` - username in format `<tenancy>/<username>`
- `DOCKER_PASSWORD` - account auth token
- `DOCKER_REPOSITORY_PATH` - `<tenancy>/<registry>/foo`
- `DOCKER_REGISTRY_URL` - set to `<region>.ocir.io`

> See [docker/login-action for OCIR](https://github.com/docker/login-action#oci-oracle-cloud-infrastructure-registry-ocir)

## Feature spring documentation

- [Micronaut Spring Framework Annotations documentation](https://micronaut-projects.github.io/micronaut-spring/latest/guide/index.html)

## Feature mockito documentation

- [https://site.mockito.org](https://site.mockito.org)

## Feature hibernate-jpa documentation

- [Micronaut Hibernate JPA documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#hibernate)

## Feature jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)

## Feature security-oauth2 documentation

- [Micronaut Security OAuth 2.0 documentation](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html#oauth)

## Feature lombok documentation

- [Micronaut Project Lombok documentation](https://docs.micronaut.io/latest/guide/index.html#lombok)

- [https://projectlombok.org/features/all](https://projectlombok.org/features/all)

## Feature management documentation

- [Micronaut Management documentation](https://docs.micronaut.io/latest/guide/index.html#management)

## Feature security-session documentation

- [Micronaut Security Session documentation](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html#session)

## Feature github-workflow-google-cloud-run-graalvm documentation

- [https://docs.github.com/en/free-pro-team@latest/actions](https://docs.github.com/en/free-pro-team@latest/actions)

## Feature testcontainers documentation

- [https://www.testcontainers.org/](https://www.testcontainers.org/)

## Feature dekorate-prometheus documentation

- [Micronaut Dekorate Prometheus Support documentation](https://micronaut-projects.github.io/micronaut-kubernetes/latest/guide/index.html)

- [https://github.com/dekorateio/dekorate#prometheus-annotations](https://github.com/dekorateio/dekorate#prometheus-annotations)

## Feature security documentation

- [Micronaut Security documentation](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature dekorate-kubernetes documentation

- [Micronaut Dekorate Kubernetes Support documentation](https://micronaut-projects.github.io/micronaut-kubernetes/latest/guide/index.html)

- [https://github.com/dekorateio/dekorate#kubernetes](https://github.com/dekorateio/dekorate#kubernetes)

## Feature assertj documentation

- [https://assertj.github.io/doc/](https://assertj.github.io/doc/)

## Feature elasticsearch documentation

- [Micronaut Elasticsearch Driver documentation](https://micronaut-projects.github.io/micronaut-elasticsearch/latest/guide/index.html)

## Feature github-workflow-graal-docker-registry documentation

- [https://docs.github.com/en/free-pro-team@latest/actions](https://docs.github.com/en/free-pro-team@latest/actions)

## Feature views-thymeleaf documentation

- [Micronaut Thymeleaf views documentation](https://micronaut-projects.github.io/micronaut-views/latest/guide/index.html#thymeleaf)

- [https://www.thymeleaf.org/](https://www.thymeleaf.org/)

## Feature hibernate-validator documentation

- [Micronaut Hibernate Validator documentation](https://micronaut-projects.github.io/micronaut-hibernate-validator/latest/guide/index.html)

