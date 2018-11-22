/*
Created		19/10/2017
Modified		20/10/2018
Project
Model
Company
Author
Version
Database		mySQL 5
*/

DROP DATABASE IF EXISTS `registratec`;
CREATE DATABASE `registratec`;

USE registratec;

drop table IF EXISTS ACOMPANANTE;
drop table IF EXISTS LISTA_PONENTES;
drop table IF EXISTS PONENTE;
drop table IF EXISTS TUTOR;
drop table IF EXISTS ADMINISTRADOR;
drop table IF EXISTS REGISTRO_ACOMPANANTE;
drop table IF EXISTS LUGAR;
drop table IF EXISTS DEPARTAMENTO;
drop table IF EXISTS EVENTO;
drop table IF EXISTS ESCUELA;
drop table IF EXISTS REGISTRO_ALUMNO;
drop table IF EXISTS ALUMNO;

Create table `ALUMNO` (
	`id_alumno` varchar(15),
	`nombre` varchar(30) NOT NULL,
	`contrasena` varchar(30) NOT NULL,
	`direccion` varchar(60) NOT NULL,
	`telefono` varchar(15) NOT NULL,
	`email` varchar(20) NOT NULL,
	`fecha_nacimiento` Date,
	`programa` varchar(3) NOT NULL,
	`semestre` decimal(2,0) NOT NULL,
	`id_escuela` int(15) NOT NULL,
 Primary Key (`id_alumno`,`id_escuela`));

Create table `REGISTRO_ALUMNO` (
	`id_registro` int(15) NOT NULL AUTO_INCREMENT,
	`id_alumno` varchar(15) NOT NULL,
	`id_escuela` int(15) NOT NULL,
	`id_evento` int(15) NOT NULL,
	`fecha_registro` Date NOT NULL,
	`asistencia` Varchar(2),
	`fecha_asistencia` Date,
	`extras` Varchar(50),
 Primary Key (`id_registro`,`id_evento`));

Create table `ESCUELA` (
	`id_escuela` int(15) NOT NULL AUTO_INCREMENT,
	`nombre` Varchar(30) NOT NULL,
	`direccion` Varchar(60) NOT NULL,
	`telefono` Varchar(15) NOT NULL,
 Primary Key (`id_escuela`));

Create table `EVENTO` (
	`id_evento` int(15) NOT NULL AUTO_INCREMENT,
	`id_administrador` varchar(15) NOT NULL,
	`id_departamento` int(15) NOT NULL,
	`nombre` Varchar(30) NOT NULL,
	`fecha_inicio_evento` Date NOT NULL,
	`fecha_termino_evento` Date,
	`status_evento` Varchar(10),
	`capacidad_maxima` Decimal(3,0),
	`capacidad_actual` Decimal(3,0),
	`fecha_inicio_registro` Date,
	`fecha_termino_registro` Date,
	`carta` Varbinary(20),
	`requisitos` Varchar(50),
	`id_lugar` int(15),
	`no_acomp` Decimal(2,0) NOT NULL,
 Primary Key (`id_evento`));

Create table `DEPARTAMENTO` (
	`id_departamento` int(15) NOT NULL AUTO_INCREMENT,
	`nombre` Varchar(15) NOT NULL,
 Primary Key (`id_departamento`));

Create table `LUGAR` (
	`id_lugar` int(15) NOT NULL AUTO_INCREMENT,
	`nombre` Varchar(20) NOT NULL,
	`direccion` Varchar(60) NOT NULL,
	`telefono` Varchar(15),
 Primary Key (`id_lugar`));

Create table `REGISTRO_ACOMPANANTE` (
	`id_registro_acomp` int(15) NOT NULL AUTO_INCREMENT,
	`no_acompanante` Double NOT NULL,
	`id_registro` int(15) NOT NULL,
	`id_evento` int(15) NOT NULL,
	`fecha_registro` Date NOT NULL,
	`asistencia` Varchar(2),
	`fecha_asistencia` Date,
	`extras` Varchar(50),
	`id_acompanante` int(15) NOT NULL,
 Primary Key (`id_registro_acomp`,`no_acompanante`,`id_acompanante`));

