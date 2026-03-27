-- Insertar perfiles
INSERT INTO perfil (nombre) VALUES ('admin');
INSERT INTO perfil (nombre) VALUES ('secretaria');
INSERT INTO perfil (nombre) VALUES ('vendedor');

-- Insertar usuarios (contraseña: 12345 encriptada con BCrypt)
-- Si necesitas regenerar los hashes, ejecuta GenerarClave.java
INSERT INTO usuario (username, password, actividad, id_perfil)
VALUES ('admin',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
        '1',
        (SELECT id_perfil FROM perfil WHERE nombre = 'admin'));

INSERT INTO usuario (username, password, actividad, id_perfil)
VALUES ('secretaria',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
        '1',
        (SELECT id_perfil FROM perfil WHERE nombre = 'secretaria'));

INSERT INTO usuario (username, password, actividad, id_perfil)
VALUES ('vendedor',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
        '1',
        (SELECT id_perfil FROM perfil WHERE nombre = 'vendedor'));
