# 1. Use the lightweight Java image
FROM eclipse-temurin:17-jdk-alpine

# 2. Set working folder
WORKDIR /app

# 3. Copy only the JAR file (Do NOT copy wait-for-it.sh)
COPY target/*.jar app.jar

# 4. Expose your port
EXPOSE 1101

# 5. Start the app directly
ENTRYPOINT ["java", "-jar", "app.jar"]