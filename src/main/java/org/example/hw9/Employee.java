package org.example.hw9;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Employee {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty position;
    private DoubleProperty salary;
    private ObjectProperty<LocalDate> hireDate;

    // Constructor
    public Employee(int id, String name, String position, double salary, LocalDate hireDate) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.salary = new SimpleDoubleProperty(salary);
        this.hireDate = new SimpleObjectProperty<>(hireDate);
    }

    public Employee(String name, String position, double salary, LocalDate hireDate) {
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.salary = new SimpleDoubleProperty(salary);
        this.hireDate = new SimpleObjectProperty<>(hireDate);
    }

    // Getters and setters for all properties
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPosition() {
        return position.get();
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public StringProperty positionProperty() {
        return position;
    }

    public double getSalary() {
        return salary.get();
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public LocalDate getHireDate() {
        return hireDate.get();
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate.set(hireDate);
    }

    public ObjectProperty<LocalDate> hireDateProperty() {
        return hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id.get() +
                ", name='" + name.get() + '\'' +
                ", position='" + position.get() + '\'' +
                ", salary=" + salary.get() +
                ", hireDate=" + hireDate.get() +
                '}';
    }
}
