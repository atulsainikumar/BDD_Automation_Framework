package qumu;

public class HomePage extends BasePage {

	public void launchApplication() {

        driver.get(LoadProp.getproperty("url"));
	}
}
