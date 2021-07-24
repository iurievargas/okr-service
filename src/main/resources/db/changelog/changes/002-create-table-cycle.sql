CREATE TABLE okr.cycle
(
    cycle_id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,

    PRIMARY KEY (cycle_id)
)


