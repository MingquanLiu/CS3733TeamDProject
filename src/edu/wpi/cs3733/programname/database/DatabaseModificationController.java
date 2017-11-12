package edu.wpi.cs3733.programname.java.database;

public class DatabaseModificationController {

    public DatabaseModificationController(){}

/*
    // need to change
    String USERID = "";
    // need to change
    String PASSWORD = "";

    System.out.println("-------JDBC COnnection Testing ---------");
        try {
        // need to change
        Class.forName("oracle.jdbc.driver.OracleDriver");

    } catch (ClassNotFoundException e) {
        // need to change
        System.out.println("Where is your Oracle JDBC Driver?");
        e.printStackTrace();
        return;
    }
        System.out.println("JDBC Driver Registered!");
    Connection connection = null;

        try {
        // need to change
        connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.wpi.edu:1521:orcl", USERID, PASSWORD);

    } catch (SQLException e) {
        System.out.println("Connection Failed! Check output console");
        e.printStackTrace();
        return;
    }
        System.out.println("JDBC Driver Connected!");
*/

    // All of the methods' return types are String, just for now
    public String addNode(NodeData data){
        String id = data.getId();
        int x = data.getX();
        int y = data.getY();
        String type = data.getType();
        String longName = data.getLongName();
        String shortName = data.getShortName();
        try {
            Statement stmt = connection.createStatement();
            // expected "insert into Node values (id, x, y, type, longName, shortName)"
            String str = "insert into Node values(" + id + ", " + x + ", " + y + ", " + type + ", "+ longName + ", "+ shortName + ")";
            stmt.executeUpdate(str);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
            return "Insert Node Failed!";
        }
        return stmt;
    }

    public String editNode(NodeData data){
        String id = data.getId();
        int x = data.getX();
        int y = data.getY();
        String type = data.getType();
        String longName = data.getLongName();
        String shortName = data.getShortName();

        try {
            Statement stmt = connection.createStatement();
            // expected "update Node set xcoord = x, ycoord = y, nodeType = type, longName = longName, shortName = shortName where nodeID = id
            String str ="update Node set xcoord = " + x + ", ycoord = " + y +
                    ", nodeType = " + type + ", longName = " + longName + ", shortName = "
                    + shortName + "where nodeID = " + id;
            stmt.executeUpdate(str);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Edit Node Failed!");
            e.printStackTrace();
            return "Edit Node Failed!";
        }
        return stmt;
    }

    public String deleteNode(NodeData node){
        String id = data.getId();

        try {
            Statement stmt = connection.createStatement();
            // expected "update Node set xcoord = x, ycoord = y, nodeType = type, longName = longName, shortName = shortName where nodeID = id
            String str ="delete from Node where nodeID = " + id;
            stmt.executeUpdate(str);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Delete Node Failed!");
            e.printStackTrace();
            return "Delete Node Failed!";
        }
        return stmt;

    }

    public String addEdge(String node1Id, String node2Id){

    }

    public String deleteEdge(Edge edge){

    }

}
