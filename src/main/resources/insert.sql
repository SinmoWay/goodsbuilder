--Должно соответствовать DictionaryType
INSERT INTO europe.dictionary SELECT * FROM (SELECT 1, 'CONTENT_NAME' UNION SELECT 2, 'FABRICATOR_NAME') dictionary_types WHERE NOT EXISTS(SELECT * FROM europe.dictionary);

INSERT INTO europe.dictionary_value VALUES (1, 1, 'TEST1'), (2, 1, 'TEST2'), (3, 2, 'TEST3');