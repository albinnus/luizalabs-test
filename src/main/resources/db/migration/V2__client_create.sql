CREATE TABLE client (
                             id BIGINT auto_increment NOT NULL,
                             name VARCHAR(256) NOT NULL,
                             email varchar(100) NOT NULL,
                             CONSTRAINT client_PK PRIMARY KEY (id),
                             CONSTRAINT email_UK UNIQUE KEY (email)
)
ENGINE=InnoDB