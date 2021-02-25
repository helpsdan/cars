package com.devnaweb.cars.unit.architecture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class UseCasesArchTest extends ArchCommon {

    @Test
    @DisplayName("Services should have '@Service' anotation")
    public void servicesShouldHaveAnnotation() {
        classes().that().resideInAPackage("..usecases..")
                .should().beAnnotatedWith(Service.class)
                .check(classes);
    }

    @Test
    @DisplayName("Services should only exists in your package")
    public void servicesShouldOnlyExistsInYourPackage() {
        noClasses().that().resideOutsideOfPackage("..usecases..")
                .should().beAnnotatedWith(Service.class)
                .check(classes);
    }

    @Test
    @DisplayName("Services should only be accessed by controllers or other services")
    public void ServicesShouldOnlyBeAccessedByControllersOrOtherServices() {
        classes().that().resideInAPackage("..usecases..")
                .should().onlyBeAccessed().byAnyPackage("..controllers..", "..usecases..")
                .check(classes);
    }

    @Test
    @DisplayName("Service should only access other services and repositories")
    public void ServicesShouldOnlyAccessOtherServicesAndRepositories() {
        classes().that().resideInAPackage("..usecases..")
                .should().accessClassesThat().resideInAnyPackage("..repositories..", "..usecases..")
                .check(classes);
    }
}
