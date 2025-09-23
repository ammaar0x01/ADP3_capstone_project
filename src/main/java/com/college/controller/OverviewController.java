package com.college.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart;

public class OverviewController {

    @FXML
    private PieChart occupancyPieChart;

    @FXML
    private BarChart<String, Number> revenueBarChart;

    @FXML
    private CategoryAxis revenueXAxis;

    @FXML
    private NumberAxis revenueYAxis;

    @FXML
    private BarChart<String, Number> totalGuestsChart;  // Added for Guests

    @FXML
    private CategoryAxis totalGuestsChartXAxis;

    @FXML
    private NumberAxis totalGuestsChartYAxis;

    @FXML
    public void initialize() {
        setupOccupancyChart();
        setupRevenueChart();
        setupGuestsChart();  // Added method
    }

    private void setupOccupancyChart() {
        occupancyPieChart.getData().addAll(
                new PieChart.Data("Occupied", 72),
                new PieChart.Data("Available", 28)
        );
    }

    private void setupRevenueChart() {
        XYChart.Series<String, Number> revenueSeries = new XYChart.Series<>();
        revenueSeries.setName("Revenue (R)");

        revenueSeries.getData().add(new XYChart.Data<>("Jan", 180000));
        revenueSeries.getData().add(new XYChart.Data<>("Feb", 200000));
        revenueSeries.getData().add(new XYChart.Data<>("Mar", 245800));
        revenueSeries.getData().add(new XYChart.Data<>("Apr", 210000));
        revenueSeries.getData().add(new XYChart.Data<>("May", 230000));

        revenueBarChart.getData().add(revenueSeries);
    }

    private void setupGuestsChart() {
        XYChart.Series<String, Number> guestsSeries = new XYChart.Series<>();
        guestsSeries.setName("Guests per Month");

        guestsSeries.getData().add(new XYChart.Data<>("Jan", 120));
        guestsSeries.getData().add(new XYChart.Data<>("Feb", 150));
        guestsSeries.getData().add(new XYChart.Data<>("Mar", 100));
        guestsSeries.getData().add(new XYChart.Data<>("Apr", 180));
        guestsSeries.getData().add(new XYChart.Data<>("May", 200));
        guestsSeries.getData().add(new XYChart.Data<>("Jun", 170));

        totalGuestsChart.getData().add(guestsSeries);
    }
}
