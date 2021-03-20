package com.rakib.employee.util;


public final class UrlConstraint {
    private UrlConstraint() {
    }

    private static final String VERSION = "/v1";
    private static final String API = "/api";

    public static class EmployeeManagement {
        public static final String ROOT = VERSION + API + "/employees";
        public static final String DELETE = "/{employeeId}";
        public static final String GET = "/{employeeId}";
        public static final String PUT = "/{employeeId}";
    }


}
