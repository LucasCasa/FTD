Tower Defense
===

Alumnos:
* Lucas Casagrande
* Nicolas Kuyumciyan

===
<h3>Acerca de:</h3>
Este juego es del genero de los Tower Defense, en dichos juegos el objetivo es prevenir que los enemigo que van apareciendo lleguen al final del recorrido. para lograr eso se posibilita la creacion de torres que atacan a los enemigos.

===

<h3>Implementación</h3>
Para hacer el juego nos decidimos por ultilizar el lenguaje JAVA junto con las librerias llamada LIBGDX ( http://libgdx.badlogicgames.com/) que ayudan en lo que es el manejo de la interfaz grafica y la interaccion con el mouse. ademas de permitirnos facilmente exportar el proyecto tanto para android como para iOS, ademas de la version para desktop.

===

<h3>Funcionalidades</h3>
* Una vez que se ejecuta el juego aparece el siguente menu:
![IMG](http://www.imageupload.co.uk/images/2014/11/11/01.jpg)
* Haciendo click en jugar aparece la siguiente pantalla, que te permite elegir que nivel jugar
![IMG2](http://www.imageupload.co.uk/images/2014/11/11/02.jpg)
* Una vez que el nivel empieza los enemigos van a empezar a venir. para matarlos se pueden poner torres en los espacios que estan marcados con un cartelito:
![IMG3](http://www.imageupload.co.uk/images/2014/11/11/03.jpg)
* Al hacer click sobre uno de los espacios posibles para la construccion te aparece un menu para seleccionar cual de las 4 torres queres:
![IMG4](http://www.imageupload.co.uk/images/2014/11/11/04.jpg)
* En caso de querer informacion sobre las 4 torres haciendo click derecho sobre los botones te despliega la informacion:<br/>
![IMG10](http://www.imageupload.co.uk/images/2014/11/11/10mWozw.jpg)
* Si se hace click sobre una torre que ya existe te aparece informacion sobre dicha torre y la opcion de subirla de nivel o venderla:
![IMG5](http://www.imageupload.co.uk/images/2014/11/11/05.jpg)
* Al hacer click sobre un enemigo se muestra informacion como su nombre, la vida restante y la resistencia a los golpes:
![IMG6](http://www.imageupload.co.uk/images/2014/11/11/06.jpg)
* Tambien se muestra en la pantalla la informacion sobre la cantidad de monedas que tenes, la vida restante y el numero de oleada en el que estas: <br/>
![IMG7](http://www.imageupload.co.uk/images/2014/11/11/07.jpg)
* Cuando el jugador gana se le avisa que gano y se le da la posibilidad de volver al menu:
![IMG9](http://www.imageupload.co.uk/images/2014/11/11/09.jpg)
* Si en cambio el jugador perdio se le da la opcion de reintentarlo o de volver al menu:
![IMG8](http://www.imageupload.co.uk/images/2014/11/11/08.jpg)

===

<h3>Limitaciones y Desiciones discutibles </h3>

* A la hora de hacer la(s) clases que representaban los enemigos se nos ocurrieron 2 posibilidades, una era hacer una clase Unit con toda la implementacion y despues hacer 1 clase hijo por cada tipo de enemigo en donde inicilizaba todas las variables(como la dimension la vida o la velocidad), esto evitaba tener que pasar como parametros toda la informacion cada vez que se queria crear una tropa, pero iba a generar que tengamos muchas clases que lo unico que difieren es en la inicializacion de las variables.( esto tambien para la UnitUI).  La otra posibilidad era hacer una sola clase Unit y que a la hora de crearla en el constructor se le pase todos los parametros que la identificaban como un tipo determinado de enemigo. La solucion a la que llegamos fue crear una clase llamada UnitCreator que entre otras cosas recibia como parametro el ID de la tropa que tenia que crear y tiene un metodo para cada tipo de enemigo que le pasa todos los parametros a la clase Unit, de forma de que haya solo una clase Unit(y UnitUI) y que a la hora de crear un nuevo enemigo el codigo quede un poco mas limpio.
* Una limitacion que tuvimos es que el juego solo esta diseñado para correr a un determinado tamaño de pantalla, al querer implementar la posiblidad de multiples resoluciones el manejo de la interfaz resultaba muy complejo.

===
<h3> Funcionamiento interno del juego </h3>
<p> Por como esta creada la libreria LibGDX hay una clase DestopLaucher que se encarga de configurar la ventana del juego, una vez que esta hecho llama a la clase Main, dentro de la clase Main hay un metodo que se llama create() que se llama al comienzo y otra que se llama render() que es el que se repite ciclicamente.
en metodo render() inicia el SpriteBatch( que se encarga de dibujar las imagenes en pantalla) y llama al metodo update() del LevelManager. <br/>
Este metodo es el que se encarga de manejar la logica dependiendo del nivel que se encuentre. si detecta que se comenzo un nuevo nivel carga todos los datos necesarios.  Despues llama al GameEngine(back) y al InterfaceManager(front). 
</p>
* La clase GameEngine es la que maneja toda la logica interna del juego, esto es:
  * Detectar si se gano o se perdio el nivel
  * Detectar si se gano la oleada, en caso afirmativo llamar a la clase correspondiente para que agregue los nuevos enemigos
  * hacer que todos los enemigos se actualicen( su posicion)
  * Hacer que todas las torres se actualicen ( si tienen que atacar)
* La clase InterfaceManager es la que maneja que se dibuje toda la interfaz grafica, esto es:
  * Dibujar el fondo
  * Dibujar los enemigos
  * Dibujar las torres
  * Dibujar los ataques
  * Dibujar los efectos
  * Dibujar toda las informacion que aparezca en pantalla
* Despues tambien esta la clase TouchProcessor que es llamada cuando se detecta que el usuario presiono un boton del mouse y que se encarga de manejar:
  * Que el usuario haya clickeado un lugar donde se puede construir una torre
  * Que el usuario haya clickeado un lugar donde hay una torre
  * Que el usuario haya clickeado un enemigo para ver su informacion
  * Que el usuario haya hecho click derecho sobre uno de los botones para crear una nueva torre para ver su informacion.

Cabe destacar que la clase InterfaceManager tiene tambien otra parecida llama MenuInterfaceManager que es la que se llama cuando se tiene que dibujar el menu y lo mismo pasa con la MenuTouchManager que se llama para manejar el mouse en el menu, y que permiten separar un poco lo que es la interfaz dentro del menu de la que esta dentro del "juego". <br/> <br/>

Otra clase importante es la llamada UnitCreator que es la que se encarga de leer el XML que tiene la informacion sobre las oleadas de cada nivel y crear los enemigos en base a dicho archivo y guardarlas en el Array de unidades. <br/>

A diferencia de la las unidades donde tienen una sola clase (Unit) las torres tienen una clase para cada una de ellas, ya que su comportamiento es diferente, aunque todas heredan de Tower. <br/>

Se craron 2 interfaces que se usan a lo largo de todo el proyecto que son Logical y Drawable, que lo unico que requieren es crear un metodo (update() y draw() respectivamente) y que permiten identificar las clases. <br/>

Tambien se implemento un nuevo tipo de Array Personalizado para poder mantener junto la parte logica con la parte grafica.

