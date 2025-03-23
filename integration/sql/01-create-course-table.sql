IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'CURSO')
BEGIN
    CREATE TABLE CURSO (
        id NVARCHAR(36) PRIMARY KEY,
        descricao NVARCHAR(255),
        nome NVARCHAR(255) NOT NULL,
        capacidade INT
    );
END