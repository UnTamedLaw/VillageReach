package com.lawk.villagereach;

public class AccountUtil {

    /*
    *
    * the account type id for AccountManager. You can then get account by type from an instance of AccountManager.
    *
    * e.g  Account[] accounts = accountManager.getAccountsByType(AccountUtil.ACCOUNT_TYPE)
    *
    */

    public static final String ACCOUNT_TYPE = "com.lawk.villagereach";

    public static final String ACCOUNT_NAME = "VillageReach";

    public static final String AUTH_TOKEN_TYPE = "password&username"; // maybe this gets set to bearer?

}
