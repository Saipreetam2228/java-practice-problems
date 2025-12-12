-- PostgreSQL sample queries

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    age INT
);

INSERT INTO students (name, age) VALUES
('Alice', 20),
('Bob', 22),
('Charlie', 19);

SELECT * FROM students;
