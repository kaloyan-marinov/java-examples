# Project layout

```shell
$ tree -I target         
.
├── LICENSE
├── pom.xml
├── README.md
└── src
    └── main
        └── java
            └── com
                └── github
                    ├── App.java
                    └── Exclamation.java
```

# Commands

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
```
