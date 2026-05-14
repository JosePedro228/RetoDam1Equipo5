DELIMITER $$

CREATE TRIGGER trg_elemento_devuelto
AFTER UPDATE ON elementos
FOR EACH ROW
BEGIN
    IF OLD.estado = 'prestado' AND NEW.estado = 'disponible' THEN
        UPDATE prestamos
        SET devuelto = 1
        WHERE id_elemento = NEW.id_elemento
          AND devuelto = 0;
    END IF;
END$$

DELIMITER ;