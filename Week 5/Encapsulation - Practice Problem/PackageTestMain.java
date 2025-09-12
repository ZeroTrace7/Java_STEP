package com.company.main;

import com.company.security.AccessModifierDemo;

public class PackageTestMain {
    public static void main(String[] args) {
        AccessModifierDemo demo = new AccessModifierDemo();
        
        System.out.println(demo.publicField);
        demo.publicMethod();
        
        demo.testInternalAccess();
    }
}

package com.company.extended;

import com.company.security.AccessModifierDemo;

public class ExtendedDemo extends AccessModifierDemo {
    
    public ExtendedDemo() {
        super();
    }
    
    public void testInheritedAccess() {
        System.out.println("Protected field: " + protectedField);
        System.out.println("Public field: " + publicField);
        
        protectedMethod();
        publicMethod();
    }
    
    @Override
    protected void protectedMethod() {
        System.out.println("Overridden protected method called");
    }
    
    public static void main(String[] args) {
        ExtendedDemo child = new ExtendedDemo();
        child.testInheritedAccess();
        
        AccessModifierDemo parent = new AccessModifierDemo();
        System.out.println(parent.publicField);
        parent.publicMethod();
        parent.testInternalAccess();
        
        System.out.println("Child accessing inherited members:");
        child.testInheritedAccess();
    }
}