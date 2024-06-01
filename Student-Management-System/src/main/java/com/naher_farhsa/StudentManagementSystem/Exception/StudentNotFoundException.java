package com.naher_farhsa.StudentManagementSystem.Exception;

public class StudentNotFoundException extends Exception {
   // String message="Student not found";
    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String message) {
        super(message);
       // this.message=message;
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
       // this.message=message;
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
       // this.message=message;
    }

    public StudentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
      //  this.message=message;
    }
}
