DROP TABLE IF EXISTS collaterals, loan_applications, credit_history_summaries, person;

CREATE TABLE person
(
    tc                BIGINT PRIMARY KEY,
    name              VARCHAR(50),
    surname           VARCHAR(50),
    age               INT,
    employment_status VARCHAR(20),
    education_level   VARCHAR(20),
    marital_status    VARCHAR(20),
    monthly_income    DOUBLE
);

CREATE TABLE loan_applications
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    loan_type VARCHAR(20),
    loan_term INT,
    person_tc BIGINT,
    CONSTRAINT fk_loan_person FOREIGN KEY (person_tc) REFERENCES person (tc) ON DELETE CASCADE
);

CREATE TABLE collaterals
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    type             VARCHAR(20),
    collateral_value DOUBLE,
    loan_app_id      BIGINT,
    CONSTRAINT fk_collateral_loan FOREIGN KEY (loan_app_id) REFERENCES loan_applications (id) ON DELETE CASCADE
);

CREATE TABLE credit_history_summaries
(
    id                          BIGINT PRIMARY KEY AUTO_INCREMENT,
    total_debt_count            INT,
    total_debt_amount           DOUBLE,
    on_time_debt_count          INT,
    on_time_debt_amount         DOUBLE,
    late_debt_count             INT,
    late_debt_amount            DOUBLE,
    loan_applications_last_year INT,
    credit_card_limit           DOUBLE,
    current_card_debt           DOUBLE,
    credit_card_usage_duration  INT,
    person_tc                   BIGINT,
    CONSTRAINT fk_history_person FOREIGN KEY (person_tc) REFERENCES person (tc) ON DELETE CASCADE
);