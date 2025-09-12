package com.company.security;

public class AccessModifierDemo {
    
    private int privateField;
    String defaultField;
    protected double protectedField;
    public boolean publicField;
    
    public AccessModifierDemo() {
        privateField = 10;
        defaultField = "Hello";
        protectedField = 3.14;
        publicField = true;
    }
    
    private void privateMethod() {
        System.out.println("Private method called");
    }
    
    void defaultMethod() {
        System.out.println("Default method called");
    }
    
    protected void protectedMethod() {
        System.out.println("Protected method called");
    }
    
    public void publicMethod() {
        System.out.println("Public method called");
    }
    
    public void testInternalAccess() {
        System.out.println("Private field: " + privateField);
        System.out.println("Default field: " + defaultField);
        System.out.println("Protected field: " + protectedField);
        System.out.println("Public field: " + publicField);
        
        privateMethod();
        defaultMethod();
        protectedMethod();
        publicMethod();
    }
    
    public static void main(String[] args) {
        AccessModifierDemo demo = new AccessModifierDemo();
        
        System.out.println(demo.publicField);
        demo.publicMethod();
        
        demo.testInternalAccess();
    }
}

class SamePackageTest {
    public static void testAccess() {
        AccessModifierDemo demo = new AccessModifierDemo();
        
        System.out.println(demo.defaultField);
        System.out.println(demo.protectedField);
        System.out.println(demo.publicField);
        
        demo.defaultMethod();
        demo.protectedMethod();
        demo.publicMethod();
    }
}