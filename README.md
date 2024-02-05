# Cron Expression Parser

### Prerequisites
In order to build and run application the following tools are required:
Java in version at least 21 

#### For development: 
JDK in version at least 21, suggested Amazon Corretto 21: https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html

Maven in version at least 3.9.2 https://maven.apache.org/docs/3.9.2/release-notes.html

Git for project management https://git-scm.com/downloads

Suggested IDE is IDEAIntelliJ as examples in this readme can be one click triggered  https://www.jetbrains.com/idea/download

#### Testing application
Application is tested with use of JUni5 Jupiter and relies heavily on the concept of parametrized tests.

### Installation 
[Download jar file directly](lib/cron-parser.jar) or build it locally: 
1. Checkout repository from GitLab or unzip it
2. Navigate to root directory of repository
3. Run
```shell
mvn install -f pom.xml
```
 this should produce in JAR file in target folder

### Application run

While being in root directory of project and having Java properly installed
```shell
java -jar ./target/cron-parser-1.0-SNAPSHOT.jar
```

In case of downloading already prepared jar simply invoke

<code>java -jar pathToJarFile</code>


In order to get meaningful response other than help provide valid input, for instance:

```shell
java -jar ./target/cron-parser-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```

### Application usage/help
    CRON parser
    Application - prints execution times of provided command
    General usage:
    Provide command to application enclosed in " (double quites) in the following pattern:
    "1 2 3 4 5 /command"
    1 being minutes
    2 hours
    3 day of month
    4 day of week
    /command command to be executed
    Supported expression semantic:
    * (all) specifies that event should happen for every time unit in category.
    Example "1 1 1 * 1 /command" will print all seven days of week.
    – (range) determines the value range (inclusive)
    Example "1 1 1 2-4 1 /command" will print all 3 values for day: 2, 3 and 4.
    / (increments) specifies the incremental values
    Example "0/20 1 1 1 1 /command" will print incremented values by 20 for minutes, starting at 0: 0, 20, 40
    , (values) specifies multiple values.

### Requirements
Write a command line application or script which parses a cron string and expands each field
to show the times at which it will run. You should use either Java or Kotlin.

You should only consider the standard cron format with five time fields (minute, hour, day of
month, month, and day of week) plus a command, and you do not need to handle the special
time strings such as "@yearly". The input will be on a single line.

The cron string will be passed to your application as a single argument.
~$ your-program "*/15 0 1,15 * 1-5 /usr/bin/find"
The output should be formatted as a table with the field name taking the first 14 columns and
the times as a space-separated list following it.

For example, the following input argument:
*/15 0 1,15 * 1-5 /usr/bin/find
Should print the following output:
minute 0 15 30 45
hour 0
day of month 1 15
month 1 2 3 4 5 6 7 8 9 10 11 12
day of week 1 2 3 4 5
command /usr/bin/find

You should spend no more than three hours on this exercise. If you do not have time to
handle all possible cron strings then an app which handles a subset of them correctly is
better than one which does not run or produces incorrect results. You will be asked to extend
the solution with additional features while onsite.

You should see your project reviewer as a new team member you are handling the project
over to. Provide everything you feel would be relevant for them to ramp up quickly, such as
tests, a README and instructions for how to run your project in a clean OS X/Linux
environment.