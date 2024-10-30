# Biblioteca

### Descrição
Sistema de biblioteca para gerenciar usuários, livros e realizar empréstimos. Permite o registro de usuários e livros, além de consultas e operações de empréstimos.

### Funcionalidades
- :heavy_check_mark: `Funcionalidade 1:` Registrar usuários e livros
- :heavy_check_mark: `Funcionalidade 2:` Listar livros, usuários e empréstimos
- :heavy_check_mark: `Funcionalidade 3:` Excluir livros e usuários
- :heavy_check_mark: `Funcionalidade 4:` Realizar empréstimos de livros
- :heavy_check_mark: `Funcionalidade 5:` Atualizar informações de usuários e livros

### Tecnologias Utilizadas

- **Java 15**
- **Maven**
- **MySQL** (Banco de Dados)
- **JDBC** (Java Database Connectivity)

### Menu de Opções
```                                               plaintext
-----------------------------------------------------
                 FUNCIONALIDADES 
------------------------------------------------------
1. Registrar novo usuário
2. Listar todos os usuários
3. Consultar um usuário específico
4. Atualizar usuário
5. Excluir usuário
------------------------------------------------------
6. Registrar novo livro
7. Listar todos os livros
8. Consultar um livro específico
9. Atualizar livro
10. Excluir livro
------------------------------------------------------
11. Listar todos os empréstimos
12. Realizar empréstimo
13. Realizar devolução
14. Sair
```

### Instalação
Para executar o sistema localmente, siga as etapas abaixo:
1. Clone este repositório.
2. Certifique-se de que a JDK do Java 15 (ou superior) esteja instalada.
3. Verifique se o Driver JDBC do MySQL está disponível no projeto.
4. Instale o MySQL, se ainda não estiver instalado.
5. Configure a senha para o usuário root do MySQL, utilizando a porta padrão (3306).
6. Atualize o arquivo de conexão com o banco de dados, localizado em ```src/java/com/devemg/data/MysqlConnection.java```, com sua senha e usuário root do MySQL.
7. Execute o programa a partir do arquivo ```Main.java```.

-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)

### SQL 

````
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_autor`
--

DROP TABLE IF EXISTS `tb_autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_autor` (
  `id_autor` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  PRIMARY KEY (`id_autor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_editora`
--

DROP TABLE IF EXISTS `tb_editora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_editora` (
  `id_editora` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY (`id_editora`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_emprestimo`
--

DROP TABLE IF EXISTS `tb_emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_emprestimo` (
  `id_emprestimo` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `id_obra` int NOT NULL,
  PRIMARY KEY (`id_emprestimo`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_obra` (`id_obra`),
  CONSTRAINT `tb_emprestimo_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`),
  CONSTRAINT `tb_emprestimo_ibfk_2` FOREIGN KEY (`id_obra`) REFERENCES `tb_obra` (`id_obra`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_endereco`
--

DROP TABLE IF EXISTS `tb_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_endereco` (
  `cep` varchar(8) NOT NULL,
  `cidade` varchar(80) NOT NULL,
  `estado` varchar(80) NOT NULL,
  `numero` int NOT NULL,
  `rua` varchar(80) NOT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `fk_usuario` int NOT NULL,
  PRIMARY KEY (`cep`),
  KEY `fk_usuario` (`fk_usuario`),
  CONSTRAINT `tb_endereco_ibfk_1` FOREIGN KEY (`fk_usuario`) REFERENCES `tb_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_exemplar`
--

DROP TABLE IF EXISTS `tb_exemplar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_exemplar` (
  `id_exemplar` int NOT NULL,
  `num_exemplar` int NOT NULL,
  `dt_aquisicao` date NOT NULL,
  `id_obra` int NOT NULL,
  `id_editora` int NOT NULL,
  PRIMARY KEY (`id_exemplar`),
  KEY `id_obra` (`id_obra`),
  KEY `id_editora` (`id_editora`),
  CONSTRAINT `tb_exemplar_ibfk_1` FOREIGN KEY (`id_obra`) REFERENCES `tb_obra` (`id_obra`),
  CONSTRAINT `tb_exemplar_ibfk_2` FOREIGN KEY (`id_editora`) REFERENCES `tb_editora` (`id_editora`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_genero`
--

DROP TABLE IF EXISTS `tb_genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_genero` (
  `id_genero` int NOT NULL AUTO_INCREMENT,
  `nm_genero` varchar(80) NOT NULL,
  PRIMARY KEY (`id_genero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_genero_obra`
--

DROP TABLE IF EXISTS `tb_genero_obra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_genero_obra` (
  `id_genero` int NOT NULL,
  `id_obra` int NOT NULL,
  PRIMARY KEY (`id_genero`,`id_obra`),
  KEY `id_obra` (`id_obra`),
  CONSTRAINT `tb_genero_obra_ibfk_1` FOREIGN KEY (`id_genero`) REFERENCES `tb_genero` (`id_genero`),
  CONSTRAINT `tb_genero_obra_ibfk_2` FOREIGN KEY (`id_obra`) REFERENCES `tb_obra` (`id_obra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_obra`
--

DROP TABLE IF EXISTS `tb_obra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_obra` (
  `id_obra` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `quantidade` int NOT NULL,
  `ano_publicacao` char(80) DEFAULT NULL,
  `editora` char(80) DEFAULT NULL,
  `autor` char(80) DEFAULT NULL,
  `genero` char(80) DEFAULT NULL,
  PRIMARY KEY (`id_obra`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_obra_autor`
--

DROP TABLE IF EXISTS `tb_obra_autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_obra_autor` (
  `id_obra` int NOT NULL,
  `id_editora` int NOT NULL,
  `id_autor` int NOT NULL,
  PRIMARY KEY (`id_obra`,`id_editora`,`id_autor`),
  KEY `id_editora` (`id_editora`),
  KEY `id_autor` (`id_autor`),
  CONSTRAINT `tb_obra_autor_ibfk_1` FOREIGN KEY (`id_obra`) REFERENCES `tb_obra` (`id_obra`),
  CONSTRAINT `tb_obra_autor_ibfk_2` FOREIGN KEY (`id_editora`) REFERENCES `tb_editora` (`id_editora`),
  CONSTRAINT `tb_obra_autor_ibfk_3` FOREIGN KEY (`id_autor`) REFERENCES `tb_autor` (`id_autor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `dt_nascimento` char(80) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

````

## :robot: Tecnologias

![Java](https://img.shields.io/badge/Java-ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
