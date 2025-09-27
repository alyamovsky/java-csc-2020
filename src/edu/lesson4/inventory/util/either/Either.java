package edu.lesson4.inventory.util.either;

public sealed interface Either<L, R> permits Left, Right {
    boolean isLeft();

    default boolean isRight() {
        return !isLeft();
    }

    default L left() {
        throw new IllegalStateException("Not a Left: " + this);
    }

    default R right() {
        throw new IllegalStateException("Not a Right: " + this);
    }

    @SuppressWarnings("unchecked")
    default <L1> Either<L1, R> mapLeft(java.util.function.Function<? super L, ? extends L1> f) {
        return isLeft() ? new Left<>(f.apply(left())) : (Either<L1, R>) this;
    }

    @SuppressWarnings("unchecked")
    default <R1> Either<L, R1> mapRight(java.util.function.Function<? super R, ? extends R1> f) {
        return isRight() ? new Right<>(f.apply(right())) : (Either<L, R1>) this;
    }

    default <X extends Throwable> R orElseThrow(java.util.function.Function<? super L, ? extends X> ex) throws X {
        if (isRight()) {
            return right();
        } else {
            throw ex.apply(left());
        }
    }
}
