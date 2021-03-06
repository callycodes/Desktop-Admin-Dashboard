/*
 * Name: Callum Bass
 * Student ID: w1682693
 * Software Development - Group Project
 */
package graph;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import sql.DatabaseManager;

/**
 *
 * @author Callum Bass <w1682693>
 */
public class ExampleGraph {
    
    //At the moment, returns just a PieChart object, you can alter the return type to another type of chart, research JavaFX Charts
    public static PieChart createGraph() throws SQLException {
        
        //Example of how you can use DatabaseManager to open a connection to the database.
        DatabaseManager manager = new DatabaseManager();
        
        /* Connect with specific credentials
        @param url - where is the db stored, if locally use 'localhost'
        @param name - database name
        @param user - database user
        @param pass- database pass
        */
        manager.connect();
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
        
        //Query your data here, i built a query method but you'll have to research on how you iterate your graph data
        ResultSet data = manager.query("SELECT * FROM fruitdata");
        
        while (data.next()) {
            pieChartData.add(new PieChart.Data(data.getString("fruitName"), data.getInt("amount")));
        }
        //Disconnect the manager from the database
        manager.disconnect();
        
        //This creates a pie chart data list
        
        
        //PieChart object created and assigned the data
        final PieChart chart = new PieChart(pieChartData);
        
        //Chart is set a title
        chart.setTitle("Callum's Fruit Graph");
        
        //PieChart object is returned and eventually displayed on grid, leave the display part to me, just make sure you return a chart object.
        return chart;
    }
    
}
