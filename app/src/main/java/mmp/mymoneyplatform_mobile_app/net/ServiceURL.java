package mmp.mymoneyplatform_mobile_app.net;

/**
 * Created by K on 18/04/2016.
 */
public class ServiceURL {
    //Base URL of the API
    public static final String REST_SERVICE_URL = "http://api.mymoneyplatform.org/api";
    public static final String REVIEW_SERVICE_URL = "http://review.mymoneyplatform.org";
    //Sections of the API
    public static final String ACCOUNT = REST_SERVICE_URL + "/Account";
    public static final String DEFAULT = REST_SERVICE_URL + "/Default";
    public static final String DASHBOARD = REST_SERVICE_URL + "/Dashboard";
    //Params of the API
    public static final String URL_PARAM_ENTITYNAME = "entityName";
    public static final String URL_PARAM_USERSUBSCRIPTIONID = "userSubscriptionID";
    public static final String URL_PARAM_EMAIL = "email";
    public static final String URL_PARAM_PASSWORD = "password";
    public static final String URL_PARAM_CONFIRMPASSWORD = "confirmPassword";
    public static final String URL_PARAM_USERNAME = "name";
    public static final String URL_PARAM_DAYOFBIRTH = "dateOfBirth";
    public static final String URL_PARAM_COUNTRY = "country";
    public static final String URL_PARAM_PAYPERIOD = "payperiod";


//    URL: {testdomain}/api/Account?email={email}&password={password}&confirmPassword={confirmPassword}&name={UserName}&payperiod={payperiod}&country={Country}&dateOfBirth={dateofBirth}


}
