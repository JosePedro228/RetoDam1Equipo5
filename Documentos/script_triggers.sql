/* Este trigger lo que hace es asignar el campo nombre automaticamente a cada insert que se le hace a la tabla ubicacion.
   Desde java se harán los insert de armarios, baldas y cajas, entonces si es un amario se le asignara un nombre autoincremental
   ,si es una balda esta podrá estar asignada a un armario o no. Por último las cajas pueden estar en baldas o en baldas de armarios.
*/
DELIMITER //
CREATE TRIGGER tg_asignar_nombre
BEFORE INSERT ON ubicacion
FOR EACH ROW
BEGIN
	DECLARE nombre_armario VARCHAR(20);
    DECLARE contador INT;
    DECLARE id_padre INT;
    DECLARE nombre_balda VARCHAR(20);
    
		IF NEW.tipo = 'Armario_grande' OR  NEW.tipo = 'Armario_pequenio' THEN -- Es un Armario_grande o un Armario_pequeño
			SELECT COUNT(*) + 1 INTO contador FROM ubicacion WHERE tipo = 'Armario_grande' OR  tipo = 'Armario_pequenio';
			SET NEW.nombre = CONCAT('A',LPAD(contador,2,'0'));
        
		ELSEIF NEW.tipo = 'Balda' THEN
		IF NEW.donde_esta IS NOT NULL THEN -- Esta en un armario
				SELECT nombre INTO nombre_armario FROM ubicacion WHERE id_ubicacion = NEW.donde_esta; -- Nombre del armario al que pertenecen las baldas
				SELECT COUNT(*) +1 INTO contador FROM ubicacion WHERE tipo = 'Balda' AND NEW.donde_esta = donde_esta; -- Cuenta el número de baldas que hay en el armario especifico
				SET NEW.nombre = CONCAT(nombre_armario,'_',LPAD(contador,2,'0'));
		
        -- No esta en un armario
        ELSE
			SELECT COUNT(*) +1 INTO contador FROM ubicacion WHERE tipo = 'Balda' AND donde_esta = NULL;
			SET NEW.nombre = CONCAT('B',LPAD(contador,2,'0'));
		END IF;
        
		ELSEIF NEW.tipo = 'Caja' THEN
			SELECT id_ubicacion INTO id_padre FROM ubicacion WHERE id_ubicacion = NEW.donde_esta;
				IF id_padre LIKE '%Armario%'  THEN -- La caja está en un armario
					SELECT COUNT(*) +1 INTO contador FROM ubicacion WHERE tipo = 'Caja' AND donde_esta = NEW.donde_esta;
					SELECT nombre INTO nombre_armario FROM ubicacion WHERE id_ubicacion = NEW.donde_esta;
					SET NEW.nombre = CONCAT(nombre_armario, '_', 'B',LPAD(contador,2,'0'));
					
		ELSE -- La caja esta en una balda
			IF id_padre LIKE '%Balda%' THEN
				SELECT COUNT(*) + 1 INTO contador FROM ubicacion WHERE tipo = 'Balda' AND donde_esta = NEW.donde_esta;
				SELECT nombre INTO nombre_balda FROM ubicacion WHERE id_ubicacin = NEW.donde_esta;
				SET NEW.nombre = CONCAT(nombre_balda,'_','B',LPAD(contador,2,'0'));
	END IF;
	END IF;
    END IF;
    
END //