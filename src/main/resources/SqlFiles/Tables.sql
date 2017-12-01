DROP TABLE Receivers;
DROP TABLE Senders;
DROP TABLE ServiceRequests;
DROP TABLE Employees;
DROP TABLE Edges;
DROP TABLE Nodes;


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
    CONSTRAINT Nodes_building CHECK (building IN
          ('BTM', 'Shapiro', 'Tower', '45 Francis', '15 Francis')),
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
    serviceID Integer,
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
    CONSTRAINT ServiceRequests_PK PRIMARY KEY (serviceID),
    CONSTRAINT ServiceRequests_FK1 FOREIGN KEY (sender)
          REFERENCES Employees (username) ON DELETE CASCADE
);