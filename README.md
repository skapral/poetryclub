# Poetry Club

[![Travis Build](https://travis-ci.org/skapral/poetryclub.svg?branch=master)](https://travis-ci.org/skapral/poetryclub)
[![Build status](https://ci.appveyor.com/api/projects/status/ny3rmbdxubkbqmm2/branch/master?svg=true)](https://ci.appveyor.com/project/skapral/poetryclub/branch/master)
[![Codecov](https://codecov.io/gh/skapral/poetryclub/branch/master/graph/badge.svg)](https://codecov.io/gh/skapral/poetryclub)

Poetry Club is a web service for community of creative people, united by common idea, where each member can expect a 
support from the others.

## How it works

Community, hosted by Poetry Club, has one administrator and arbitrary number of members. Each member (including 
the admin) has following responsibilities to the community:

1. Once a month they must publish some contribution.

2. Each of the member must read the contributions, made by other members and provide some feedback. It may be a 
constructive discussion, raised in social networks, or a share/repost.

A system aggregates the members activity and provides agenda for administrator, showing them the users who violate
the rules and don't support the community. Upon administrator's decision, they may be banned.

## How to build

```bash
$ mvn clean install
```

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

| Name               | Description                                                              |
|--------------------|--------------------------------------------------------------------------|
| PORT               | Optional. Integer. Port number, to which the service is bound. Default is|
|                    | 5000.                                                                    |
| OWNER              | Mandatory. Github login name for the user, owning the service instance.  |
|                    | When logged in first time, this user will be prompted to craete a        | 
|                    | community.                                                               |
| JDBC_DATABASE_URL  | Mandatory. JDBC URL to the Postgresql database instance.                 |
| GITHUB_API_KEY     | Mandatory. Github OAuth application's API key.                           |
| GITHUB_API_SECRET  | Mandatory. Github OAuth application's API secret.                        |
| TEST_ENV           | Optional. If set, the instance will be deployed with stubbed             |
|                    | authentication. Convenient for testing.                                  |
| DOCKER_HOST        | Optional. Default is `http://localhost:2375`. Docker host, used by       | 
|                    | Testcontainers during integration tests execution                        |

## License

 MIT License
 
 Copyright (c) 2018 Kapralov Sergey
 
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
