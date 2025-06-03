

# Task 4: Build a native executable

Requiring the JDK to be installed, with the correct version, enabling preview features, are cumbersome. We will now instead create a native executable which can be run as any other command/program in your terminal.

See if you are able to configure the GraalVM `native-maven-plugin` to produce a native image (or native executable) by following the Getting started guide at [https://graalvm.github.io/native-build-tools/latest/end-to-end-maven-guide.html](https://graalvm.github.io/native-build-tools/latest/end-to-end-maven-guide.html).

Hints:
Set up the base configuration in the existing `pluginManagement`, specifying the plugin version, setting it as `extensions=true` and enabling preview features is done with this:

```xml
<configuration>
    <jvmArgs>--enable-preview</jvmArgs>
</configuration>
```

Then set up a profile with id "native", configuring an execution of the `compile-no-fork` goal in your build.

You are not required to configure your main-class, as the `native-maven-plugin` is capable of using the existing configuration of the JAR-plugin.

You can try to build the native executable like this:

```sh
mvn verify -Pnative
```

And you should end up with the executable named only `cli` in the target-folder, and you can run it like this:

```
target/cli
```





# Task 3: Build an "executable JAR"

Configure your Maven project to package your Java class in a JAR file, with a directive pointing to it as a "Main class", which makes the JAR file executable without needing to know what contained class is the entrypoint for execution.

Maven expects to find Java sources in the folder `src/main/java`, so start with moving your Java source:

```sh
mkdir -p src/main/java
mv <your-source-file> src/main/java
```

Configure compilation to target Java version 24 with enabled preview features (needed for simplified main classes). Add this properties to `pom.xml`

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.release>24</maven.compiler.release>
    <maven.compiler.enablePreview>true</maven.compiler.enablePreview>
</properties>
```

(the "sourceEncoding" property is a bonus to get rid of some warnings)




Lastly the [maven-jar-plugin](https://maven.apache.org/plugins/maven-jar-plugin/) like this in your `pom.xml` file:

```xml
<build>
    <pluginManagement>
        <plugins>
            <plugin>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.4.2</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>[your-source-file-basename]</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </pluginManagement>
</build>
```

Running `mvn clean verify` should now produce the file `target/cli-1.0-SNAPSHOT.jar`, and to verify it works as it should, you can try to run it with

```sh
java --enable-preview -jar target/cli-1.0-SNAPSHOT.jar
```


Continue to [Task 4](https://github.com/runeflobakk/graalvm-cli-tinkering/tree/task4)




# Task 2: Minimal Maven POM

Verify that Maven uses your installed GraalVM JDK:

```sh
mvn -v
```

Should produce output containing:

```
Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
Maven home: /opt/homebrew/Cellar/maven/3.9.9/libexec
Java version: 24.0.1, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/graalvm-24.jdk/Contents/Home
```

Pay attention that the directory for `Java version:` points to your installed GraalVM installation.

Try to run a Maven command to build the project. E.g:

```sh
mvn verify
```

You should see something like this:

```
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.065 s
[INFO] Finished at: 2025-06-04T14:28:56+02:00
[INFO] ------------------------------------------------------------------------
[ERROR] The goal you specified requires a project to execute but there is no POM in this directory
```

Create a minimal `pom.xml` file needed to have a successful build (although it may not do much).
Your pom.xml can contain this:

```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>cli</artifactId>
    <groupId>no.bekk.java.tgp</groupId>
    <version>1.0-SNAPSHOT</version>
</project>
```

Now, run `mvn verify` again, at it should emit a `BUILD SUCCESS` message


Continue to [Task 3](https://github.com/runeflobakk/graalvm-cli-tinkering/tree/task3)



--------------------



# Task 0: Install GraalVM

Suggestion for MacOS:
- `brew install graalvm-jdk`
- `brew install jenv` (skip if you already have a working jenv installation)
    - [set up jEnv for your shell](https://github.com/jenv/jenv?tab=readme-ov-file#12-configuring-your-shell)
    - verify it works with `jenv doctor`
- `jenv enable-plugin maven`
- `ln -s /Library/Java/JavaVirtualMachines/graalvm-24.jdk/Contents/Home ~/.jenv/versions/graalvm-24`
- `jenv shell graalvm-24`


You are done when you get the following output from the command `java --version`:

```
java 24.0.1 2025-04-15
Java(TM) SE Runtime Environment Oracle GraalVM 24.0.1+9.1 (build 24.0.1+9-jvmci-b01)
Java HotSpot(TM) 64-Bit Server VM Oracle GraalVM 24.0.1+9.1 (build 24.0.1+9-jvmci-b01, mixed mode, sharing)
```


# Task 1: Create a small program for the GraalVM JVM

Create a small runnable program (it may just be the traditional "Hello World" we all know and love) in the root of the repository.
It should use the [new simple form](https://docs.oracle.com/en/java/javase/24/language/simple-source-files-and-instance-main-methods.html)
with an _instance main method_.

Run it with GraalVM with enabled preview features:

```sh
java --enable-preview <your-source-file.java>
```

Continue to [Task 2](https://github.com/runeflobakk/graalvm-cli-tinkering/tree/task2)
