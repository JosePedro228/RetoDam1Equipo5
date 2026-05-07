INSERT INTO roles (id_rol, nombre_rol) VALUES 
(1, 'Administrador'),
(2, 'Profesor');

INSERT INTO ubicacion (tipo) VALUES 
('Armario'),
('Caja'),
('Estanteria');

INSERT INTO categoria (nombre_categoria) VALUES 
('Componentes de red'),
('Hardware'),
('Material fungible'),
('Herramientas'),
('Otros'),
('Equipos completos');
INSERT INTO usuarios (nombre, contraseña, id_rol) VALUES 
('admin_eduardo', 'nozalbdd123', 1),
('profe_roberto', 'roberto2024', 2),
('profe_david', 'david88', 2);

INSERT INTO elementos (id_categoria, nombre, descripcion, estado, id_ubicacion) VALUES 
(6, 'Laptop Dell', 'Dell Latitude 5420, 16GB RAM', 'disponible', 2),
(6, 'MacBook Air', 'Chip M2, 8GB RAM', 'prestado', 1),
(2, 'Epson X41', 'Proyector 3600 lúmenes', 'disponible', 3),
(2, 'Mouse Logitech', 'Mouse inalámbrico ergonómico', 'en reparacion', 1),
(5, 'Kit Destornilladores', 'Juego de 24 piezas de precisión', 'disponible', 1);

INSERT INTO prestamos (id_usuario, fecha, id_elemento, devuelto) VALUES 
(2, '2024-05-20 09:00:00', 2, FALSE), -- Profe Eduardo pidió la MacBook
(3, '2024-05-21 10:30:00', 3, TRUE),  -- Profe David pidió el proyector y ya lo devolvió
(2, '2024-05-22 08:15:00', 1, FALSE); -- Profe Roberto pidió la Laptop Dell


SELECT * FROM roles;
SELECT * FROM ubicacion;
SELECT * FROM categoria;
SELECT * FROM usuarios;
SELECT * FROM elementos;
SELECT * FROM prestamos;