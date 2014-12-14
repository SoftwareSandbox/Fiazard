package be.craftsmen.fiazardtje.exceptions;

import be.craftsmen.fiazard.common.error.AppErrorCode;
import be.craftsmen.fiazard.exceptions.FiazardException;
import com.google.common.collect.Lists;

import java.util.List;

public class FiazardExceptionBuilder {

    private int status;
    private AppErrorCode appErrorCode;
    private String message;
    private List<String> fields;

    public static FiazardExceptionBuilder someFiazardExceptionBuilder(){
        return new FiazardExceptionBuilder()
                .withStatus(400)
                .withAppError(AppErrorCode.ILLEGAL_ID)
                .withMessage("datMessageThough")
                .withFields("field1", "field2");
    }

    public static FiazardException someFiazardException(){
        return someFiazardExceptionBuilder().build();
    }

    public DummyFiazardException build() {
        return new DummyFiazardException(status, appErrorCode, message, fields);
    }

    public FiazardExceptionBuilder withStatus(int status) {
        this.status = status;
        return this;
    }
    public FiazardExceptionBuilder withAppError(AppErrorCode appErrorCode) {
        this.appErrorCode = appErrorCode;
        return this;
    }

    public FiazardExceptionBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public FiazardExceptionBuilder withFields(String... fields) {
        this.fields = Lists.newArrayList(fields);
        return this;
    }

    public class DummyFiazardException extends FiazardException{
        private static final long serialVersionUID = -9119892999746544720L;

        private final Iterable<String> fields;
        private final AppErrorCode appErrorCode;
        private final int status;

        public DummyFiazardException(int status, AppErrorCode appErrorCode, String message, Iterable<String> fields) {
            super(message);
            this.status = status;
            this.appErrorCode = appErrorCode;
            this.fields = fields;
        }

        @Override
        public AppErrorCode getErrorCode() {
            return appErrorCode;
        }

        @Override
        public int getStatus() {
            return status;
        }

        @Override
        public Iterable<String> getFields() {
            return fields;
        }
    }
}
