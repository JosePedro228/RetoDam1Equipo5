/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;



DROP DATABASE IF EXISTS Inventario;
CREATE DATABASE IF NOT EXISTS Inventario;
USE Inventario;

DROP TABLE IF EXISTS roles;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS roles(
	id_rol INT PRIMARY KEY,
    nombre_rol VARCHAR(20)
);
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS ubicacion;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS ubicacion(
id_ubicacion INT PRIMARY KEY AUTO_INCREMENT,
tipo VARCHAR(20)
);
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS categoria;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS categoria(
	id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nombre_categoria VARCHAR(20)
    
);
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS usuarios;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS usuarios(
	id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(20) UNIQUE,
    contraseña VARCHAR(40),
    id_rol INT NOT NULL, 
    CONSTRAINT fk_id_rol FOREIGN KEY (id_rol) REFERENCES roles (id_rol)
); 
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS elementos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS elementos(
id_elemento INT PRIMARY KEY AUTO_INCREMENT,
id_categoria INT NOT NULL,
nombre VARCHAR(15) NOT NULL,
descripcion TEXT,
estado ENUM('disponible', 'prestado', 'en reparacion', 'baja') NOT NULL DEFAULT 'disponible',
id_ubicacion INT NOT NULL,
CONSTRAINT fk_id_ubicacion FOREIGN KEY (id_ubicacion) REFERENCES ubicacion(id_ubicacion),
CONSTRAINT fk_id_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS prestamos;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE IF NOT EXISTS prestamos(
id_usuario INT NOT NULL,
fecha DATETIME NOT NULL,
id_elemento INT NOT NULL,
devuelto BOOLEAN DEFAULT FALSE,
PRIMARY KEY (id_usuario, fecha, id_elemento),
CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios (id_usuario),
CONSTRAINT fk_id_elemento2 FOREIGN KEY (id_elemento) REFERENCES elementos (id_elemento)
);
/*!40101 SET character_set_client = @saved_cs_client */;








