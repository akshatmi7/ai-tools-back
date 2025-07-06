# Step 1: Use an official Java 17 image
FROM eclipse-temurin:17-jdk

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file (you will create this in step 2)
COPY build/libs/*.jar app.jar

# Step 4: Expose port 8080 to make your app accessible
EXPOSE 8080

# Step 5: Start the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
