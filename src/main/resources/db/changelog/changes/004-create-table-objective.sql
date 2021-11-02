CREATE TABLE okr.objective
(
    objective_id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    annotations VARCHAR(1000) NULL,
    created_date DATETIME NOT NULL,
    cycle_id INT NOT NULL,
    objective_father_id INT,
    team_id INT,
    PRIMARY KEY (objective_id),

    INDEX objective_cycle_fk_idx (cycle_id ASC),
    INDEX objective_father_fk_idx (objective_father_id ASC),
    CONSTRAINT objective_cycle_fk
        FOREIGN KEY (cycle_id)
            REFERENCES okr.cycle (cycle_id),
    CONSTRAINT objective_father_fk
        FOREIGN KEY (objective_father_id)
            REFERENCES okr.objective (objective_id),
    CONSTRAINT objective_team_fk
        FOREIGN KEY (team_id)
            REFERENCES okr.team (team_id)


)
