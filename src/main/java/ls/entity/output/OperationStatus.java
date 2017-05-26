package ls.entity.output;

/**
 * Created by leishu on 17-5-26.
 */
public enum OperationStatus {
    SUCCESS(0), ERROR(-1), NOTUNIQUE(1), NOTNULL(2);

    private final Integer operationId;

    OperationStatus(Integer operationId) {

        this.operationId = operationId;
    }

    public Integer getOperationId() {
        return operationId;
    }
}
