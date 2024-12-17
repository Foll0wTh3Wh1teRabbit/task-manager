package ru.nsu.common.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Path {

    public static final String AUTH = "/auth";

    public static final String AUTH_IN_APP_REGISTER_ENDPOINT = AUTH + "/register/in-app";

    public static final String AUTH_CONFIRMATION_ENDPOINT = AUTH + "/confirmation";

    public static final String AUTH_IN_APP_LOGIN_ENDPOINT = AUTH + "/login/in-app";

    public static final String AUTH_QR_CODE_LOGIN_ENDPOINT = AUTH + "/login/qr-code";

    public static final String AUTH_LOGOUT_ENDPOINT = AUTH + "/logout";

    public static final String AUTH_RECOVERY_ENDPOINT = AUTH + "/recovery";


    public static final String PROFILE = "/profile";

    public static final String PROFILE_CHANGE_EMAIL_ENDPOINT = PROFILE + "/change/email";

    public static final String PROFILE_CHANGE_PASSWORD_ENDPOINT = PROFILE + "/change/password";

    public static final String PROFILE_GENERATE_QR_CODE_ENDPOINT = PROFILE + "/generate/qr-code";


    public static final String PROJECT = "/project";

    public static final String PROJECT_FETCH_ENDPOINT = PROJECT + "/fetch";

    public static final String PROJECT_CREATE_ENDPOINT = PROJECT + "/create";

    public static final String PROJECT_UPDATE_ENDPOINT = PROJECT + "/update";

    public static final String PROJECT_DELETE_ENDPOINT = PROJECT + "/delete";


    public static final String TASK = "/task";

    public static final String TASK_CREATE_ENDPOINT = TASK + "/create";

    public static final String TASK_UPDATE_ENDPOINT = TASK + "/update";

    public static final String TASK_DELETE_ENDPOINT = TASK + "/delete";

}
