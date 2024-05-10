package app.util;

public class Variables {

    private Variables() {
    };

    // PAGE
    public static String FXMLBasePath = "/app/fxml/";
    public static String baseFXMLBasePath = FXMLBasePath + "base/";
    public static String authFXMLBasePath = FXMLBasePath + "auth/";
    public static String pagesFXMLBasePath = FXMLBasePath + "page/";

    // PAGE => AUTH
    public static String authFXMLPath = authFXMLBasePath + "Auth.fxml";
    public static String loginFXMLPath = authFXMLBasePath + "Login.fxml";
    public static String signupFXMLPath = authFXMLBasePath + "Signup.fxml";

    // PAGE => BASE
    public static String baseFXMLPath = baseFXMLBasePath + "Base.fxml";

    // IMAGE
    public static String imageBasePath = "/app/images/";
    public static String logoPath = imageBasePath + "logo.png";
    public static String bgAuthPath = imageBasePath + "bg-auth.png";

    // IMAGE => ICON
    public static String iconBasePath = imageBasePath + "icon/";

    // IMAGE => ICON => BOTTOMBAR
    public static String playIconPath = iconBasePath + "icon-play.png";
    public static String pauseIconPath = iconBasePath + "icon-pause.png";
    public static String nextIconPath = iconBasePath + "icon-next.png";
    public static String repeatOnIconPath = iconBasePath + "icon-repeat-on.png";
    public static String repeatOffIconPath = iconBasePath + "icon-repeat-off.png";
    public static String shuffleOnIconPath = iconBasePath + "icon-shuffle-on.png";
    public static String shuffleOffIconPath = iconBasePath + "icon-shuffle-off.png";

    // IMAGE => ICON => LEFTBAR
    public static String homeIconPath = iconBasePath + "icon-home.png";
    public static String searchIconPath = iconBasePath + "icon-search.png";
    public static String libraryIconPath = iconBasePath + "icon-library.png";
    public static String copyleftIconPath = iconBasePath + "icon-copyleft.png";

    // CSS
    public static String CSSBasePath = "/app/css/";
    public static String baseCSSBasePath = CSSBasePath + "base/";
    public static String authCSSBasePath = CSSBasePath + "auth/";

    // CSS => BASE
    public static String baseCSSPath = baseCSSBasePath + "base.css";
    public static String topbarCSSPath = baseCSSBasePath + "topbar.css";
    public static String leftbarCSSPath = baseCSSBasePath + "leftbar.css";
    public static String bottombarCSSPath = baseCSSBasePath + "bottombar.css";
    public static String bodyCSSPath = baseCSSBasePath + "body.css";

    // CSS => auth
    public static String authCSSPath = authCSSBasePath + "auth.css";

}
