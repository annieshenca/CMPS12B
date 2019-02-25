//Annie Shen (ashen7@ucsc.edu)
//DictionaryTest.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

int main(int argc, char* argv[]){
    Dictionary D = newDictionary();

    char* key;
    char* value;
    char* numbers[] = {"1","2","3","4","5","6"};
    char* words[]  =  {"one","two","three","four","five","six"};
    int i;

    for(i=0; i<6; i++){
        insert(D, numbers[i], words[i]);
    }

    printf("Size: %i\n", size(D)); //6
    printf("Is empty: %i\n", isEmpty(D)); //return 0 for true - NOT empty

    printf("Lookup numbers '2': %s\n", lookup(D, "2"));
    //delete(D, "4"); //made a mistake here where I would delete "3" when asking to delete "4"
    printf("Lookup numbers '3': %s\n", lookup(D, "3"));
    delete(D, "4");
    printf("Lookup numbers '4': %s\n", lookup(D, "4"));

    //printf("before printDictionary\n");
    printDictionary(stdout, D);
    //printf("after printDictionary\n");

    for(i=0; i<6; i++){
        key = numbers[i];
        value = lookup(D, key);
        printf("key=\"%s\" %s\"%s\"\n", key, (value==NULL?"not found ":"value="), value);
    }

    printf("%s\n", (isEmpty(D)?"true":"false"));
    printf("%d\n", size(D)); //5 instead of 6 because deleted "4"
    makeEmpty(D); //empty Dictionary D
    printf("%s\n", (isEmpty(D)?"true":"false"));

    freeDictionary(&D);

    return(EXIT_SUCCESS);
}
