package com.spring.boot.hystrix.basic;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Sample {@link HystrixCommand} that implements a fallback that returns an object
 * combining defaults and injected values from elsewhere in the system (such as
 * HTTP request headers, arguments and cookies or other services previously executed).
 */
public class CommandWithStubbedFallback extends HystrixCommand<UserAccount> {

    private final int customerId;
    private final String countryCodeFromGeoLookup;

    /**
     * @param customerId               The customerID to retrieve UserAccount for
     * @param countryCodeFromGeoLookup The default country code from the HTTP request geo code lookup used for fallback.
     */
    protected CommandWithStubbedFallback(int customerId, String countryCodeFromGeoLookup) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.customerId = customerId;
        this.countryCodeFromGeoLookup = countryCodeFromGeoLookup;
    }

    @Override
    protected UserAccount run() {
        // fetch UserAccount from remote service
        //        return UserAccountClient.getAccount(customerId);
        throw new RuntimeException("forcing failure for example");
    }

    @Override
    protected UserAccount getFallback() {
        /**
         * Return stubbed fallback with some static defaults, placeholders,
         * and an injected value 'countryCodeFromGeoLookup' that we'll use
         * instead of what we would have retrieved from the remote service.
         */
        return new UserAccount(customerId, "Unknown Name",
                countryCodeFromGeoLookup, true, true, false);
    }

    public static class UnitTest {

        @Test
        public void test() {
            CommandWithStubbedFallback command = new CommandWithStubbedFallback(1234, "ca");

            UserAccount account = command.execute();
            assertTrue(command.isFailedExecution());
            assertTrue(command.isResponseFromFallback());

            assertEquals(1234, account.getCustomerId());
            assertEquals("Unknown Name", account.getName());
            assertEquals("ca", account.getCountryCode());
            assertEquals(true, account.isFeatureXPermitted());
            assertEquals(true, account.isFeatureYPermitted());
            assertEquals(false, account.isFeatureZPermitted());
        }
    }
}
