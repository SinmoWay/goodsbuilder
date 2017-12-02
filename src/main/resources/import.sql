CREATE SCHEMA IF NOT EXISTS europe AUTHORIZATION sa;

CREATE MEMORY TABLE IF NOT EXISTS europe.dictionary (id INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR NOT NULL, CONSTRAINT unique_dic_name UNIQUE (name));
CREATE MEMORY TABLE IF NOT EXISTS europe.dictionary_value (id INTEGER PRIMARY KEY AUTO_INCREMENT, id_dictionary INTEGER NOT NULL, value VARCHAR, CONSTRAINT related_dictionary_fk FOREIGN KEY (id_dictionary) REFERENCES europe.dictionary (id) ON UPDATE NO ACTION ON DELETE NO ACTION);

CREATE MEMORY TABLE IF NOT EXISTS europe.content (id INTEGER PRIMARY KEY AUTO_INCREMENT, id_name INTEGER NOT NULL, amount INTEGER NOT NULL, CONSTRAINT content_name_from_dictionary_fk FOREIGN KEY (id_name) REFERENCES europe.dictionary_value (id) ON UPDATE NO ACTION ON DELETE NO ACTION);
CREATE MEMORY TABLE IF NOT EXISTS europe.fabricator (id INTEGER PRIMARY KEY AUTO_INCREMENT, id_name INTEGER NOT NULL, CONSTRAINT fabricator_name_from_dictionary_fk FOREIGN KEY (id_name) REFERENCES europe.dictionary_value (id) ON UPDATE NO ACTION ON DELETE NO ACTION);
CREATE MEMORY TABLE IF NOT EXISTS europe.product (id INTEGER PRIMARY KEY AUTO_INCREMENT, image_name VARCHAR, description TEXT, price DOUBLE, weight DOUBLE);

CREATE MEMORY TABLE IF NOT EXISTS europe.fabricator_content (id_fabricator INTEGER, id_content INTEGER NOT NULL, CONSTRAINT fabricator_content_pk PRIMARY KEY (id_fabricator, id_content), CONSTRAINT fabricator_fk FOREIGN KEY (id_fabricator) REFERENCES europe.fabricator (id) ON UPDATE NO ACTION ON DELETE NO ACTION, CONSTRAINT content_fk FOREIGN KEY (id_content) REFERENCES europe.content (id) ON UPDATE NO ACTION ON DELETE NO ACTION);
CREATE MEMORY TABLE IF NOT EXISTS europe.product_fabricator (id_product INTEGER NOT NULL, id_fabricator INTEGER, CONSTRAINT product_fabricator_pk PRIMARY KEY (id_product, id_fabricator), CONSTRAINT product_fk FOREIGN KEY (id_product) REFERENCES europe.product (id) ON UPDATE NO ACTION ON DELETE NO ACTION, CONSTRAINT fabricator_fk FOREIGN KEY (id_fabricator) REFERENCES europe.fabricator (id) ON UPDATE NO ACTION ON DELETE NO ACTION);

--Должно соответствовать DictionaryType
INSERT INTO europe.dictionary SELECT * FROM (SELECT 1, 'CONTENT_NAME' UNION SELECT 2, 'FABRICATOR_NAME') dictionary_types WHERE NOT EXISTS(SELECT * FROM europe.dictionary);

INSERT INTO europe.dictionary_value VALUES (1, 1, 'TEST1'), (2, 1, 'TEST2'), (3, 2, 'TEST3');