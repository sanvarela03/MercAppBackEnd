INSERT INTO rol (id_rol, nombre) VALUES (1, 'Eliga un rol');
INSERT INTO rol (id_rol, nombre) VALUES (2, 'comprador');
INSERT INTO rol (id_rol, nombre) VALUES (3, 'productor');
INSERT INTO rol (id_rol, nombre) VALUES (4, 'transportador');

INSERT INTO usuarios (id_usuario, nombre, apellido, email,fecha_nacimiento ,create_at, id_rol, direccion,password,foto) VALUES (1,'Santiago','Varela Daza','svarela03@gmail.com', '1998-03-22','2020-03-22',2,'Allá arriba en aquel alto',1234,'');

INSERT INTO comprador (id_usuario) VALUES (1);


INSERT INTO usuarios (id_usuario, nombre, apellido, email,fecha_nacimiento ,create_at, id_rol, direccion,password,foto) VALUES (2,'Andres','Celeita Mora','aceleita04@gmail.com', '1998-03-22','2020-03-22',3,'Allá arriba en aquel alto',1234,'');

INSERT INTO productor (id_usuario) VALUES (2);


INSERT INTO tipo (id,nombre) VALUES (1,'verdura');

INSERT INTO producto (id_producto,costo,descripcion,nombre,precio,id_productor,tipo_id) VALUES(1,'$20000','un producto','una verdura','$30000',2,1);

INSERT INTO producto (id_producto,costo,descripcion,nombre,precio,id_productor,tipo_id) VALUES(2,'$20000','un producto','alcachofa','$30000',2,1);






