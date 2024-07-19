# Use a imagem base do OpenJDK 11
FROM openjdk:11

# Crie o diretório de aplicativos dentro do container
RUN mkdir -p /usr/src/app

# Copie o arquivo JAR da pasta target para dentro do container
COPY target/consulta-cep-0.0.1.jar /usr/src/app/consulta-cep-0.0.1.jar

# Especifique o diretório de trabalho
WORKDIR /usr/src/app

# Exponha a porta 9090 para fora do container
EXPOSE 9090

# Comando para executar a aplicação quando o container iniciar
CMD ["java", "-jar", "consulta-cep-0.0.1.jar"]

