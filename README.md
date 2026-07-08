# Project layout

```shell
$ tree -I target
.
├── LICENSE
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── github
    │               ├── App.java
    │               └── Exclamation.java
    └── test
        └── java
            └── com
                └── github
                    └── Test*.java
```

# Commands

Compile the tests and run the via the `maven-surefire-plugin`:
```shell
$ mvn test

# Run only one test class.
$ mvn test \
    -Dtest=Test_01_Exclamation

# Run only one test method.
$ mvn test \
    -Dtest=Test_01_Exclamation#build
```

Remove previous build output from the `target/` directory:
```shell
$ mvn clean
```

Compiles `src/main/com/github/` into `target/classes/com/github/`.
```shell
$ mvn compile
```

Run the executable JAR file:
```shell
$ java \
    -cp target/classes \
    com.github.App \
    attention
ATTENTION!

$ mvn \
    --quiet \
    exec:java \
    -Dexec.mainClass="com.github.App" \
    -Dexec.args="'WATCH OUT'"
WATCH OUT!
```
