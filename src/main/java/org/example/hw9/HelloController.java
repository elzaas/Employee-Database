package org.example.hw9;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

public class HelloController {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldPosition;
    @FXML
    private TextField textFieldSalary;
    @FXML
    private DatePicker datePickerHireDate;
    @FXML
    private TextField textFieldSearchId;
    @FXML
    private TableView<Employee> tableViewEmployees;
    @FXML
    private TableColumn<Employee, String> columnEmployeeName;
    @FXML
    private TableColumn<Employee, String> columnEmployeePosition;
    @FXML
    private TableColumn<Employee, Double> columnEmployeeSalary;
    @FXML
    private TableColumn<Employee, LocalDate> columnEmployeeHireDate;

    private EmployeeData employeeData;
    private Employee selectedEmployee;

    public HelloController() {
        this.employeeData = new EmployeeData();
    }

    @FXML
    private void initialize() {
        columnEmployeeName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        columnEmployeePosition.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        columnEmployeeSalary.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());
        columnEmployeeHireDate.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty());

        refreshTableView();
    }

    @FXML
    private void handleAddEmployee() {
        String name = textFieldName.getText();
        String position = textFieldPosition.getText();
        String salaryText = textFieldSalary.getText();
        LocalDate hireDate = datePickerHireDate.getValue();

        if (name.isEmpty() || position.isEmpty() || salaryText.isEmpty() || hireDate == null) {
            System.out.println("Please fill in all fields.");
            return;
        }

        double salary;
        try {
            salary = Double.parseDouble(salaryText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary. Please enter a valid number.");
            return;
        }

        Employee employee = new Employee(name, position, salary, hireDate);
        employeeData.createEmployee(employee);
        refreshTableView();
        clearFields();
        System.out.println("Employee added: " + employee);
    }

    @FXML
    private void handleUpdateEmployee() {
        if (selectedEmployee == null) {
            System.out.println("No employee selected.");
            return;
        }

        String name = textFieldName.getText();
        String position = textFieldPosition.getText();
        String salaryText = textFieldSalary.getText();
        LocalDate hireDate = datePickerHireDate.getValue();

        if (name.isEmpty() || position.isEmpty() || salaryText.isEmpty() || hireDate == null) {
            System.out.println("Please fill in all fields.");
            return;
        }

        double salary;
        try {
            salary = Double.parseDouble(salaryText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary. Please enter a valid number.");
            return;
        }

        selectedEmployee.setName(name);
        selectedEmployee.setPosition(position);
        selectedEmployee.setSalary(salary);
        selectedEmployee.setHireDate(hireDate);

        employeeData.updateEmployee(selectedEmployee);
        refreshTableView();
        clearFields();
        System.out.println("Employee updated: " + selectedEmployee);
    }

    @FXML
    private void handleDeleteEmployee() {
        if (selectedEmployee == null) {
            System.out.println("No employee selected.");
            return;
        }

        employeeData.deleteEmployee(selectedEmployee.getId());
        refreshTableView();
        clearFields();
        System.out.println("Employee deleted: " + selectedEmployee);
    }

    @FXML
    private void handleSearchEmployeeById() {
        String searchIdText = textFieldSearchId.getText();
        if (searchIdText.isEmpty()) {
            System.out.println("Please enter an employee ID.");
            return;
        }

        int searchId;
        try {
            searchId = Integer.parseInt(searchIdText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a valid number.");
            return;
        }

        Employee employee = employeeData.getEmployeeById(searchId);
        if (employee != null) {
            textFieldName.setText(employee.getName());
            textFieldPosition.setText(employee.getPosition());
            textFieldSalary.setText(Double.toString(employee.getSalary()));
            datePickerHireDate.setValue(employee.getHireDate());
            selectedEmployee = employee;
            System.out.println("Employee found: " + employee);
        } else {
            System.out.println("Employee not found.");
        }
    }

    @FXML
    private void handleTableClick() {
        selectedEmployee = tableViewEmployees.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            textFieldName.setText(selectedEmployee.getName());
            textFieldPosition.setText(selectedEmployee.getPosition());
            textFieldSalary.setText(Double.toString(selectedEmployee.getSalary()));
            datePickerHireDate.setValue(selectedEmployee.getHireDate());
        }
    }

    private void refreshTableView() {
        List<Employee> employees = employeeData.getAllEmployees();
        tableViewEmployees.setItems(FXCollections.observableArrayList(employees));
    }

    private void clearFields() {
        textFieldName.clear();
        textFieldPosition.clear();
        textFieldSalary.clear();
        datePickerHireDate.setValue(null);
        textFieldSearchId.clear();
        selectedEmployee = null;
    }
}
