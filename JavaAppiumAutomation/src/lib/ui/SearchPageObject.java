package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private final static String
    SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
    SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
    SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
    SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
    SEARCH_FIELD = "org.wikipedia:id/search_src_text",
    FIRST_SUBTITLE = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Constitutional monarchy in Oceania']",
    SECOND_SUBTITLE = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Rugby union team of New Zealand']";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE_METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE_METHODS */

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }

    public void CheckSearchText(String expected)
    {
        this.CheckSearch(By.id(SEARCH_FIELD), "Cannot find search field", 5, "We see unexpected title", expected);
    }

    public void CheckSearchDoubleSubtitle()
    {
        this.CheckSearch(
                By.xpath(FIRST_SUBTITLE),
                "Cannot find 'Constitutional monarchy in Oceania' on page",
                5,
                "We see unexpected title",
                "Constitutional monarchy in Oceania"
        );

        this.CheckSearch(
                By.xpath(SECOND_SUBTITLE),
                "Cannot find 'Rugby union team of New Zealand' on page",
                5,
                "We see unexpected title",
                "Rugby union team of New Zealand"
        );
    }

    public void waitForDoubleSubtitle()
    {
        this.waitForElementNotPresent(
                By.xpath(FIRST_SUBTITLE),
                "'Constitutional monarchy in Oceania' is still present on this page",
                5
        );

        this.waitForElementNotPresent(
                By.xpath(SECOND_SUBTITLE),
                "'Rugby union team of New Zealand' is still present on this page",
                5
        );
    }
}
