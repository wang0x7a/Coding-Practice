#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef char *String;

void pre_kmp(char *pat, int pat_len, int *lps);
void kmp_search(char *pat, char *txt);

int main() {
  char *txt = "ABABDABACDABABCABAB";
  char *pat = "ABABCABAB";
  kmp_search(pat, txt);
  return 0;
}

void kmp_search(char *pat, char *txt) {
  int pat_len = strlen(pat);
  int txt_len = strlen(txt);

  int *lps = malloc(sizeof(int) * pat_len);
  pre_kmp(pat, pat_len, lps);

  int i = 0;    // index for txt
  int j = 0;    // index for pat
  while (i < txt_len) {
    /*
    if (j == pat_len + 1) {
      j = 0;
      i++;
      printf("Find pattern in txt at: %d", i - j - 1);
      continue;
    }

    if (txt[i] != pat[j])
      j = lps[j];
    else
      j++;

    i++;
  }
  */
    if (txt[i] == pat[j]) {
      i++;
      j++;
    }
  
    if (j > pat_len) {
      printf("Find pattern in txt at %d\n", i - pat_len);
      j = 0;
    }

    if (txt[i] != pat[j]) {
      j = lps[j - 1];
    }
  }
  return;
}

void pre_kmp(char *pat, int pat_len, int *lps) {
  int len = 0;     // the length of the previous longest prefix
  int i;

  lps[0] = 0;
  i = 1;

  while (i < pat_len) {
    if (pat[i] == pat[len]) {
      len++;
      pat[i] = len;
      i++;
    }
    else {
      if (len != 0) {
        len = lps[len - 1];
      }
      else {
        lps[i] = 0;
        i++;
      }
    }
  }
}
