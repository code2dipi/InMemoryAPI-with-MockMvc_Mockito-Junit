-- Create the Presentation table
CREATE TABLE IF NOT EXISTS Presentation (
    id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

-- Create the Slide table with a foreign key reference to the Presentation table
CREATE TABLE IF NOT EXISTS Slide (
    id VARCHAR(255) PRIMARY KEY,
    content TEXT NOT NULL,
    presentation_id VARCHAR(255),
    FOREIGN KEY (presentation_id) REFERENCES Presentation(id)
);
