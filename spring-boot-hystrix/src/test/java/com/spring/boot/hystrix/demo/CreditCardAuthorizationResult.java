package com.spring.boot.hystrix.demo;

/**
 * POJO for holding the result of a CreditCardAuthorization
 */
public class CreditCardAuthorizationResult {

    public static CreditCardAuthorizationResult createSuccessResponse(String transactionID, String authorizationCode) {
        return new CreditCardAuthorizationResult(true, transactionID, authorizationCode, false);
    }

    public static CreditCardAuthorizationResult createDuplicateSuccessResponse(String transactionID, String authorizationCode) {
        return new CreditCardAuthorizationResult(true, transactionID, authorizationCode, true);
    }

    public static CreditCardAuthorizationResult createFailedResponse(String message) {
        return new CreditCardAuthorizationResult(false, message, null, false);
    }

    private final boolean success;
    private final boolean isDuplicate;
    private final String authorizationCode;
    private final String transactionID;
    private final String errorMessage;

    /**
     * Private constructor that normally would be a horrible API as it re-uses different arguments for different state.
     * 
     * @param success
     * @param value
     * @param isResponseDuplicate
     *            boolean whether the response is the result of a duplicate transaction returning a previously submitted transaction result
     *            <p>
     *            This is for handling the idempotent double-posting scenario, such as retries after timeouts.
     */
    private CreditCardAuthorizationResult(boolean success, String value, String value2, boolean isResponseDuplicate) {
        this.success = success;
        this.isDuplicate = isResponseDuplicate;
        if (success) {
            this.transactionID = value;
            this.authorizationCode = value2;
            this.errorMessage = null;
        } else {
            this.transactionID = null;
            this.errorMessage = value;
            this.authorizationCode = null;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    /**
     * Whether this result was a duplicate transaction.
     * 
     * @return boolean
     */
    public boolean isDuplicateTransaction() {
        return isDuplicate;
    }

    /**
     * If <code>isSuccess() == true</code> this will return the authorization code.
     * <p>
     * If <code>isSuccess() == false</code> this will return NULL.
     * 
     * @return String
     */
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    /**
     * If <code>isSuccess() == true</code> this will return the transaction ID.
     * <p>
     * If <code>isSuccess() == false</code> this will return NULL.
     * 
     * @return String
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * If <code>isSuccess() == false</code> this will return the error message.
     * <p>
     * If <code>isSuccess() == true</code> this will return NULL.
     * 
     * @return String
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
