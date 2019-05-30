#include <stdio.h>

int main() {
    #pragma omp parallel num_threads(2)
    {
        puts("Hello.");
        #pragma omp for // Distribute the iterations to the active threads.
        for (int _ = 0; _ < 3; ++_) {
            puts("Now let's do something.");
        }
        puts("Goodbye.");
    }
}