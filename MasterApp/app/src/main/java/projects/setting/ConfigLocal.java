package projects.setting;

public abstract class ConfigLocal {
    public static String APP_SYS_ID;
    public static String APP_LOGIN_ADDRESS;
    public static String APP_BASE_ADDRESS;
    public static String FOCUS_BASE_ADDRESS;
    public static String APP_PUSH_ADDRESS;
    public static String APK_VERSION_ADDRESS_TEST;
    public static String APK_DOWNLOAD_ADDRESS_TEST;

    public enum LanZhou {
        EXTRANET() {
            @Override
            public void isRelease(boolean isRelease) {
                if (isRelease) {
                    APP_SYS_ID = "8a8185e2703f4fcb017066dbb3fc003a";
                    APP_LOGIN_ADDRESS = "http://39.101.176.211:8534";
                    APP_BASE_ADDRESS = "http://39.101.176.211:8534";
                    FOCUS_BASE_ADDRESS = "http://39.101.176.211:8534/dre-v1";
                    APP_PUSH_ADDRESS = "http://192.168.136.155:18150/communicationMsg/jPush/audience";
                    APK_VERSION_ADDRESS_TEST = "";
                    APK_DOWNLOAD_ADDRESS_TEST = "";
                }
            }
        };

        public abstract void isRelease(boolean isBeta);
    }

    public enum XiAn {
        INTRANET() {
            @Override
            public void isRelease(boolean isRelease) {
                if (isRelease) {
                    APP_SYS_ID = "8a8185e2703f4fcb017066dbb3fc003a";
                    APP_LOGIN_ADDRESS = "http://39.101.176.211:8534";
                    APP_BASE_ADDRESS = "http://39.101.176.211:8534";
                    FOCUS_BASE_ADDRESS = "http://39.101.176.211:8534/dre-v1";
                    APP_PUSH_ADDRESS = "http://192.168.136.155:18150/communicationMsg/jPush/audience";
                    APK_VERSION_ADDRESS_TEST = "";
                    APK_DOWNLOAD_ADDRESS_TEST = "";
                } else {
                    APP_SYS_ID = "8a8185e2703f4fcb017066dbb3fc003a";
                    APP_LOGIN_ADDRESS = "http://39.101.176.211:8534";
                    APP_BASE_ADDRESS = "http://39.101.176.211:8534";
                    FOCUS_BASE_ADDRESS = "http://39.101.176.211:8534/dre-v1";
                    APP_PUSH_ADDRESS = "http://192.168.136.155:18150/communicationMsg/jPush/audience";
                    APK_VERSION_ADDRESS_TEST = "";
                    APK_DOWNLOAD_ADDRESS_TEST = "";
                }
            }
        },
        EXTRANET() {
            @Override
            public void isRelease(boolean isRelease) {
                if (isRelease) {
                    APP_SYS_ID = "8a8185e2703f4fcb017066dbb3fc003a";
                    APP_LOGIN_ADDRESS = "0";
                    APP_BASE_ADDRESS = "1";
                    FOCUS_BASE_ADDRESS = "2";
                    APP_PUSH_ADDRESS = "3";
                    APK_VERSION_ADDRESS_TEST = "";
                    APK_DOWNLOAD_ADDRESS_TEST = "";
                } else {
                    APP_SYS_ID = "8a8185e2703f4fcb017066dbb3fc003a";
                    APP_LOGIN_ADDRESS = "4";
                    APP_BASE_ADDRESS = "5";
                    FOCUS_BASE_ADDRESS = "6";
                    APP_PUSH_ADDRESS = "7";
                    APK_VERSION_ADDRESS_TEST = "";
                    APK_DOWNLOAD_ADDRESS_TEST = "";
                }
            }
        };

        public abstract void isRelease(boolean isBeta);
    }
}
