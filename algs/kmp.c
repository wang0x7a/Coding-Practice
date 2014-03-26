#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void pre_kmp(char *pat, int len, char *lps);
void kmp_search(char *txt, char *pat);

void main() {
  char *txt = "ABABDABACDABABCABAB";
  char *pat = "ABABCABAB";
  kmp_search(txt, pat);
  return;
}

void pre_kmp(char *pat, int len, char *lps) {
  int q;    // index of pat
  int k;    // the length of the current longest proper prefix 

  lps[0] = 0;
  k = 0;

  for (q = 1; q < len; q++) {
    // here we make of the fact that index of array starts from 0, which is
    // one unit less than the corresponding length
    while (k > 0 && pat[k] != pat[q])
      k = lps[k - 1];
    if (pat[k] == pat[q])
      k++;

    lps[q] = k;
  }

  return;
}

void kmp_search(char *txt, char *pat) {
  int txt_len = strlen(txt);
  int pat_len = strlen(pat);

  char *lps = malloc(sizeof(int) * pat_len);
  pre_kmp(pat, pat_len, lps);

  int i;
  int q = 0;
  for (i = 0; i < txt_len; i++) {
    while (q > 0 && pat[q] != txt[i])
      q = lps[q - 1];
    if (pat[q] == txt[i])
      q++;

    if (q == pat_len) {
      printf("Found pattern in %d\n", i - pat_len + 1);
      q = lps[q - 1];
    }
  }
}
