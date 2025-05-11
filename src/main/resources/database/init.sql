--En caso de que no se cree la base de datos cuando se inicializa el servidor habria que crear fibonacci_db en mysql.

--para revisar las tablas y los valores.
--select * from fibonacci_results;
--select * from fibonacci_query_stadistics;


-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS fibonacci_db;

-- Usar la base de datos
USE fibonacci_db;

-- Crear la tabla FibonacciResultsEntity
CREATE TABLE IF NOT EXISTS fibonacci_results (id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              n INT NOT NULL,
                                              value VARCHAR(255) NOT NULL,
                                              created_at DATETIME NOT NULL,
                                              updated_at DATETIME NOT NULL);

-- Crear la tabla FibonacciQueryStadistics
CREATE TABLE IF NOT EXISTS fibonacci_query_stadistics (id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                       n INT NOT NULL,
                                                       query_count INT NOT NULL,
                                                       created_at_time DATETIME NOT NULL,
                                                       updated_at_time DATETIME NOT NULL);