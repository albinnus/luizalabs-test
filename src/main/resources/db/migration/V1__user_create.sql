CREATE TABLE user (
                             id BIGINT auto_increment NOT NULL,
                             email varchar(256) NOT NULL,
                             password varchar(256) NOT NULL,
                             token varchar(256) NULL,
                             enabled int NOT NULL DEFAULT '1',
                             CONSTRAINT user_PK PRIMARY KEY (id),
                             CONSTRAINT user_email_UK UNIQUE KEY (email)
)
ENGINE=InnoDB;
CREATE INDEX user_email_IDX USING BTREE ON user (email);
INSERT INTO user (email, password, token, enabled) VALUES('teste@teste.com', '$2a$10$duNGHu.eCZWPJQ0R8miV0.SS/fztHE.co9W/uKcPRZLAoLxepMzAG', '', 1);
