-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE Senders;
DROP TABLE Receivers;
DROP TABLE MaintenanceRequests;
DROP TABLE InterpreterRequests;
DROP TABLE TransportationRequests;
DROP TABLE MaintenanceSkills;
DROP TABLE InterpreterSkills;
DROP TABLE ServiceRequests;
DROP TABLE Employees;
DROP TABLE Edges;
DROP TABLE Nodes;
DROP TABLE MapInfo;


CREATE TABLE Nodes(
    nodeID VARCHAR(20),
    xcoord INTEGER,
    ycoord INTEGER,
    floor VARCHAR(4),
    building VARCHAR(20),
    nodeType VARCHAR(4),
    longName VARCHAR(200),
    shortName VARCHAR(100),
    teamAssigned VARCHAR(6),
    CONSTRAINT Nodes_PK PRIMARY KEY (nodeID),
    CONSTRAINT Nodes_nodeType CHECK (nodeType IN
          ('HALL', 'ELEV', 'REST', 'STAI', 'DEPT', 'LABS', 'INFO', 'CONF', 'EXIT', 'RETL', 'SERV', 'BATH')),
    CONSTRAINT Nodes_floor CHECK (floor IN
          ('L2', 'L1', 'G', '1', '2', '3')),
    CONSTRAINT Nodes_xcoordVal check (xcoord >= 0 AND xcoord <= 5000),
    CONSTRAINT Nodes_ycoordVal check (ycoord >= 0 AND ycoord <= 3400)

);


CREATE TABLE Edges(
    edgeID VARCHAR(30),
    startNode VARCHAR(20),
    endNode VARCHAR(20),
    CONSTRAINT Edges_PK PRIMARY KEY (edgeID),
    CONSTRAINT Edges_FK1 FOREIGN KEY (startNode)
          REFERENCES Nodes (nodeID) ON DELETE CASCADE,
    CONSTRAINT Edges_FK2 FOREIGN KEY (endNode)
          REFERENCES Nodes (nodeID) ON DELETE CASCADE
);


CREATE TABLE Employees(
    username VARCHAR(15),
    password VARCHAR(20),
    firstName VARCHAR(15),
    middleName VARCHAR(10),
    lastName VARCHAR(20),
    sysAdmin Integer,
    serviceType VARCHAR(20),
    email VARCHAR(50),
    CONSTRAINT Employees_PK PRIMARY KEY (username),
    CONSTRAINT serviceType_VAL CHECK (serviceType IN
          ('interpreter', 'maintenance', 'transportation', 'none'))
);


CREATE TABLE ServiceRequests(
    serviceID VARCHAR(30),
    sender VARCHAR(15),
    receiver VARCHAR(15),
    serviceType VARCHAR(25),
    location1 VARCHAR(20),
    location2 VARCHAR(20),
    description VARCHAR(500),
    requestTime VARCHAR(30),
    handleTime VARCHAR(30),
    completionTime VARCHAR(30),
    status VARCHAR(10),
    severity INTEGER,
    CONSTRAINT ServiceRequests_PK PRIMARY KEY (serviceID),
    CONSTRAINT ServiceRequests_FK1 FOREIGN KEY (sender)
          REFERENCES Employees (username) ON DELETE CASCADE,
    CONSTRAINT ServiceRequests_FK2 FOREIGN KEY (location1)
          REFERENCES Nodes (nodeID) ON DELETE CASCADE,
    CONSTRAINT ServiceRequests_severityVal CHECK (severity > 0 AND severity < 6)
);

CREATE TABLE InterpreterSkills(
    username VARCHAR(15),
    language VARCHAR(15),
    CONSTRAINT InterpreterSkills_PK PRIMARY KEY (username,language),
    CONSTRAINT InterpreterSkills_FK1 FOREIGN KEY (username)
          REFERENCES Employees (username) ON DELETE CASCADE,
    CONSTRAINT InterpreterSkills_languageVal CHECK (language IN
    ('Mandarin', 'Cantonese', 'Spanish', 'French', 'German', 'Korean', 'Japanese', 'Russian', 'Hindi', 'Arabic', 'Portuguese', 'Bengali', 'other'))
);

CREATE TABLE MaintenanceSkills(
    username VARCHAR(15),
    skill VARCHAR(20),
    CONSTRAINT MaintenanceSkills_PK PRIMARY KEY (username,skill),
    CONSTRAINT MaintenanceSkills_FK1 FOREIGN KEY (username)
          REFERENCES Employees (username) ON DELETE CASCADE,
    CONSTRAINT MaintenanceSkills_skillVal CHECK (skill IN
    ('clean', 'elevator', 'electricity', 'network','other'))
);

CREATE TABLE TransportationRequests(
    serviceID VARCHAR(30),
    transportType VARCHAR(15),
    destination VARCHAR(20),
    reservationTime VARCHAR(30),
    CONSTRAINT TransportationRequests_PK PRIMARY KEY (serviceID),
    CONSTRAINT TransportationRequests_FK1 FOREIGN KEY (serviceID)
          REFERENCES ServiceRequests (serviceID) ON DELETE CASCADE,
    CONSTRAINT TransportationRequests_FK2 FOREIGN KEY (destination)
          REFERENCES Nodes (nodeID) ON DELETE CASCADE,
    CONSTRAINT TransportationRequests_typeVal CHECK (transportType IN ('wheelchair', 'stretcher', 'walking cane', 'bed', 'other'))
);

CREATE TABLE InterpreterRequests(
    serviceID VARCHAR(30),
    language VARCHAR(15),
    reservationTime VARCHAR(30),
    CONSTRAINT InterpreterRequests_PK PRIMARY KEY (serviceID),
    CONSTRAINT InterpreterRequests_FK1 FOREIGN KEY (serviceID)
          REFERENCES ServiceRequests (serviceID) ON DELETE CASCADE,
    CONSTRAINT InterpreterRequests_languageVal CHECK (language IN
    ('Mandarin', 'Cantonese', 'Spanish', 'French', 'German', 'Korean', 'Japanese', 'Russian', 'Hindi', 'Arabic', 'Portuguese', 'Bengali', 'other'))
);

CREATE TABLE MaintenanceRequests(
    serviceID VARCHAR(30),
    maintenanceType VARCHAR(15),
    CONSTRAINT MaintenanceRequests_PK PRIMARY KEY (serviceID),
    CONSTRAINT MaintenanceRequests_FK1 FOREIGN KEY (serviceID)
          REFERENCES ServiceRequests (serviceID) ON DELETE CASCADE,
    CONSTRAINT MaintenanceRequests_typeVal CHECK (maintenanceType IN
    ('cleaning', 'elevator', 'electricity', 'network','other'))
);

CREATE TABLE MapInfo(
    buildingName VARCHAR(30),
    floorName VARCHAR(30),
    imagePath VARCHAR(100),
    floorNum VARCHAR(5)

)