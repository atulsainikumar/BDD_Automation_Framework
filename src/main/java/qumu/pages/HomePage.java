package qumu.pages;

import qumu.baseclass.BasePage;
import qumu.utils.LoadProp;

public class HomePage extends BasePage {

    public void launchApplication() {
        getDriver().get(LoadProp.getProperty("url"));
    }
}
