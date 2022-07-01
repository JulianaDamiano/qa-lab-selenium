#language: es

  Característica: Login
    Yo , como usuario
    Quiero, tener una opción para iniciar sesión
    Para ver todos los items
@test
  Escenario: Iniciar sesión
    Dado que me encuentro en la pagina de login de Saucedemo
    Cuando inicio sesión con las credenciales usuario: "standard_user" y contraseña: "secret_sauce"
    Entonces valido que debería aparecer titulo de "PRODUCTS"
    Y tambien valido que al menos exista un item