CREATE TABLE okr.key_result (
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(200) NULL,
    baseline INT NULL,
    target INT NOT NULL,
    result INT NULL,
    objective_id INT NOT NULL,
    team_id INT NOT NULL,
    PRIMARY KEY (id),
    INDEX key_result_objective_fk_idx (objective_id ASC),
    INDEX key_result_team_fk_idx (team_id ASC),
    CONSTRAINT key_result_objective_fk
    FOREIGN KEY (objective_id)
    REFERENCES okr.objective (id),
    CONSTRAINT key_result_team_fk
    FOREIGN KEY (team_id)
    REFERENCES okr.team (id)
)
