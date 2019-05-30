#include <omp.h>
#include <stdio.h>
#include <stdlib.h>

void private() {
    int n = 1;
    #pragma omp parallel for private(n)
    for (int _ = 0; _ < 4; ++ _)
    {
        printf("Is %d 1?\n", n); // Undefined behavior
        n = 2;
    }
    printf("Is %d 2?\n", n); // Undefined behavior
}
void first_private() {
    int n = 1;
    #pragma omp parallel for firstprivate(n)
    for (int _ = 0; _ < 4; ++ _)
    {
        printf("%d is definitely 1.\n", n);
        n = 2;
    }
}
void last_private() {
    int n = 1;
    #pragma omp parallel for lastprivate(n)
    for (int i = 0; i < 4; ++ i)
    {
        n = 0;
        if (i == 3) { // Last iteration
            n = 2;
        }
    }
    printf("%d is definitely 2.\n", n);
}
int main(int argc, char *argv[]) {
    private(); puts("");
    first_private(); puts("");
    last_private();
    return 0;
}