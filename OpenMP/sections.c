#include <stdio.h>

int main() {
    #pragma omp parallel
    {
        #pragma omp sections
        {
            #pragma omp section
            {
                puts("I'm from the first section.");
                puts("I'm very exciting.");
            }
            #pragma omp section
            {
                puts("I'm from the second section.");
                puts("I'm a little boring.");
            }
        }
    }
}