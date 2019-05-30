#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 16
int main() {
    srand((unsigned int)time(NULL));
    int i, list[SIZE], sum;

    for (i = 0; i < SIZE; ++i) {
        list[i] = rand();
    }
    printf("The sum of %d", list[0]);
    for (i = 1; i < SIZE-1; ++i) {
        printf(", %d", list[i]);
    }

    sum = 0;
    #pragma omp parallel for reduction(+ : sum)
    for (i = 0; i < SIZE; ++i) {
        sum += list[i];
    }
    printf(" and %d is %d.\n", list[SIZE - 1], sum);

}