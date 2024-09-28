# Dockerfile
FROM openjdk:17-oracle

WORKDIR /app

COPY target/pruebas3joelarias-0.0.1-SNAPSHOT.jar app.jar


COPY Wallet_BDFULLSTACK1/ /app/oracle_wallet/


EXPOSE 8080


CMD ["java", "-jar", "app.jar"]
