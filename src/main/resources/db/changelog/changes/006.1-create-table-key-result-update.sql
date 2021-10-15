CREATE TABLE okr.key_result_update_history (
    key_result_update_history_id INT NOT NULL AUTO_INCREMENT,
    new_value DECIMAL(10,2) NOT NULL,
    updated_date DATETIME NOT NULL,
    key_result_id INT NOT NULL,
	PRIMARY KEY (key_result_update_history_id),

    CONSTRAINT key_result_update_history_key_result_fk
            FOREIGN KEY (key_result_id)
            REFERENCES okr.key_result (key_result_id)


)
