import time
import getpass
import os

# Mapeo de teclas a jugadas
jugadas_j1 = {'A': 'piedra', 'S': 'papel', 'D': 'tijera'}
jugadas_j2 = {'4': 'piedra', '5': 'papel', '6': 'tijera'}

# Variables de control
racha_j1 = 0
racha_j2 = 0
repeticiones_j1 = 0
repeticiones_j2 = 0
ultima_jugada_j1 = None
ultima_jugada_j2 = None
ronda = 0

# Nombre del archivo
fichero = "jugadas.txt"

def determinar_ganador(j1, j2):
    if j1 == j2:
        return 0  # Empate
    if (j1 == 'piedra' and j2 == 'tijera') or \
       (j1 == 'papel' and j2 == 'piedra') or \
       (j1 == 'tijera' and j2 == 'papel'):
        return 1
    else:
        return 2

def guardar_datos():
    """Crea el archivo si no existe"""
    if not os.path.exists(fichero):
        with open(fichero, "w") as f:
            f.write("=== Registro de jugadas ===\n\n")

print("=== Juego de Piedra, Papel o Tijera ===")
print("Jugador 1: A = piedra | S = papel | D = tijera")
print("Jugador 2: 4 = piedra | 5 = papel | 6 = tijera")
print("Las jugadas no serán visibles en pantalla.\n")

guardar_datos()

while True:
    ronda += 1
    print(f"\n--- Ronda {ronda} ---")
    input("Presiona ENTER para comenzar...")
    print("Piedra...")
    time.sleep(0.5)
    print("Papel...")
    time.sleep(0.5)
    print("Tijeras...")
    time.sleep(0.5)
    print("¡YA!")

    # Jugador 1
    inicio = time.perf_counter()
    jugada_1 = getpass.getpass("Jugador 1, elige tu jugada (A/S/D): ").upper()
    tiempo_j1 = time.perf_counter() - inicio

    while jugada_1 not in jugadas_j1:
        print("Entrada no válida. Usa A, S o D.")
        jugada_1 = getpass.getpass("Jugador 1, elige tu jugada (A/S/D): ").upper()

    # Jugador 2
    inicio = time.perf_counter()
    jugada_2 = getpass.getpass("Jugador 2, elige tu jugada (4/5/6): ").upper()
    tiempo_j2 = time.perf_counter() - inicio

    while jugada_2 not in jugadas_j2:
        print("Entrada no válida. Usa 4, 5 o 6.")
        jugada_2 = getpass.getpass("Jugador 2, elige tu jugada (4/5/6): ").upper()

    j1 = jugadas_j1[jugada_1]
    j2 = jugadas_j2[jugada_2]

    # Repeticiones consecutivas
    if j1 == ultima_jugada_j1:
        repeticiones_j1 += 1
    else:
        repeticiones_j1 = 1

    if j2 == ultima_jugada_j2:
        repeticiones_j2 += 1
    else:
        repeticiones_j2 = 1

    # Determinar resultado
    resultado = determinar_ganador(j1, j2)
    if resultado == 1:
        print(f"Jugador1 ({j1}) ha GANADO contra Jugador2 ({j2})")
        racha_j1 += 1
        racha_j2 = 0
    elif resultado == 2:
        print(f"Jugador1 ({j1}) ha PERDIDO contra Jugador2 ({j2})")
        racha_j2 += 1
        racha_j1 = 0
    else:
        print(f"Empate: ambos eligieron {j1}")
        # En empate, las rachas se mantienen
        pass

    # Guardar jugada en archivo
    with open(fichero, "a") as f:
        f.write(f"Ronda: {ronda}\n")
        f.write(f"Jugador1: {j1}\n")
        f.write(f"Jugador2: {j2}\n")
        f.write(f"Tiempo reaccion jugador1: {tiempo_j1:.2f} s\n")
        f.write(f"Tiempo reaccion jugador2: {tiempo_j2:.2f} s\n")
        f.write(f"Racha victorias jugador1: {racha_j1}\n")
        f.write(f"Racha victorias jugador2: {racha_j2}\n")
        f.write(f"Jugador1 jugadas consecutivas: {repeticiones_j1}\n")
        f.write(f"Jugador2 jugadas consecutivas: {repeticiones_j2}\n")
        f.write("\n")

    # Actualizar últimas jugadas
    ultima_jugada_j1 = j1
    ultima_jugada_j2 = j2

    # Preguntar si continuar
    continuar = input("¿Jugar otra ronda? (s/n): ").lower()
    if continuar != 's':
        print("Juego terminado. ¡Gracias por jugar!")
        break
