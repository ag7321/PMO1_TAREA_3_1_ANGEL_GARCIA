package com.example.pmo1_tarea_3_1.Configuracion;

public class Transacciones {


    public static final String NAME_DATABASE = "PM1EMPLEADOS";

    public static final String NAME_TABLE = "empleados";

    public static final String EMPLOYEE_ID = "id";
    public static final String EMPLOYEE_NAMES = "nombres";
    public static final String EMPLOYEE_SURNAMES = "apellidos";
    public static final String EMPLOYEE_DIRECTION = "direccion";
    public static final String EMPLOYEE_JOB = "trabajo";
    public static final String EMPLOYEE_AGE = "anios";


    public static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + NAME_TABLE +
            "("+
            EMPLOYEE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            EMPLOYEE_NAMES +" TEXT, "+
            EMPLOYEE_SURNAMES +" TEXT, "+
            EMPLOYEE_DIRECTION +" TEXT, "+
            EMPLOYEE_JOB +" TEXT, "+
            EMPLOYEE_AGE +" INTEGER"+
            ")";

    public static final String DROP_TABLE_EMPLOYEE = "DROP TABLE IF EXIST " + NAME_TABLE;
    public static final String SELECT_ALL_TABLE_EMPLOYEE = "SELECT * FROM " + NAME_TABLE;
}