Create table `ADMINISTRADOR` (
	`id_administrador` varchar(15) NOT NULL,
	`id_departamento` int(15) NOT NULL,
	`nombre` Varchar(30) NOT NULL,
	`contrasena` Varchar(30) NOT NULL,
	`direccion` Varchar(60) NOT NULL,
	`telefono` Varchar(15) NOT NULL,
	`email` Varchar(20) NOT NULL,
	`fecha_nacimiento` Date,
	`posicion` Varchar(20),
	`tipo_administrador` Varchar(30) NOT NULL,
 Primary Key (`id_administrador`,`id_departamento`));

Create table `TUTOR` (
	`id_tutor` int(15) NOT NULL AUTO_INCREMENT,
	`id_alumno` varchar(15) NOT NULL,
	`nombre` Varchar(30) NOT NULL,
	`direccion` Varchar(60) NOT NULL,
	`telefono` Varchar(15) NOT NULL,
	`email` Varchar(20),
	`fecha_nacimiento` Date,
	`parentezco` Varchar(10) NOT NULL,
	`id_escuela` int(15) NOT NULL,
 Primary Key (`id_tutor`,`id_alumno`,`id_escuela`));

Create table `PONENTE` (
	`id_ponente` int(15) NOT NULL AUTO_INCREMENT,
	`nombre` Varchar(30) NOT NULL,
	`direccion` Varchar(60) NOT NULL,
	`telefono` Varchar(15) NOT NULL,
	`email` Varchar(20),
 Primary Key (`id_ponente`));

Create table `LISTA_PONENTES` (
	`id_ponente` int(15) NOT NULL,
	`id_evento` int(15) NOT NULL,
	`id_lista_ponentes` int(15) NOT NULL AUTO_INCREMENT,
 Primary Key (`id_lista_ponentes`));

Create table `ACOMPANANTE` (
	`id_acompanante` int(15) NOT NULL AUTO_INCREMENT,
	`nombre` Varchar(30) NOT NULL,
	`direccion` Varchar(60) NOT NULL,
	`telefono` Varchar(15) NOT NULL,
	`email` Varchar(20) NOT NULL,
	`fecha_nacimiento` Date,
 Primary Key (`id_acompanante`));


Alter table TUTOR add Foreign Key (`id_alumno`,`id_escuela`) references `ALUMNO` (`id_alumno`,`id_escuela`) on delete  restrict on update  restrict;
Alter table REGISTRO_ALUMNO add Foreign Key (`id_alumno`,`id_escuela`) references `ALUMNO` (`id_alumno`,`id_escuela`) on delete  restrict on update  restrict;
Alter table REGISTRO_ACOMPANANTE add Foreign Key (`id_registro`,`id_evento`) references `REGISTRO_ALUMNO` (`id_registro`,`id_evento`) on delete  restrict on update  restrict;
Alter table ALUMNO add Foreign Key (`id_escuela`) references `ESCUELA` (`id_escuela`) on delete  restrict on update  restrict;
Alter table LISTA_PONENTES add Foreign Key (`id_evento`) references `EVENTO` (`id_evento`) on delete  restrict on update  restrict;
Alter table ADMINISTRADOR add Foreign Key (`id_departamento`) references `DEPARTAMENTO` (`id_departamento`) on delete  restrict on update  restrict;
Alter table EVENTO add Foreign Key (`id_lugar`) references `LUGAR` (`id_lugar`) on delete  restrict on update  restrict;
Alter table EVENTO add Foreign Key (`id_administrador`,`id_departamento`) references `ADMINISTRADOR` (`id_administrador`,`id_departamento`) on delete  restrict on update  restrict;
Alter table LISTA_PONENTES add Foreign Key (`id_ponente`) references `PONENTE` (`id_ponente`) on delete  restrict on update  restrict;
Alter table REGISTRO_ACOMPANANTE add Foreign Key (`id_acompanante`) references `ACOMPANANTE` (`id_acompanante`) on delete  restrict on update  restrict;
