package br.com.fiap.softsales.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoVendaValidator implements ConstraintValidator<TipoVenda, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("Cartão") || value.equals("Dinheiro") || value.equals("Pix");
    }

}
