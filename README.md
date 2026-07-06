Remove previous build output from the `target/` directory,
and then run through several phases to compile, test, and package the project into an executable JAR file:
```shell
$ mvn \
    clean \
    package
```

Run the executable JAR file:
```shell
$ java \
    -cp target/classes \
    com.github.App
Hello World!
```

