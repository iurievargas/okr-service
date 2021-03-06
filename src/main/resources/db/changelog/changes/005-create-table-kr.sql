CREATE TABLE okr.key_result (
    key_result_id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(200) NULL,
    annotations VARCHAR(1000) NULL,
    labels VARCHAR(200) NULL,
    baseline DECIMAL(10,2) NULL,
    target DECIMAL(10,2) NOT NULL,
    value DECIMAL(10,2) NULL,
    objective_id INT NOT NULL,
    team_id INT NOT NULL,
    key_result_type_id INT NOT NULL,
    PRIMARY KEY (key_result_id),
    INDEX key_result_objective_fk_idx (objective_id ASC),
    INDEX key_result_team_fk_idx (team_id ASC),
    CONSTRAINT key_result_objective_fk
    FOREIGN KEY (objective_id)
    REFERENCES okr.objective (objective_id),
    CONSTRAINT key_result_team_fk
    FOREIGN KEY (team_id)
    REFERENCES okr.team (team_id),
    CONSTRAINT key_result_type_fk
        FOREIGN KEY (key_result_type_id)
        REFERENCES okr.key_result_type (key_result_type_id)
)
