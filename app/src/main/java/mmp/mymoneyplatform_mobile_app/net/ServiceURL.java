package mmp.mymoneyplatform_mobile_app.net;

/**
 * Created by K on 18/04/2016.
 */
public class ServiceURL {
    //Base URL of the API
    public static final String REST_SERVICE_URL = "http://api.mymoneyplatform.org/api";
    //Sections of the API
    public static final String ACCOUNT = REST_SERVICE_URL + "/Account";
    public static final String DEFAULT = REST_SERVICE_URL + "/Default";
    public static final String DASHBOARD = REST_SERVICE_URL + "/Dashboard";
    //Params of the API
    public static final String URL_PARAM_ENTITYNAME = "entityName";

    public static final String URL_PARAM_USERSUBSCRIPTIONID = "userSubscriptionID";
    //Values of the API
    public static final String URL_VAL_COUNTRY = "country";
    public static final String URL_VAL_PAYPERIOD = "payperiod";


}
