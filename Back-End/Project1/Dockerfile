FROM gradle

COPY . /workspace

WORKDIR /workspace

RUN gradle task customFatJar
# A step to run when making the image to create my jar file

WORKDIR /workspace/build/libs

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Project1-App-1.0-SNAPSHOT.jar"]