package com.TugasAkhirAPI.springapi.config;

public class SSOConfig {
    final static public String CLIENT_BASE_URL = "https://apap-059.cs.ui.ac.id";
    final static public String CLIENT_LOGIN = CLIENT_BASE_URL + "/validate-ticket";
    final static public String CLIENT_LOGOUT = CLIENT_BASE_URL + "/logout";

    final static public String SERVER_BASE_URL = "https://sso.ui.ac.id/cas2";

    final static public String SERVER_LOGIN = SERVER_BASE_URL + "/login?service=";

    final static public String SERVER_LOGOUT = SERVER_BASE_URL + "/logout?url=";

    final static public String SERVER_VALIDATE_TICKET = SERVER_BASE_URL + "/serviceValidate?ticket=%s&service=%s";
}
