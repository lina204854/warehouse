-- 1. Создание базы данных
CREATE DATABASE IF NOT EXISTS warehouse_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- 2. Переключение на созданную базу данных
USE warehouse_db;

-- 3. Создание таблицы для техники
CREATE TABLE IF NOT EXISTS techniques (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    technique_type VARCHAR(255) NOT NULL,
    technique_group VARCHAR(255),
    import_date DATE NOT NULL,
    export_date DATE,
    driver_name VARCHAR(255)
);

-- 4. Добавление тестовых данных
INSERT INTO techniques (technique_type, technique_group, import_date, export_date, driver_name) VALUES
('Мультиварка Redmond RMC-M90', 'Бытовые приборы', '2023-10-05', '2023-10-10', 'Иванов П.С.'),
('Телевизор Samsung QE55Q80A', 'Электроника', '2023-10-05', '2023-10-12', 'Петров В.А.'),
('Холодильник LG GA-B509SLKM', 'Крупная бытовая техника', '2023-11-01', NULL, NULL),
('Ноутбук Apple MacBook Air 13', 'Компьютеры', '2023-11-15', '2023-11-20', 'Сидорова А.К.'),
('Стиральная машина Bosch WHA122W1OE', 'Крупная бытовая техника', '2023-11-15', NULL, NULL);
