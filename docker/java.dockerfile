FROM registry.access.redhat.com/ubi8/openjdk-11-runtime
COPY target/smasher-runner.jar .
CMD ["java","-jar","smasher-runner.jar"]