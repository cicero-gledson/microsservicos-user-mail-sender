CREATE TABLE tb_email (
                          email_id UUID NOT NULL,
                          user_id UUID,
                          email_from VARCHAR(255),
                          email_to VARCHAR(255),
                          email_subject VARCHAR(255),
                          email_body TEXT,
                          email_status VARCHAR(50),
                          send_date_time_email TIMESTAMP,

                          CONSTRAINT pk_tb_email PRIMARY KEY (email_id)
);