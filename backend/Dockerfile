# 1 FASE Build Stage: Build dell'applicazione
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia i file delle dipendenze per sfruttare la cache di Docker
# mvn dependency:go-offline -B scarica in anticipo tutte le dipendenze, plugin del progetto e li salva nella cache locale di Maven 
# mvn dependency:go-offline: Dice a Maven di analizzare il file pom.xml e scaricare tutti i JAR necessari per poter lavorare "offline" in seguito.
# -B Maven non si blocca in attesa di un input da tastiera
COPY pom.xml .
RUN mvn dependency:go-offline -B 

# Copia i sorgenti e compila il file .jar (saltando i test per velocizzare)
# clean: cancella la cartella target/ e ripulisce la build precedente per evitare conflitti  tra file vecchi e nuovi 
# package: complila il codice sorgente, esegue i test e confeziona l'app nel formato definito nel pom.xml
# -Dskiptests: Dice a Maven di saltare l'esecuzione dei test unitari ed d'integrazione. Questo serve a velocizzare il processo di build dell'immagine Docker.
COPY src ./src
RUN mvn clean package -DskipTests

# 2 FASE (stage): Runtime leggero per l'esecuzione
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia il JAR generato nello stadio precedente
# --from=builder dice a Docker di non prendere il file dal pc locale ma da un precedente layer di build chiamato builder (vedere inizio file)
# /app/target/*.jar: percorso sorgente nello stage builder dove Maven ha generato il file eseguibile dopo il comando mvn package
# app.jar: nome finale con cui il file viene salvato all'interno del container corrente
COPY --from=builder /app/target/*.jar app.jar

# EXPOSE 8080 dice a Docker che la tua app Java ascolta su questa porta di rete 
EXPOSE 8080
# Definisce il comando principale che verrà eseguito automaticamente quando il container viene avviato.
ENTRYPOINT ["java", "-jar", "app.jar"]