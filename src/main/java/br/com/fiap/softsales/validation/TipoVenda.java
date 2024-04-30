package br.com.fiap.softsales.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoVendaValidator.class)
public @interface TipoVenda {

    String message() default "{venda.tipo.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}