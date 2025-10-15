#include <stdio.h>
#include <stdlib.h>



int main() {
    printf("Listando archivos del directorio actual:\n");
    system("ls -l"); // Ejecuta el comando ls -l
    printf("Fin del comando.\n");
    return 0;
}