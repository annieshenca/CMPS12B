/*
 * Annie Shen (ashen7@ucsc.edu)
 * CMPS 12B/M
 * pa5 - Due: Dec 2, 2016
 * DictionaryTest.c
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

//#define MAX_LEN 180

int main(int argc, char* argv[]) {
    Dictionary A = newDictionary();
    char *key;
    char *value;
    char *word1[] = {"one", "two", "three", "four", "five", "six"};
    char *word2[] = {"1", "2", "3", "4", "5", "6"};
    int i;

    printf("Size: %i\n", size(A));
    printf("Empty: %s\n", (isEmpty(A)?"true":"false"));
    printf("\n");

    for (i = 0; i < 6; i++) {
        insert(A, word1[i], word2[i]);
    }
    //insert(A, "five", "500"); //Error: inserting duplicate keys
    printDictionary(stdout, A);
    printf("\n");

    //Test lookup
    printf("Print word1[0]: %s\n", word1[0]);
    printf("Look up 'two': %s\n", lookup(A, "two"));
    printf("Look up 'forty': %s\n", lookup(A, "forty"));
    printf("\n");

    //Print out all keys and values pairs
    for(i = 0; i < 6; i++){
        key = word1[i];
        value = lookup(A, key);
        printf("key=\"%s\" %s\"%s\"\n", key, (value==NULL?"not found ":"value="), value);
    }
    printf("\n");

    delete(A, "three");
    delete(A, "five");
    //delete(A, "seven"); //Error: Deleting non-existing key
    printDictionary(stdout, A);
    printf("\n");

    for(i=0; i<6; i++){
        key = word1[i];
        value = lookup(A, key);
        printf("key=\"%s\" %s\"%s\"\n", key, (value==NULL?"not found ":"value="), value);
    }

    printf("\n");
    makeEmpty(A);
    printf("Size: %i\n", size(A));
    printf("Empty: %s\n", (isEmpty(A)?"true":"false"));
    freeDictionary(&A);
    printf("\n___DictionaryTest.c - end___");
    return(EXIT_SUCCESS);
}