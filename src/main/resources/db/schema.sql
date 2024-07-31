CREATE TABLE IF NOT EXISTS course (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    credit INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS rating (
    id INT PRIMARY KEY,
    number INT NOT NULL CHECK (number BETWEEN 0 AND 5),
    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id)
    );

CREATE TABLE IF NOT EXISTS assessment (
    id INT PRIMARY KEY,
    content VARCHAR(500) NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id)
    );

CREATE TABLE IF NOT EXISTS author (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    birthdate DATE
    );

CREATE TABLE IF NOT EXISTS course_author(
    course_id INT NOT NULL,
    author_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id),
    FOREIGN KEY (author_id) REFERENCES author(id)
);
