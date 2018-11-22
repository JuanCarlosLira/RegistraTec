INSERT INTO escuela (nombre, direccion, telefono) VALUES ('ITESM Campus Toluca', 'Eduardo Monroy C�rdenas 2000, San Antonio Buenavista, 50110', '01 722 279 9990');

INSERT INTO departamento (nombre) VALUES('Ingenieria');

INSERT INTO departamento (nombre) VALUES('Arquitectura');

INSERT INTO departamento (nombre) VALUES('Derecho');

INSERT INTO alumno VALUES('A01362655', 'Esteban', 'qwe', 'Super Cool Street 332', '722 659 5830', 'este_777@hotmail.com', STR_TO_DATE('30/05/1997', '%d/%m/%Y'), 'ISC', 5, 1);

INSERT INTO alumno VALUES('A01362643','Jimena', 'qwe', 'Super Cool Street 332', '722 345 5464', 'mail@hotmail.com', STR_TO_DATE('21/07/1998', '%d/%m/%Y'), 'ISC', 3, 1);

INSERT INTO administrador VALUES ('L0123542', 1,
                                  'Juan',
                                  'qwe',
                                  'Some Street 213, Toluca',
                                  '722 4859 263',
                                  'mail@mail.com',
                                  STR_TO_DATE('02/02/1985', '%d/%m/%Y'),
                                  'Director',
                                  'True');

INSERT INTO administrador VALUES ('L0123552', 2, 'Mar�a', 'qwe', 'Other Street 213, Toluca', '722 4729 249', 'mail2@mail.com', STR_TO_DATE('25/08/1985', '%d/%m/%Y'), 'Directora', 'True');

INSERT INTO administrador VALUES ('L0126878', 3, 'Joaqu�n', 'qwe', 'Better Street 1389, Toluca', '722 4542 123', 'mail3@mail.com', STR_TO_DATE('17/03/1965', '%d/%m/%Y'), 'Profesor', 'False');

INSERT INTO evento (id_administrador,
                    id_departamento,
                    nombre,
                    fecha_inicio_evento,
                    status_evento,
                    capacidad_maxima,
                    capacidad_actual,
                    no_acomp)
VALUES ('L0123542',
        1,
        'Evento1',
        STR_TO_DATE('01/12/2017', '%d/%m/%Y'),
        'ABIERTO',
        15,
        12,
        2);

INSERT INTO evento (id_administrador, id_departamento, nombre, fecha_inicio_evento, status_evento, capacidad_maxima, capacidad_actual, no_acomp) VALUES ('L0123542', 1, 'Concurso', STR_TO_DATE('05/12/2017', '%d/%m/%Y'), 'CANCELADO', 20, 5, 0);

INSERT INTO evento (id_administrador, id_departamento, nombre, fecha_inicio_evento, status_evento, capacidad_maxima, capacidad_actual, no_acomp) VALUES ('L0123552', 2, 'Muertec', STR_TO_DATE('28/11/2017', '%d/%m/%Y'), 'CONCLUIDO', 35, 35, 3);

INSERT INTO evento (id_administrador, id_departamento, nombre, fecha_inicio_evento, status_evento, capacidad_maxima, capacidad_actual, no_acomp) VALUES ('L0123552', 2, 'Evento2', STR_TO_DATE('25/12/2017', '%d/%m/%Y'), 'LLENO', 17,17,1);

INSERT INTO evento (id_administrador, id_departamento, nombre, fecha_inicio_evento, status_evento, capacidad_maxima, capacidad_actual, no_acomp) VALUES ('L0126878', 3, 'Rifa A�o Nuevo', STR_TO_DATE('30/12/2017', '%d/%m/%Y'), 'ABIERTO', 90,26,1);
