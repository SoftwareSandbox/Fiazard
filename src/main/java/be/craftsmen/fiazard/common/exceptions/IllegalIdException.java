package be.craftsmen.fiazard.common.exceptions;

import be.craftsmen.fiazard.common.error.AppErrorCode;
import com.google.common.collect.Lists;

public class IllegalIdException extends FiazardException {

    private static final long serialVersionUID = 7524886230064484746L;
    private Iterable<String> idFields = Lists.newArrayList("id");

    public IllegalIdException(String id) {
        super(String.format("id invalid: %s", id));
    }

    @Override
    public AppErrorCode getErrorCode() {
        return AppErrorCode.ILLEGAL_ID;
    }

    @Override
    public int getStatus() {
        return 400; // bad request
    }

    @Override
    public Iterable<String> getFields() {
        return idFields;
    }
}
