/*
 * Annie Shen (ashen7@ucsc.edu)
 * CMPS 12B/M
 * pa5 - Due: Dec 2, 2016
 * Dictionary.c
 */

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize = 101; //Prime number

//rotate_left()
//Rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
    int sizeInBits = 8*sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);
    if(shift == 0)  return value;
    return (value << shift) | (value >> (sizeInBits - shift));
}

//pre_hash()
//Turn a string into an unsigned int
unsigned int pre_hash(char* input) {
    unsigned int result = 0xBAE86554;
    while(*input){
        result ^= *input++;
        result = rotate_left(result, 5);
    }
    return result;
}

//hash()
//Turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
    return pre_hash(key)%tableSize;
}

//-----------------------------------------------

//NodeObj
typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
} NodeObj;

//Node
typedef NodeObj* Node;

//newNode()
//Constructor for Node type
Node newNode(char* k, char* v) {
    Node N = malloc(sizeof(NodeObj));
    assert(N != NULL); //Die if N equals to NULL
    N->key = k;
    N->value = v;
    N->next = NULL;
    return(N);
}

//freeNode()
//Destructor for the Node type
void freeNode(Node* pN){
    if(pN != NULL && *pN != NULL){
        free(*pN); //Prevent memory leak
        *pN = NULL;
    }
}

//DictionaryObj
typedef struct DictionaryObj{
   Node* hashTable;
   int numPairs;
} DictionaryObj;

//findKey()
//Returns the Node containing key k in the subtree rooted at R, or returns
//NULL if no such Node exists
Node findKey(Node R, char* k){
    if(R == NULL || strcmp(k, R->key) == 0)
        return R;
    else    return findKey(R->next, k);
}

//printInOrder()
//Prints the (key, value) pairs belonging to the subtree rooted at R in order
//of increasing keys to file pointed to by out.
void printInOrder(FILE* out, Node R){
    if(R != NULL){
        fprintf(out, "%s %s\n", R->key, R->value);
        printInOrder(out, R->next);
    }
}


//deleteAll()
//Deletes all Nodes in N recursively
void deleteAll(Node N){
    if(N != NULL){
        deleteAll(N->next);
        freeNode(&N);
        N = NULL;
    }
}



//public functions -----------------------------------------------


//newDictionary()
//Constructor for the Dictionary type
Dictionary newDictionary(){
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D != NULL);
    D->hashTable = calloc(tableSize,sizeof(Node));//hashtable
    //assert(D != NULL);
    for(int i = 0; i < tableSize; i++){
        D->hashTable[i] = NULL;
    }
    D->numPairs = 0;
    return D;
}

//freeDictionary()
//Destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if(pD != NULL && *pD != NULL){
       makeEmpty(*pD);
       free((*pD)->hashTable);
       free(*pD);
       *pD = NULL;
   }
}

//isEmpty()
//Returns 1 (true) if D is empty, 0 (false) otherwise
int isEmpty(Dictionary D) {
    if (D == NULL) { //when Dictionary is NULL
        fprintf(stderr,
                "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return (D->numPairs == 0); //Empty or not depends on number of pairs in hashTable
}

//size()
//Returns the size of (key,value) pair in D
int size(Dictionary D){
    if(D == NULL){
        fprintf(stderr,
                "Dictionary Error: calling size() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return (D->numPairs);
}

//lookup()
//Return the value that is in D's (key, value),
//else return NULL if there's no such key
char* lookup(Dictionary D, char* k){
    Node N = NULL;
    int hashValue = hash(k);

    if(D == NULL){
        fprintf(stderr,
                "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if(D->hashTable[hashValue] != NULL){
        N = findKey(D->hashTable[hashValue], k);
    }
    return (N == NULL ? NULL : N->value); //Return NULL if no such key found
//    while(D->hashTable[hashValue] != NULL){
//        if(D->hashTable[hashValue]->key == k){
//            return D->hashTable[hashValue];
//        }
//        ++hashValue;
//    }
}

//insert()
//insert new (key,value) into D
void insert(Dictionary D, char* k, char* v) {
    
    if(D == NULL){
        fprintf(stderr,
                "Dictionary Error: calling insert() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }

    int hashValue = hash(k);
    if(findKey(D->hashTable[hashValue],k) == NULL) { //Equals to NULL when the key is NOT a duplicate
        if (D->hashTable[hashValue] == NULL) { //If hashValue's head is empty
            D->hashTable[hashValue] = newNode(k, v); //Set the new node as the head
        } else {
            Node temp = D->hashTable[hashValue];
            while (temp->next != NULL){ //loop to the last node
                temp = temp->next;
            }
            temp->next = newNode(k, v); //Insert new node as the last node
        }
        D->numPairs++; //Increment on number of (k,v) pairs
    } else{ //findkey(D->hashTable[hashValue],k) != NULL
        fprintf(stderr,
                "Dictionary Error: cannot insert duplicate key\n");
    }
}

//delete()
//delete k key from D's (key, value)
void delete(Dictionary D, char* k){
    int hashValue = hash(k);
    int found = 0;
    if(D == NULL){
        fprintf(stderr,
                "Dictionary error: calling delete() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }

    Node N = D->hashTable[hashValue];
    if(findKey(N, k) == NULL){ //Non-existing key
        fprintf(stderr, "Dictionary error: cannot delete non-existing keys\n");
        exit(EXIT_FAILURE);
    }
    while(N != NULL && found != 1){ //Loop until key is found
        if(strcmp(N->key, k) == 0) { //(=0) means the keys match!
            //N = N->next; //Doesn't work
            D->hashTable[hashValue] = D->hashTable[hashValue]->next;
            found = 1; //Get out of while loop once found
        } else{
            while(strcmp(N->next->key, k) != 0){
                N = N->next;
            }
            N->next = N->next->next;
        }
    } //Exit while loop
    freeNode(&N); //Prevent memory leak
    N = NULL;
    D->numPairs--; //Decrement the number of pairs
}

//makeEmpty()
//Resets-empty Dictionary D
void makeEmpty(Dictionary D){
    for(int i = 0; i < tableSize; i++){ //Delete all Nodes and set all to NULL
//        while(N != NULL){
//            //deleteAll(N);
//            N = N->next;
//            freeNode(&N);
//            N = NULL;
//        }
        Node N = D->hashTable[i];
        if(N != NULL){
            deleteAll(N);
            N = NULL;
        }        
    }
    //free(D->hashTable);
    //D->hashTable=NULL;
    D->numPairs = 0; //Reset number of pairs
}


//printDictionary()
//prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
    if(D == NULL){
        fprintf(stderr,
                "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }

    for(int i = 0; i < tableSize; i++) {
        printInOrder(out, D->hashTable[i]);
    }
}
