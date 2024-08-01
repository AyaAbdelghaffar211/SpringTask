DROP TABLE IF EXISTS rating;
DROP TABLE IF EXISTS assessment;
DROP TABLE IF EXISTS course_author;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS course;

CREATE TABLE course (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    credit INT NOT NULL
    );

CREATE TABLE rating (
    id INT PRIMARY KEY,
    number INT NOT NULL CHECK (number BETWEEN 0 AND 5),
    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
    );

CREATE TABLE assessment (
    id INT PRIMARY KEY,
    content VARCHAR(500) NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
    );

CREATE TABLE author (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    birthdate DATE
    );

CREATE TABLE course_author(
    course_id INT NOT NULL,
    author_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE
);
