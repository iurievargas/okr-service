CREATE TABLE okr.initiative
(
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description VARCHAR(800) NULL,
    status VARCHAR(20) NOT NULL COMMENT 'TO_DO\nDOING\nDONE',
    key_result_id INT NOT NULL,

    CONSTRAINT initiative_key_result_fk
        FOREIGN KEY (key_result_id)
            REFERENCES okr.key_result (id),

    PRIMARY KEY (id)
)
