-- Insert data into `course` table
INSERT INTO course (id, name, description, credit) VALUES
   (1, 'Introduction to Programming', 'Learn the basics of programming using Java.', 3),
   (2, 'Database Systems', 'Study relational databases and SQL.', 4),
   (3, 'Web Development', 'Introduction to web technologies including HTML, CSS, and JavaScript.', 3);

-- Insert data into `rating` table
INSERT INTO rating (id, number, course_id) VALUES
   (1, 4, 1),
   (2, 5, 1),
   (3, 3, 2),
   (4, 2, 3);

-- Insert data into `assessment` table
INSERT INTO assessment (id, content, course_id) VALUES
    (1, 'Midterm Exam on Java basics', 1),
    (2, 'Final Project: Build a Java application', 1),
    (3, 'Quiz on SQL and database design', 2),
    (4, 'Web application development project', 3);

-- Insert data into `author` table
INSERT INTO author (id, name, email, birthdate) VALUES
    (1, 'John Doe', 'johndoe@example.com', '1980-05-15'),
    (2, 'Jane Smith', 'janesmith@example.com', '1975-10-22');

-- Insert data into `course_author` table
INSERT INTO course_author (course_id, author_id) VALUES
     (1, 1),
     (2, 2),
     (3, 1);
