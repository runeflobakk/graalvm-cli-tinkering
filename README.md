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
