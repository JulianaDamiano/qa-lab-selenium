package com.nttdata.stepsdefinitions;

import com.nttdata.steps.InventorySteps;
import com.nttdata.steps.LoginSteps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginStepsDef {

    private WebDriver driver;
    private Scenario scenario;


    private InventorySteps inventorySteps(WebDriver driver) {
        return new InventorySteps(driver);
    }

    @Before(order=0)
    public void setUp(){
        //setUp
        System.setProperty("webdriver.chrome.driver","C:\\Users\\USER\\Documents\\Git carpeta\\qa-lab-selenium\\drivers\\chromedriver.exe");
        //Crear el driver
        driver= new ChromeDriver();
        //max
        driver.manage().window().maximize();
    }
    //Esto se ejecuta antes de la prueba
    @Before(order=1)
    public void setScenario(Scenario scenario){
        this.scenario=scenario;
    }

    @After
    public void quitDriver(){
        driver.quit();
        //Para cerrar el driver
    }

    @Dado("que me encuentro en la pagina de login de Saucedemo")
    public void que_me_encuentro_en_la_pagina_de_login_de_saucedemo() {
        driver.get("https://www.saucedemo.com/");
        screenShot();
    }
    @Cuando("inicio sesión con las credenciales usuario: {string} y contraseña: {string}")
    public void inicio_sesión_con_las_credenciales_usuario_y_contraseña(String user, String password) {

        LoginSteps loginSteps =new LoginSteps(driver);
        loginSteps.typeUser(user);
        loginSteps.typePassword(password);
        loginSteps.login();
        screenShot();
    }
    @Entonces("valido que debería aparecer titulo de {string}")
    public void valido_que_debería_aparecer_titulo_de(String expectedTitle) {
        String title = inventorySteps(driver).getTitle();

        //Prueba: validamos el título del producto
        Assertions.assertEquals(expectedTitle,title);
    }
    @Y("tambien valido que al menos exista un item")
    public void tambien_valido_que_al_menos_exista_un_item() {
        int itemsListSize =inventorySteps(driver).getItemSize();
        //prueba: validar que al menos existe un item
        screenShot();
        Assertions.assertTrue(itemsListSize>0, "El tamaño de la lista es : "+ itemsListSize);
    }

    public void screenShot(){
        byte[] evidencia=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        this.scenario.attach(evidencia,"image/png","evidencias");
    }
}
