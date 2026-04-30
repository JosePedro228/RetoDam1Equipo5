
SELECT * FROM empleados;
DROP FUNCTION IF EXISTS fecha;

DELIMITER //
CREATE FUNCTION fecha(id int)
RETURNS DATE
NO SQL
BEGIN
DECLARE fecha DATE;
SET fecha = (SELECT fecnac FROM empleados WHERE numem = id);
return fecha;
END //

SELECT fecha(110);

DELIMITER ;

DROP PROCEDURE IF EXISTS salario;

DELIMITER //
CREATE PROCEDURE salario(IN numempleado INT)
BEGIN
DECLARE salario INT;
DECLARE clasificacion VARCHAR(5);
DECLARE nombre VARCHAR(20);
SET salario = (SELECT salar FROM empleados WHERE numem = numempleado);
SET nombre = (SELECT nomem FROM empleados WHERE numem = numempleado);
	CASE 
		WHEN salario < 1200 THEN
			SET clasificacion = 'BAJO';
		WHEN salario between 1200 AND 2000 THEN
			SET clasificacion = 'MEDIO';
		WHEN salario > 2000 THEN
			SET clasificacion = 'ALTO';
    
    END CASE;
    
	SELECT nombre;
    SELECT salario;
    SELECT clasificacion;

END //
DELIMITER ;
CALL salario(110);


DROP PROCEDURE IF EXISTS azar;
DELIMITER //
CREATE PROCEDURE azar(OUT nregistros INT)
BEGIN

SET nregistros = RAND() * (SELECT COUNT(*) FROM empleados) + 1; -- +1 para que de enteros

END//
DELIMITER ;
CALL azar(@numero);
SELECT @numero;

DROP PROCEDURE IF EXISTS diames;
DELIMITER //
CREATE PROCEDURE diames(IN mes INT, anio INT)
BEGIN
DECLARE correcto boolean DEFAULT false;
	CREATE TEMPORARY TABLE tablaTemp;
WHILE correct = false
	DO IF mes between 1 AND 12 THEN
		IF anio <= 2026 THEN
			SET correcto = true;

END IF;
END IF;

END//

