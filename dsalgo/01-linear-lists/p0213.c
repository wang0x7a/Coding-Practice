#include <stdio.h>
#include <limits.h>

#define MAX_LEN INT_MAX
#define MAX_GROUP INT_MAX

int get_group(int index);
//int calc_digits_num(int group_num, int* r, int n);
int calc_digits_num(int group_num, int acc[]);
int get_digits_num(int n);
int* calc_acc(int acc[], int len);

//int acc[5] = {0, 9, 189, 2889, 38889, 488889, 5888889};
//int acc[5] = {9, 189, 2889, 38889, 488889};
int acc[6] = {0, 45, 0, 0, 0, 0};

void main() {
  int index = 19;

  int* acc = {0, 0, 0, 0, 0, 0};
  acc = calc_acc(acc, 6);

  int i;
  int n = 0;
  for (i = 1; i < 6; i++) {
    n += acc[1];
    
    if (index >= n) break;
  }

}

int calc_digits_num(int group_num, int acc[]) {
  int n = 1;

  while (group_num / 10 > 0) n++;

  return acc[n - 1] + (group_num - acc[n - 1]) * n;
}

int* calc_acc(int acc[], int len) {
  int n = 2;
  int base = 1;

  int i;
  for (i = 1; i < len; i++) {
    acc[i] = acc[i - 1] * 9 * base + i * (1 + 9 * base) * 9 / 2;
    base *= 10;
  }

  return acc;
}

/* n is a 4-digit number at most */


/*
int calc_digits_num(int group_num, int* r, int n) {

  for (; n < group_num; n++) {
    r[n + 1] = r[n] + get_digits_num(n + 1); 
  }

  return r[group_num];
}

int get_digits_num(int n) {
  int res = 1;

  while (n / 10 > 0) {
    res++;
    n /= 10;
  }

  return res;
}
*/
