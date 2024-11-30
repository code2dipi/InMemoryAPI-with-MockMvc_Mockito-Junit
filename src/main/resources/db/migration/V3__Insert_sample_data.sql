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
