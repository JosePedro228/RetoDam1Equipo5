DELIMITER $$

CREATE TRIGGER trg_elemento_prestado
AFTER INSERT ON prestamos
FOR EACH ROW
BEGIN
    UPDATE elementos
    SET estado = 'prestado'
    WHERE id_elemento = NEW.id_elemento;
END$$

DELIMITER ;