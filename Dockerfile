FROM openjdk:17
WORKDIR /src
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build
CMD ["java", "-classpath", "./build/classes/java/main/", "org.example.Main"]
