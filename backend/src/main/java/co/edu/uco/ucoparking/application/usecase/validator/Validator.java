package co.edu.uco.ucoparking.application.usecase.validator;

public interface Validator<T> {

    void validate(T data);
}
