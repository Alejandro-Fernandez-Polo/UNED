/**
 * PROYECTO: ANOMALIA EN EL SECTOR 4 - BUCLE DE LA MUERTE
 */

% ==========================================
% BASE DE HECHOS DINÁMICA
% Requisito: Uso de predicados de inserción y borrado (assert/retract)
% ==========================================
:- dynamic recuerdo/1.
:- dynamic inventario/1.
:- dynamic ciclo_actual/1.

% ==========================================
% HECHOS SIN VARIABLES (CONSTANTES)
% Requisito: 2 o mas constantes / 2 o mas hechos sin variables
% ==========================================
protagonista(alejandro).
ubicacion(sector4).
amenaza(dron_defensa).
amenaza(radiacion).

% ==========================================
% HECHOS CON VARIABLES
% Requisito: 2 o mas hechos con variables (Implementación relacional)
% ==========================================
% Define qué amenaza existe en cada ubicación crítica
peligro_ubicacion(reactor, radiacion).
peligro_ubicacion(seguridad, dron_defensa).

% Define a qué entidades es vulnerable el protagonista sin equipamiento
vulnerable_a(alejandro, radiacion).
vulnerable_a(alejandro, dron_defensa).

% ==========================================
% PREDICADOS PRINCIPALES
% Requisito: 5 o mas predicados en total
% ==========================================

% 1. PREDICADO DE INICIALIZACIÓN
iniciar :-
    retractall(recuerdo(_)),
    retractall(inventario(_)),
    retractall(ciclo_actual(_)),
    assert(ciclo_actual(1)),
    nl, write('--- SISTEMA REINICIADO: ANOMALÍA EN SECTOR 4 ---'), nl,
    write('El reactor cuántico va a explotar. Debes estabilizarlo.'), nl,
    menu_estacion.

% 2. PREDICADO DE BUCLE / MENÚ
% Verificamos primero el FINAL BUENO (Traje + Código + Elena rescatada)
menu_estacion :-
    inventario(traje_antiradiacion),
    recuerdo(codigo_descubierto),
    inventario(elena_rescatada),
    victoria_heroica, !. 

% Verificamos el FINAL NEUTRAL (Traje + Código, pero SIN Elena)
menu_estacion :-
    inventario(traje_antiradiacion),
    recuerdo(codigo_descubierto),
    \+ inventario(elena_rescatada), % Negación: No ha sido rescatada
    victoria_solitaria, !. 

menu_estacion :-
    nl, write('¿Dónde te diriges?'), nl,
    write('1. Entrar a la sala del reactor.'), nl,
    write('2. Buscar en las taquillas del personal.'), nl,
    write('3. Inspeccionar la sala de seguridad.'), nl,
    write('4. Entrar a la armería.'), nl,
    write('5. Ir a la enfermería.'), nl, % NUEVA OPCIÓN
    read(Opcion),
    ejecutar_accion(Opcion, alejandro).

% 3. PREDICADO DE ACCIÓN (Lógica del juego)
% Requisito: Al menos un predicado con 2 o mas argumentos (ejecutar_accion/2)
% Requisito: 2 o mas reglas deben constar de 2 o mas antecedentes

% Opción 1: Reactor (Muerte por unificación de peligro)
ejecutar_accion(1, Personaje) :-
    protagonista(Personaje),
    peligro_ubicacion(reactor, Peligro),   % <-- Prolog unifica Peligro = radiacion
    vulnerable_a(Personaje, Peligro),      % <-- Prolog verifica si Alejandro es vulnerable a la radiacion
    \+ inventario(traje_antiradiacion),
    write('Entras al reactor sin protección. La radiación te desintegra. MUERES.'), nl,
    reiniciar_bucle.

ejecutar_accion(1, Personaje) :-
    protagonista(Personaje),
    inventario(traje_antiradiacion),
    \+ recuerdo(codigo_descubierto),
    write('Llegas a la consola protegido por el traje, pero no sabes la contraseña.'), nl,
    write('Pierdes tiempo intentando hackearla y el núcleo explota. MUERES.'), nl,
    reiniciar_bucle.

% Opción 2: Taquillas (SIN el recuerdo de Elena)
ejecutar_accion(2, Personaje) :-
    protagonista(Personaje),
    \+ recuerdo(elena_atrapada),
    ubicacion(sector4),
    write('Revisas las taquillas de emergencia y encuentras un traje antirradiación.'), nl,
    write('Te lo pones para protegerte.'), nl,
    assert(inventario(traje_antiradiacion)),
    menu_estacion.

% Opción 2: Taquillas (CON el recuerdo de Elena)
ejecutar_accion(2, Personaje) :-
    protagonista(Personaje),
    recuerdo(elena_atrapada), % Antecedente empírico
    ubicacion(sector4),
    write('Revisas las taquillas y coges el traje antirradiación...'), nl,
    write('¡Y recordando a la Dra. Elena, también buscas y coges un BOTIQUÍN!'), nl,
    assert(inventario(traje_antiradiacion)),
    assert(inventario(botiquin)), % Nuevo objeto
    menu_estacion.

