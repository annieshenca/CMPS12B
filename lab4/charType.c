/*Annie Shen(ashen7@ucsc.edu)*/

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

//string s, Alphabet, Digits, Punctuation, Whitespace.
void extract_chars(char* s, char* a, char* d, char* p, char* w);
int count(char* c);
void reinit(char* x);

int main(int argc, char* argv[]){
    FILE* in;       //handle for input file
    FILE* out;      //handle for output file
    char* input;    //string holding input line
    char* chara;    //string holding all alphabetic characters
    char* num;      //string holding all numbers
    char* punc;     //string holding all punctuation characters
    char* white;    //string holding all whitespaces
    
    //check command line for correct number of arguments
    if( argc != 3 ){
        printf("Usage: %s <input-file><output-file>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    //open input file for reading
    if( (in=fopen(argv[1], "r")) == NULL ) {
        printf("Unable to read from file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }

    //open output file for writing
    if( (out=fopen(argv[2], "w")) == NULL ) {
        printf("Unable to write to file %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }

    //allocate strings line and input on the heap
    input = (char*) calloc(MAX_STRING_LENGTH + 1, sizeof(char) );
    chara = (char*) calloc(MAX_STRING_LENGTH + 1, sizeof(char) );
    num   = (char*) calloc(MAX_STRING_LENGTH + 1, sizeof(char) );
    punc  = (char*) calloc(MAX_STRING_LENGTH + 1, sizeof(char) );
    white = (char*) calloc(MAX_STRING_LENGTH + 1, sizeof(char) );
    assert( input!=NULL && chara!=NULL && num!=NULL && punc!=NULL && white!=NULL);

    int line = 1;
    while( fgets(input, MAX_STRING_LENGTH, in) != NULL ){
        extract_chars(input, chara, num, punc, white);


        fprintf(out, "line %d contains: \n", line);
        if(count(chara)==1){
                fprintf(out, "%i alphanumeric character: %s\n",count(chara), chara);
        }else{
                fprintf(out, "%i alphanumeric characters: %s\n",count(chara),chara );
        }
        if(count(num)==1){
                fprintf(out, "%i numeric character: %s\n",count(num),num );
        }else{
                fprintf(out, "%i numeric characters: %s\n",count(num),num );
        }
        if(count(punc)==1){
                fprintf(out, "%i punctuation character: %s\n",count(punc),punc);
        }else{
                fprintf(out, "%i punctuation characters: %s\n",count(punc),punc);
        }
        if(count(white)==1){
                fprintf(out, "%i whitespace character: %s\n",count(white),white);
        }else{
                fprintf(out, "%i whitespace characters: %s\n",count(white),white );
        }
        reinit(chara);
        reinit(num);
        reinit(punc);
        reinit(white);
        line++;
    }

    //free heap memory
    free(input);
    free(chara);
    free(num);
    free(punc);
    free(white);
    input = NULL;
    chara = NULL;
    num = NULL;
    punc = NULL;
    white = NULL;

    //close input and output files
    fclose(in);
    fclose(out);

    return EXIT_SUCCESS;
}

//function definition
void extract_chars(char* s, char* a, char* d, char* p, char* w) {
    int i=0;
    int j=0;
    int k=0;
    int l=0;
    int m=0;
    while(s[i]!='\0' && i < MAX_STRING_LENGTH) {
        if (isalpha(s[i])) {
            a[j] = s[i];
            j++;
        }
        if (isdigit(s[i])) {
            d[k] = s[i];
            k++;
        }
        if (ispunct(s[i])) {
            p[l] = s[i];
            l++;
        }
        if (isspace(s[i])) {
            w[m] = s[i];
            m++;
        }
        i++;
    }
}

int count(char* c){
        int i = 0;
        while(c[i] != '\0'){
                i++;
        }
        return i;
}

void reinit(char* x){
        int j = 0;
        while(j <= MAX_STRING_LENGTH ){
                x[j] = '\0';
                j++;
        }
}
