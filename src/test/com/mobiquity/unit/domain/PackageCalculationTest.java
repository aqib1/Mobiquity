package com.mobiquity.unit.domain;

import com.mobiquity.domain.Package;
import com.mobiquity.domain.PackageCalculation;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import org.junit.jupiter.api.Test;

public class PackageCalculationTest {
    @Test
    public void packageCalculationTest() {
        PojoClass pojoClass = PojoClassFactory.getPojoClass(PackageCalculation.class);
        Validator validator = ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                .with(new GetterTester())
                .build();
        validator.validate(pojoClass);
    }
}
