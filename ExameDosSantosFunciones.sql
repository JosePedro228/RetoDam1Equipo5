delimiter //
create function fecha(id int) 
returns date
no sql
begin 

declare fechaNac date;
select fecnac into fechaNac from empleados where numem = id;
return fechaNac;
end //
delimiter ;

select * from empleados;

select fecha(110);

delimiter //
create procedure deterCategoria(in id int)
begin
declare salario int;
declare nomem varchar(10);
declare categoria varchar(8);

select salar,nomem into salario,nomem from empleados where numem = id;
if salario <1200 then 
set categoria = "bajo";
elseif salario <=2000 then
set categoria= "medio";
else 
set categoria = "alto";
 end if;
 
 select salario,nomem,categoria;
end //
delimiter ;
call deterCategoria(110);

delimiter //
create procedure randResgistro(in cantReg int)
begin
declare numAle int;
declare i int;
set i = 0;
while i<cantReg do
set numAle = round((rand()*33)+1);
select * from empleados where numem = numAle;
set i = i+1;
end while;
end //

delimiter ;

call randResgistro(5);

delimiter //
create procedure cantEmplead(in mes int, in ano int)
begin
declare fechainicio date;
declare cant int;
CREATE TABLE `CantDIa` (
  `Fecha` date DEFAULT NULL,
  `Cant` int DEFAULT NULL,
) 
set fechainicio = conct(ano "-" mes "-01")
where month(fechainicio) < mes do
se


delimiter ;