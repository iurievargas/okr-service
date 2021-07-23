CREATE TABLE okr.objective
(
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    created_date DATETIME NOT NULL,
    type VARCHAR(45) NULL,
    cycle_id INT NOT NULL,
    PRIMARY KEY (id),

    INDEX objective_cycle_fk_idx (cycle_id ASC),
    CONSTRAINT objective_cycle_fk
        FOREIGN KEY (cycle_id)
            REFERENCES okr.cycle (id)

)
