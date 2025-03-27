package hw5;

import org.openqa.selenium.By;

public class Hw5 {
    //составление xpath
    private static final By HEADER = By.xpath("//h1[@class=\"display-4\"]");
    private static final By PAGE_NAME = By.xpath("//h1[@class=\"display-6\"]");
    private static final By TEXT_INPUT = By.xpath("//input[@id=\"my-text-id\"]");
    private static final By PASSWORD = By.xpath("//input[@name=\"my-password\"]");
    private static final By TEXT_AREA = By.xpath("//textarea[@name=\"my-textarea\"]");
    private static final By DISABLED_INPUT = By.xpath("//input[@name=\"my-disabled\"]");
    private static final By READONLY_INPUT = By.xpath("//input[@name=\"my-readonly\"]");
    private static final By DROPDOWN_SELECT = By.xpath("//select[@name=\"my-select\"]");
    private static final By DROPDOWN_DATA_LIST = By.xpath("//input[@name=\"my-datalist\"]");
    private static final By FILE_INPUT = By.xpath("//input[@name=\"my-file\"]");
    private static final By CHECKED_CHECKBOX = By.xpath("//input[@id=\"my-check-1\"]");
    private static final By DEFAULT_CHECKBOX = By.xpath("//input[@id=\"my-check-2\"]");
    private static final By CHECKED_RADIO = By.xpath("//input[@id=\"my-radio-1\"]");
    private static final By DEFAULT_RADIO = By.xpath("//input[@id=\"my-radio-2\"]");
    private static final By SUBMIT = By.xpath("//button[@type=\"submit\"]");
    private static final By COLOR_PICKER = By.xpath("//input[@name=\"my-colors\"]");
    private static final By DATE_PICKER = By.xpath("//input[@name=\"my-date\"]");
    private static final By EXAMPLE_RANGE = By.xpath("//input[@name=\"my-range\"]");

    


}
