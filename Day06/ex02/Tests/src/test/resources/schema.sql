create table if not exists products (
                                       identifier  INT IDENTITY PRIMARY KEY,
                                       name        VARCHAR(50) NOT NULL ,
                                       price       INT NOT NULL
);