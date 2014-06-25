--Version:1
CREATE TABLE series ( 
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    remote_id INTEGER, 
    country VARCHAR, 
    format VARCHAR, 
    issue_count INTEGER, 
    "language" VARCHAR, 
    last_issue_id INTEGER, 
    "name" VARCHAR, 
    notes VARCHAR, 
    publication_dates VARCHAR, 
    publication_notes VARCHAR, 
    "year_began" INTEGER, 
    "year_ended" INTEGER
);
--
CREATE TABLE issues (
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    remote_id INTEGER, 
    barcode VARCHAR, 
    indicia_frequency VARCHAR, 
    isbn VARCHAR, 
    notes VARCHAR, 
    "number" VARCHAR, 
    page_count INTEGER, 
    price FLOAT, 
    publication_date VARCHAR, 
    series_id INTEGER, 
    sort_code INTEGER, 
    title VARCHAR, 
    volume VARCHAR,
    variant VARCHAR
);
--
CREATE TABLE collections (
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    "name" VARCHAR
);
--
CREATE TABLE collection_series (
    collection_id INTEGER, 
    series_id INTEGER, 
    PRIMARY KEY(collection_id,series_id)
);
--
CREATE TABLE collection_issues (
    collection_id INTEGER, 
    issue_id INTEGER, 
    PRIMARY KEY(collection_id, issue_id)
);
--Version:2
ALTER TABLE series add column "publisher" VARCHAR;
--
