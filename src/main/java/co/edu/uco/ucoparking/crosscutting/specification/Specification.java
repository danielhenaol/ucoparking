package co.edu.uco.ucoparking.crosscutting.specification;

public interface Specification<T> {

    boolean isSatisfiedBy(T candidate);
}
