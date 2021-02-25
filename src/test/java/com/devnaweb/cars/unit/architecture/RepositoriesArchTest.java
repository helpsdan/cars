package com.devnaweb.cars.unit.architecture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class RepositoriesArchTest extends ArchCommon {

    @Test
    @DisplayName("Repositories should have '@Repository' anotation, suffix 'Repository', be interfaces")
    public void repositoriesShouldHaveAnnotationSuffixRepositoryAndBeInterfaces() {
        classes().that().resideInAPackage("..repositories..")
                .should().beAnnotatedWith(Repository.class)
                .andShould().haveSimpleNameEndingWith("Repository")
                .andShould().beInterfaces()
                .andShould().beAssignableTo(CrudRepository.class)
                .check(classes);
    }

    @Test
    @DisplayName("Repositories should only be accessed by Services")
    public void repositoriesShouldOnlyBeAccessedByServices() {
        classes().that().resideInAPackage("..repositories..")
                .should().onlyBeAccessed().byClassesThat().resideInAPackage("..usecases..")
                .check(classes);
    }

    @Test
    @DisplayName("Repositories should only exists in your package")
    public void repositoriesShouldOnlyExistsInYourPackage() {
        noClasses().that().resideOutsideOfPackage("..repositories..")
                .should().beAnnotatedWith(Repository.class)
                .orShould().haveSimpleNameEndingWith("Repository")
                .check(classes);
    }
}
