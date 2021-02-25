package com.devnaweb.cars.unit.architecture;

import com.tngtech.archunit.thirdparty.com.google.common.collect.ObjectArrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ControllersArchTest extends ArchCommon {
    private static final String[] COMMOM_PACKAGES = {
            "java..", "org.springframework..", "..controllers.."
    };

    private static String[] commonPackagesAnd(String... packages) {
        return ObjectArrays.concat(COMMOM_PACKAGES, packages, String.class);
    }

    @Test
    @DisplayName("Controllers should have 'RestController' annotation and should have suffix 'Controller'")
    void controllersShouldHaveAnnotationAndSuffixController() {
        classes().that().resideInAPackage("..controllers..")
                .should().beAnnotatedWith(RestController.class)
                .andShould().haveSimpleNameEndingWith("Controller")
                .check(classes);
    }

    @Test
    @DisplayName("Should not have classes with '@RestController' and/or suffix 'Controller' outside Controllers package")
    public void controllersShouldNotExistOutsideOfYourPackage() {
        noClasses().that().resideOutsideOfPackage("..controllers..")
                .should().beAnnotatedWith(RestController.class)
                .orShould().haveSimpleNameEndingWith("Controller")
                .check(classes);
    }

    @Test
    @DisplayName("Controllers should not be accessed from outside your package")
    public void controllersShouldNotBeAccessedFromOutsideYourPackage() {
        noClasses().that().resideOutsideOfPackage("..controllers..")
                .should().accessClassesThat().resideInAPackage("..controllers..")
                .check(classes);
    }

    @Test
    @DisplayName("Controllers should only access classes in Services package")
    public void controllersShouldOnlyAccessClassesInServicesPackages() {
        classes().that().resideInAPackage("..controllers..")
                .should().onlyAccessClassesThat().resideInAnyPackage(commonPackagesAnd("..usecases.."))
                .check(classes);
    }

}
