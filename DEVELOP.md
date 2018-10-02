# Development and deployment

## How to build

```bash
$ mvn clean install
```

### Database schema management

Project uses [FlyWay DB](https://flywaydb.org/) for database schema versioning, and [JOOQ](jooq.org) framework for 
accessing database. After each impact on database schema, JOOQ supplementary classes regeneration must be launched. 
Regeneration procedure assumes that:

- There is a Postgresql database instance, available at JDBC_URL: `jdbc:postgresql://127.0.0.1:5432/postgres?user=postgres&password=postgres`

To launch JOOQ sources regeneration:

```bash
$ mvn clean install -Pjooq-regenerate
```

### Integration tests

Project uses integration testing, based on [Testcontainers](https://www.testcontainers.org) and 
[Webdriver](http://chromedriver.chromium.org/). Integration tests expect that:

1. Chrome browser is installed on host
2. Chrome [webdriver](http://chromedriver.chromium.org/) of the version, compatible with installed Chrome browser, is 
installed and available in PATH
3. Docker is installed on host and is online (see Testcontainers 
[prerequisites](https://www.testcontainers.org/usage.html#prerequisites))

Integration tests are supported only on Linux-based hosts. You can skip them by using either of two commands below:

```bash
$ mvn clean install -DskipTests
```

## How to deploy

0. Prerequisites: Oracle/Openjdk Java 8 or greater. Postgresql 10.
1. Prepare postgres, GitHub OAuth integration and set up mandatory environment variables.
2. `mvn dependency:get -DgroupId=com.github.skapral.poetryclub -DartifactId=poetryclub-app -Dversion=<version number>`
3. `java -jar poetryclub-app-*.jar` 

Note that Poetry Club supports also deployment to Dokku and Heroku.

### Environment variables

| Name               | Description                                                                                     |
|--------------------|-------------------------------------------------------------------------------------------------|
| PORT               | Optional. Integer. Port number, to which the service is bound. Default is 5000.                 |
| OWNER              | Mandatory. Github login name for the user, owning the service instance. When logged in first time, this user will be prompted to craete a community. |
| JDBC_DATABASE_URL  | Mandatory. JDBC URL to the Postgresql database instance.                                        |
| GITHUB_API_KEY     | Mandatory. Github OAuth application's API key.                                                  |
| GITHUB_API_SECRET  | Mandatory. Github OAuth application's API secret.                                               |
| TEST_ENV           | Optional. If set, the instance will be deployed with stubbed authentication. Convenient for testing. |
| DOCKER_HOST        | Optional. Default is `http://localhost:2375`. Docker host, used by Testcontainers during integration tests execution |