% Opción 3: Sala de Seguridad (Muerte por unificación de peligro)
ejecutar_accion(3, Personaje) :-
    protagonista(Personaje),
    peligro_ubicacion(seguridad, Peligro), % <-- Prolog unifica Peligro = dron_defensa
    vulnerable_a(Personaje, Peligro),      % <-- Prolog verifica vulnerabilidad
    \+ inventario(granada_emp),
    write('Entras a seguridad. Un dron de defensa automatizado te detecta.'), nl,
    write('Sin armas, no puedes defenderte. El dron te aniquila. MUERES.'), nl,
    write('(Nota mental: Necesito un pulso electromagnético - EMP).'), nl,
    assert(recuerdo(dron_descubierto)),
    reiniciar_bucle.

ejecutar_accion(3, Personaje) :-
    protagonista(Personaje),
    inventario(granada_emp),
    write('Entras a seguridad. El dron te ataca, pero le lanzas la granada EMP.'), nl,
    write('El dron se desactiva. Accedes a los registros y descubres el CÓDIGO: 7734.'), nl,
    assert(recuerdo(codigo_descubierto)),
    menu_estacion.

% Opción 4: Armería
ejecutar_accion(4, Personaje) :-
    protagonista(Personaje),
    \+ recuerdo(dron_descubierto),
    write('Entras a la armería, pero está llena de equipo y no sabes qué necesitas.'), nl,
    write('Pierdes demasiado tiempo buscando y el reactor explota. MUERES.'), nl,
    reiniciar_bucle.

ejecutar_accion(4, Personaje) :-
    protagonista(Personaje),
    recuerdo(dron_descubierto),
    write('Como recuerdas al dron letal, buscas directamente una granada EMP.'), nl,
    write('La guardas en tu inventario.'), nl,
    assert(inventario(granada_emp)),
    menu_estacion.

% Opción 5: Enfermería (Sin el botiquín)
ejecutar_accion(5, Personaje) :-
    protagonista(Personaje),
    \+ inventario(botiquin),
    write('Llegas a la enfermería y encuentras a la Dra. Elena gravemente herida.'), nl,
    write('Sin suministros médicos, no puedes estabilizarla. Pierdes tiempo y el reactor explota. MUERES.'), nl,
    write('(Nota mental: La Dra. Elena necesita un botiquín urgentemente).'), nl,
    assert(recuerdo(elena_atrapada)), % Adquieres la memoria
    reiniciar_bucle.

% Opción 5: Enfermería (Con el botiquín)
ejecutar_accion(5, Personaje) :-
    protagonista(Personaje),
    inventario(botiquin),
    \+ inventario(elena_rescatada), % Para que no la rescates dos veces
    write('Llegas a la enfermería y usas el botiquín para curar a la Dra. Elena.'), nl,
    write('Ella te lo agradece profundamente y decide acompañarte.'), nl,
    assert(inventario(elena_rescatada)),
    menu_estacion.

% Opción inválida
ejecutar_accion(_, _) :-
    write('Comando no reconocido. Tu indecisión es fatal. MUERES.'), nl,
    reiniciar_bucle.

% 4. PREDICADO DE REINICIO Y RECURSIVIDAD
% Requisito: Uso de operadores aritméticos/relacionales y recursividad
reiniciar_bucle :-
    ciclo_actual(N),
    N < 10, % Operador relacional: Límite de 10 intentos
    N1 is N + 1, % Operador aritmético
    retract(ciclo_actual(N)),
    % Borramos el inventario al morir, pero conservamos los recuerdos
    retractall(inventario(_)), 
    assert(ciclo_actual(N1)),
    nl, write('... EL TIEMPO SE RESETEA ...'), nl,
    write('Despiertas de nuevo. Estás en el ciclo número: '), write(N1), nl,
    menu_estacion. % Llamada recursiva

reiniciar_bucle :- 
    nl, write('El núcleo ha colapsado por completo. La mente no soporta más. SIMULACIÓN FINALIZADA.'), nl.

% 5. PREDICADOS DE VICTORIA MÚLTIPLE

% FINAL NEUTRAL
victoria_solitaria :-
    write('Entras al reactor con el traje puesto. La radiación no te afecta.'), nl,
    write('Vas directo a la consola e introduces el código 7734.'), nl,
    write('¡NÚCLEO ESTABILIZADO! Has roto el bucle... pero a qué costo.'), nl,
    write('Has sobrevivido, pero la base está llena de cadáveres que podrías haber salvado.'), nl,
    write('--- FINAL NEUTRAL: SUPERVIVIENTE SOLITARIO ---'), nl.

% FINAL BUENO
victoria_heroica :-
    write('Entras al reactor junto a la Dra. Elena. Tú usas el traje y cubres sus signos vitales.'), nl,
    write('Introduces el código 7734. ¡NÚCLEO ESTABILIZADO!'), nl,
    write('Has roto el bucle. La Dra. Elena te abraza, agradecida por haberle salvado la vida.'), nl,
    write('--- FINAL BUENO: HÉROE DEL SECTOR 4 ---'), nl.