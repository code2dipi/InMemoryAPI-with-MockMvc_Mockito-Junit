-- Create the database (if not exists)
CREATE DATABASE IF NOT EXISTS presentation_db;

-- Switch to the newly created database (optional in H2 for persistent databases)
USE presentation_db;

-- Create the Presentation table
CREATE TABLE IF NOT EXISTS Presentation (
    id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

-- Create the Slide table
CREATE TABLE IF NOT EXISTS Slide (
    id VARCHAR(255) PRIMARY KEY,
    content TEXT NOT NULL,
    presentation_id VARCHAR(255),
    FOREIGN KEY (presentation_id) REFERENCES Presentation(id)
);

-- Insert sample data into Presentation
INSERT INTO Presentation (id, title)
VALUES ('presentation1', 'Java Programming Basics'),
       ('presentation2', 'Advanced Java Topics');

-- Insert slides for Presentation 1
INSERT INTO Slide (id, content, presentation_id)
VALUES ('slide1', 'Introduction to Java', 'presentation1'),
       ('slide2', 'OOP Concepts', 'presentation1'),
       ('slide3', 'Java Syntax', 'presentation1');

-- Insert slides for Presentation 2
INSERT INTO Slide (id, content, presentation_id)
VALUES ('slide1', 'Java Streams API', 'presentation2'),
       ('slide2', 'Concurrency in Java', 'presentation2');

-- Example Query: Get all presentations
SELECT * FROM Presentation;

-- Example Query: Get all slides for a specific presentation
SELECT * FROM Slide WHERE presentation_id = 'presentation1';
