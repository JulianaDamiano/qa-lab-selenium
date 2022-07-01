package com.nttdata.steps;

import com.nttdata.page.InventoryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventorySteps {

    private WebDriver driver;

    //constructor
    public InventorySteps(WebDriver driver){
        this.driver=driver;
    }

    /**
     * Obtener el título de la pantalla de productos
     * @return el valor del titulo de la pantalla de productos
     */

    public String getTitle(){
        return this.driver.findElement(InventoryPage.productsTitle).getText();

    }

    /**
     * Retorna la cantidad de item
     * @return la cantidad de item
     */
    public int getItemSize() {
        List<WebElement> items =this.driver.findElements(InventoryPage.itemCards);
        return items.size();
    }
}
