package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject
{

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeApplication[@name='Wikipedia']/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeSearchField";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_FIELD = "id:org.wikipedia:id/search_src_text";
        FIRST_SUBTITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Constitutional monarchy in Oceania']";
        SECOND_SUBTITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Rugby union team of New Zealand']";
    }

    public IOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
