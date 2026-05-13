/* Este trigger lo que hace es asignar el campo nombre automaticamente a cada insert que se le hace a la tabla ubicacion.
   Desde java se harán los insert de armarios, baldas y cajas, entonces si es un amario se le asignara un nombre autoincremental
   ,si es una balda esta podrá estar asignada a un armario o no. Por último las cajas pueden estar en baldas o en baldas de armarios.
*/
DROP TRIGGER IF EXISTS tg_asignar_nombre;
DELIMITER //
CREATE TRIGGER tg_asignar_nombre
BEFORE INSERT ON ubicacion
FOR EACH ROW
BEGIN
	DECLARE nombre_armario VARCHAR(20);
    DECLARE contador INT;
    DECLARE v_tipo_padre VARCHAR(20);
    DECLARE v_nombre_padre VARCHAR(20);
    
		IF NEW.tipo LIKE '%Armario%' THEN -- Es un Armario_grande o un Armario_pequeño
			SELECT COUNT(*) + 1 INTO contador FROM ubicacion WHERE tipo LIKE '%Armario%';
			SET NEW.nombre = CONCAT('A',LPAD(contador,2,'0'));
        
        -- Baldas
		ELSEIF NEW.tipo = 'Balda' THEN 
        -- Baldas en armarios
		IF NEW.donde_esta IS NOT NULL THEN
				SELECT nombre INTO nombre_armario FROM ubicacion WHERE id_ubicacion = NEW.donde_esta; -- Nombre del armario al que pertenecen las baldas
				SELECT COUNT(*) +1 INTO contador FROM ubicacion WHERE tipo = 'Balda' AND NEW.donde_esta = donde_esta; -- Cuenta el número de baldas que hay en el armario especifico
				SET NEW.nombre = CONCAT(nombre_armario,'_B',LPAD(contador,2,'0'));
		
        -- Baldas que no están en armarios
        ELSE
			SELECT COUNT(*) +1 INTO contador FROM ubicacion WHERE tipo = 'Balda' AND donde_esta IS NULL;
			SET NEW.nombre = CONCAT('B',LPAD(contador,2,'0'));
		END IF;

	-- Cajas
	ELSEIF NEW.tipo = 'Caja' THEN 
        -- tipo y nombre del padre 
        SELECT tipo, nombre INTO v_tipo_padre, v_nombre_padre 
        FROM ubicacion WHERE id_ubicacion = NEW.donde_esta;

        IF v_tipo_padre = 'Balda' THEN
            -- Caja en Balda
            SELECT COUNT(*) + 1 INTO contador FROM ubicacion WHERE tipo = 'Caja' AND donde_esta = NEW.donde_esta;
            SET NEW.nombre = CONCAT(v_nombre_padre, '_C', LPAD(contador, 2, '0'));
        
        ELSE 
            -- lanza error si intentas meter una caja en algo que no sea una balda.
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Error: Una caja solo puede colocarse sobre una Balda.';
        END IF;
	END IF;
END //
DELIMITER ;

SHOW TRIGGERS LIKE 'ubicacion';
    