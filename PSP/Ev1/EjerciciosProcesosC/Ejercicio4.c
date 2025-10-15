
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <ctype.h>
#include <string.h>

// #TODO: Ampliación: Generar 2 hijos y que cada uno haga una tarea diferente, uno pasa a mayúsculas, otro cuenta palabras.
int main() {

    int pipeHijoAPadre[2];
    int pipePadreAHijo[2];
    char buffer[100];

    if (pipe(pipeHijoAPadre) == -1) {
        perror("Pipe hijo a padre");
        exit(1);
    }

    if (pipe(pipePadreAHijo) == -1) {
        perror("Pipe padre a hijo");
        exit(1);
    }

    pid_t pid = fork();

    if (pid < 0) {
        perror("fork");
        exit(1);
    } 
    else if (pid == 0) {
        // ----------------------------
        // Código del hijo
        // ----------------------------
        read(pipePadreAHijo[0], buffer, sizeof(buffer));  // Leemos lo que escribió el padre
        printf("Hijo recibió: %s\n", buffer); // Mostramos el mensaje
        //recorrer el mensaje y hacerlo mayusculas
        int i = 0;
        while (buffer[i] != '\0') {          
            buffer[i] = toupper(buffer[i]);  
            i++;
        }
        printf("Hijo transformó: %s\n", buffer); // Mostramos el mensaje
        close(pipeHijoAPadre[0]);
        close(pipePadreAHijo[1]); // El hijo no escribe aquí

        write(pipeHijoAPadre[1], buffer, sizeof(buffer)); // Escribimos en el pipe
        close(pipeHijoAPadre[1]); // Cerramos el extremo de escritura
        _exit(0);     // Terminamos el hijo
    } 
    else {
        // ----------------------------
        // Código del padre
        // ----------------------------
        close(pipePadreAHijo[0]); // Cerramos el extremo de lectura del padre
        close(pipeHijoAPadre[1]); // El padre no escribe aquí
        char mensaje[] = "Hola mundo, aqui el padre hablando al hijo";
        write(pipePadreAHijo[1], mensaje, sizeof(mensaje));
        wait(NULL);   // Esperamos que el hijo termine
        read(pipeHijoAPadre[0], buffer, sizeof(buffer));  // Leemos lo que escribió el hijo
        printf("Padre recibió: %s\n", buffer); // Mostramos el mensaje
    }

    return 0;

}